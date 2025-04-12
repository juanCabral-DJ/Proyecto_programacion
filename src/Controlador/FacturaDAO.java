package Controlador;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Modelo.Databaseconnection;
import Modelo.Imprimible;
import Modelo.Producto;
import Modelo.Factura;
import Modelo.Factura.Detalle_Factura;

public class FacturaDAO extends DAOBase<Factura> implements Imprimible{
	private Connection connection;

	public FacturaDAO() throws SQLException {
		this.connection = Databaseconnection.getInstancia().getConnection();
	}
	
	@Override
	public boolean Guardar(Factura factura) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO factura (idFactura, fecha, fkcliente, vendedor, pago, estado, tipo_factura) VALUES (?, ?, ?, ?, ?, ?, ?)";
		String sqlDetalle = "INSERT INTO detalle_factura (fkfactura, fkproducto, cantidad, ITBIS, precio_venta) VALUES (?, ?, ?, ?, ?)";
		
		  try {
		        connection.setAutoCommit(false); // Iniciar transacción

		        // Insertar en factura
		        PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		        stmt.setInt(1, factura.getidfactura());
		            stmt.setString(2, factura.getfecha());
		            stmt.setInt(3, factura.getfkcliente());
		            stmt.setString(4, factura.getvendedor());
		            stmt.setInt(5, factura.getpago());
		            stmt.setString(6, factura.getestado());
		            stmt.setString(7, factura.gettipo());

		            int affectedRows = stmt.executeUpdate();
		            if (affectedRows == 0) throw new SQLException("No se pudo insertar la factura.");

		            int idFactura;
		            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
		                if (generatedKeys.next()) {
		                    idFactura = generatedKeys.getInt(1);
		                } else {
		                    throw new SQLException("No se obtuvo el ID de la factura.");
		                }
		            }

		            // Insertar detalles
		            try (PreparedStatement stmtDetalle = connection.prepareStatement(sqlDetalle)) {
		            	if (factura.getDetalles() == null || factura.getDetalles().isEmpty()) {
		            	    throw new SQLException("No hay detalles de factura para insertar.");
		            	}
		                for (Detalle_Factura detalle : factura.getDetalles()) {
		                    stmtDetalle.setInt(1, idFactura);
		                    stmtDetalle.setInt(2, detalle.getfkproducto());
		                    stmtDetalle.setInt(3, detalle.getcantidad());
		                    stmtDetalle.setDouble(4, detalle.getITBIS());
		                    stmtDetalle.setInt(5, detalle.getprecio_venta());

		                    stmtDetalle.addBatch(); // Agrega al batch para ejecutar todos juntos
		                }
		                stmtDetalle.executeBatch(); // Ejecutar todos los inserts de detalles
		            }

		            connection.commit(); // Confirmar la transacción
		            Factura facturaGuardada = obtenerPorId(idFactura); // usa el método nuevo		   
		            String rutaArchivo = "factura_" + idFactura + ".pdf";
		            
                    try {
		            // Generar e imprimir PDF
		            generarPDF(facturaGuardada, rutaArchivo);
		            imprimirFactura(rutaArchivo);
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		            return true;
		        } catch (SQLException e) {
		            connection.rollback(); // En caso de error, deshacer la transacción
		            throw e;   
		        }finally{
		        connection.setAutoCommit(true); // Restaurar el modo autocommit
		        }
		  }

	
	@Override
	public boolean Eliminar(int idfactura) throws SQLException {
		// TODO Auto-generated method stub
      String sql = "DELETE FROM factura Where idFactura = ?";
		
		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setInt(1, idfactura);
			
			int resultado = stmt.executeUpdate();
			 return resultado > 0;
		}
	}

	@Override
	public ArrayList<Factura> Listar() throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<Factura> facturas = new ArrayList<>();
		 String sql = "SELECT idFactura, fecha, fkcliente, vendedor, pago, estado From factura";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        ResultSet st = stmt.executeQuery();
        
        while(st.next()) {
                int idfactura = st.getInt("idFactura");
                String fecha = st.getString("fecha");
                int fkcliente = st.getInt("fkcliente");
                String vendedor = st.getString("vendedor");
                int pago = st.getInt("pago");
                String estado = st.getString("estado"); 
                
                facturas.add(new Factura(idfactura, fecha, fkcliente, vendedor, pago, estado));
            }
    }
   
    return facturas;
}

	
	
	public Factura obtenerPorId(int idFactura) throws SQLException {
	    String sql = "SELECT idFactura, fecha, fkcliente, vendedor, pago, estado, tipo_factura, " +
	                 "iddetalle_factura, fkproducto, cantidad, ITBIS, precio_venta " +
	                 "FROM factura LEFT JOIN detalle_factura ON idFactura = fkfactura " +
	                 "WHERE idFactura = ?";

	    Factura factura = null;

	    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	        stmt.setInt(1, idFactura);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            if (factura == null) {
	                // Crear la factura si aún no se ha creado
	                String fecha = rs.getString("fecha");
	                int fkcliente = rs.getInt("fkcliente");
	                String vendedor = rs.getString("vendedor");
	                int pago = rs.getInt("pago");
	                String estado = rs.getString("estado");
	                String tipo = rs.getString("tipo_factura");

	                factura = new Factura(idFactura, fecha, fkcliente, vendedor, pago, estado, tipo);
	            }

	            // Extraer detalles si hay
	            int iddetalle = rs.getInt("iddetalle_factura"); // por si lo necesitas
	            int fkproducto = rs.getInt("fkproducto");
	            int cantidad = rs.getInt("cantidad");
	            double itbis = rs.getDouble("ITBIS");
	            int precioVenta = rs.getInt("precio_venta");

	            // Si hay un producto válido
	            if (fkproducto > 0) {
	                Factura.Detalle_Factura detalle = factura.new Detalle_Factura(iddetalle, fkproducto, cantidad);
	                detalle.setITBIS(itbis);
	                detalle.setprecio_venta(precioVenta);

	                factura.agregarDetalle(detalle);
	            }
	        }
	    }

	    return factura;
	}

	@Override
	public void imprimirFactura(String filePath) throws Exception{
		// TODO Auto-generated method stub
		try {
            // Comando para PDFCreator
            ProcessBuilder pb = new ProcessBuilder(
                "C:\\Program Files\\PDFCreator\\PDFCreator.exe",
                "/PrintFile=\"" + filePath + "\""
            );
            Process process = pb.start();
            boolean terminado = process.waitFor(10, TimeUnit.SECONDS);

            if (!terminado) {
                // Si no terminó a tiempo, forzar su cierre
                process.destroy();
                System.err.println("Impresión detenida: tiempo de espera excedido.");
            } else {
                int exitCode = process.exitValue();
                if (exitCode != 0) {
                    System.err.println("Impresión fallida con código de salida: " + exitCode);
                } else {
                    System.out.println("Impresión completada correctamente.");
                }
            }
        } catch (Exception e) {
            System.err.println("Error al imprimir la factura: " + e.getMessage());
        }
	}

	@Override
	public void generarPDF(Factura factura, String rutaArchivo) throws Exception {
		// TODO Auto-generated method stub
		 Document document = new Document();
         PdfWriter.getInstance(document, new FileOutputStream(rutaArchivo));
         document.open();

         // Fuentes
         Font negrita = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
         Font normal = new Font(Font.FontFamily.HELVETICA, 12);

         // Título
         Paragraph titulo = new Paragraph("FACTURA", new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD));
         titulo.setAlignment(Element.ALIGN_CENTER);
         document.add(titulo);
         document.add(new Paragraph(" ")); // Espacio

         // Datos de la factura
         document.add(new Paragraph("ID Factura: " + factura.getidfactura(), normal));
         document.add(new Paragraph("Fecha: " + factura.getfecha(), normal));
         document.add(new Paragraph("Cliente ID: " + factura.getfkcliente(), normal));
         document.add(new Paragraph("Vendedor: " + factura.getvendedor(), normal));
         document.add(new Paragraph("Forma de Pago: RD$ " + factura.getpago(), normal));
         document.add(new Paragraph("Estado: " + factura.getestado(), normal));
         document.add(new Paragraph("Factura de: " + factura.gettipo(), normal));
         document.add(new Paragraph(" "));

         // Tabla de productos
         PdfPTable tabla = new PdfPTable(5); // 5 columnas
         tabla.setWidthPercentage(100);
         tabla.setSpacingBefore(10f);
         tabla.setSpacingAfter(10f);

         // Encabezados
         String[] encabezados = {"Producto ID", "Cantidad", "Precio", "ITBIS (%)", "Total"};
         for (String encabezado : encabezados) {
             PdfPCell celda = new PdfPCell(new Phrase(encabezado, negrita));
             celda.setHorizontalAlignment(Element.ALIGN_CENTER);
             tabla.addCell(celda);
         }

         // Detalles
         for (Factura.Detalle_Factura detalle : factura.getDetalles()) {
             double totalLinea = detalle.getcantidad() * detalle.getprecio_venta();
             tabla.addCell(String.valueOf(detalle.getfkproducto()));
             tabla.addCell(String.valueOf(detalle.getcantidad()));
             tabla.addCell(String.format("RD$ %.2f", (double) detalle.getprecio_venta()));
             tabla.addCell(String.format("%.2f", detalle.getITBIS() * 100)); // como porcentaje
             tabla.addCell(String.format("RD$ %.2f", totalLinea));
         }

         document.add(tabla);

         // Resumen
         Paragraph subtotal = new Paragraph("Subtotal: RD$ " + String.format("%.2f", factura.calcularSubtotal()), normal);
         Paragraph itbis = new Paragraph("ITBIS: RD$ " + String.format("%.2f", factura.calcularITBIS()), normal);
         Paragraph total = new Paragraph("Total: RD$ " + String.format("%.2f", factura.calcularTotal()), negrita);
         Paragraph pago = new Paragraph("Pago del Cliente: RD$ " + factura.getpago(), normal);
         Paragraph cambio = new Paragraph("Cambio: RD$ " + String.format("%.2f", factura.calcularCambio()), normal);

         subtotal.setAlignment(Element.ALIGN_RIGHT);
         itbis.setAlignment(Element.ALIGN_RIGHT);
         total.setAlignment(Element.ALIGN_RIGHT);
         pago.setAlignment(Element.ALIGN_RIGHT);
         cambio.setAlignment(Element.ALIGN_RIGHT);

         document.add(subtotal);
         document.add(itbis);
         document.add(total);
         document.add(pago);
         document.add(cambio);

         document.close();
         System.out.println("Factura PDF generada en: " + rutaArchivo);

     }

}
