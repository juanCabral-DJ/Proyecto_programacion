package Modelo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Factura extends  Exportar_Excel<Factura> implements Calculable {
private int idfactura;
private String fecha;
private int fkcliente;
private String vendedor;
private int pago;
private String estado;
private String tipo;
private ArrayList<Detalle_Factura> detalles = new ArrayList<>();

public Factura() {}
public Factura(int idfactura, String fecha, int fkcliente, String vendedor,  int pago, String estado, String tipo) {
	this.idfactura = idfactura;
	this.fecha = fecha;
	this.fkcliente = fkcliente;
	this.vendedor = vendedor;
	this.pago = pago;
	this.estado = estado;
	this.tipo = tipo;
	this.detalles = new ArrayList<>();
}

public Factura(int idfactura, String fecha, int fkcliente, String vendedor, int pago, String estado) {
	// TODO Auto-generated constructor stub
	this.idfactura = idfactura;
	this.fecha = fecha;
	this.fkcliente = fkcliente;
	this.vendedor = vendedor;
	this.pago = pago;
	this.estado = estado;
}
public void setidfactura(int idfactura) {
	this.idfactura = idfactura;
}
public int getidfactura() {
	return idfactura;
}

public void setfecha(String fecha) {
	this.fecha = fecha;
}
public String getfecha() {
	return fecha;
}

public void setfkcliente(int fkcliente) {
	this.fkcliente = fkcliente;
}
public int getfkcliente() {
	return fkcliente;
}

public void setvendedor(String vendedor) {
	this.vendedor = vendedor;
}
public String getvendedor() {
	return vendedor;
}

public void setpago(int pago) {
	this.pago = pago;
}
public int getpago() {
	return pago;
}

public void setestado(String estado) {
	this.estado = estado;
}
public String getestado() {
	return estado;
}

public void settipo(String tipo) {
	this.tipo = tipo;
}
public String gettipo() {
	return tipo;
}


public void agregarDetalle(Detalle_Factura detalle) {
    this.detalles.add(detalle);
}
public void setDetalles(ArrayList<Detalle_Factura> detalles) {
    this.detalles = detalles;
}
public ArrayList<Detalle_Factura> getDetalles() {
    return detalles;
}

public class Detalle_Factura {
private int iddetalle_factura;
private int fkfactura;
private int fkproducto;
private int cantidad;
private double ITBIS;
private int precio_venta;

public Detalle_Factura() {}
public Detalle_Factura(int iddetalle_factura, int fkfactura, int fkproducto, int cantidad, double ITBIS, int precio_venta) {
	this.iddetalle_factura = iddetalle_factura;
	this.fkfactura = fkfactura;
	this.fkproducto = fkproducto;
	this.cantidad = cantidad;
	this.ITBIS = ITBIS;
	this.precio_venta = precio_venta;
}
public Detalle_Factura(int iddetalle_factura, int fkproducto, int cantidad) {
	this.iddetalle_factura = iddetalle_factura;
	this.fkproducto = fkproducto;
	this.cantidad = cantidad;
 }

public void setidfactura() {
	this.iddetalle_factura = iddetalle_factura;
}
public int getiddetalle_factura() {
	return iddetalle_factura;
}

public void setfkfactura() {
	this.fkfactura = fkfactura;
}
public int getfkfactura() {
	return fkfactura;
}

public void setfkproducto(int fkproducto) {
	this.fkproducto = fkproducto;
}
public int getfkproducto() {
	return fkproducto;
}

public void setcantidad(int cantidad) {
	this.cantidad = cantidad;
}
public int getcantidad() {
	return cantidad;
}

public void setITBIS(double ITBIS) {
	this.ITBIS = ITBIS;
}
public double getITBIS() {
	return ITBIS;
}

public void setprecio_venta(int precio_venta) {
	this.precio_venta = precio_venta;
}
public int getprecio_venta() {
	return precio_venta;
}
}

@Override
public void exportarAExcel(ArrayList<Factura> facturas, String rutaArchivo) {
	// TODO Auto-generated method stub
    Workbook workbook = new XSSFWorkbook();
    Sheet hoja = workbook.createSheet("Facturas");

    // Cabecera
    Row header = hoja.createRow(0);
    header.createCell(0).setCellValue("ID Factura");
    header.createCell(1).setCellValue("Fecha");
    header.createCell(2).setCellValue("Cliente");
    header.createCell(3).setCellValue("Vendedor");
    header.createCell(4).setCellValue("Pago");
    header.createCell(5).setCellValue("Estado");

    int rowNum = 1;

    for (Factura f : facturas) {
            Row fila = hoja.createRow(rowNum++);
            fila.createCell(0).setCellValue(f.getidfactura());
            fila.createCell(1).setCellValue(f.getfecha());
            fila.createCell(2).setCellValue(f.getfkcliente());
            fila.createCell(3).setCellValue(f.getvendedor());
            fila.createCell(4).setCellValue(f.getpago());
            fila.createCell(5).setCellValue(f.getestado());
         }

    for (int i = 0; i < 8; i++) hoja.autoSizeColumn(i);

    try (FileOutputStream out = new FileOutputStream(rutaArchivo)) {
        workbook.write(out);
        workbook.close();
        System.out.println("Archivo Excel creado exitosamente en: " + rutaArchivo);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

@Override
public double redondear(double valor) {
	// TODO Auto-generated method stub
	 return Math.round(valor * 100.0) / 100.0;
}

@Override
public double calcularSubtotal() {
	// TODO Auto-generated method stub
	double subtotal = 0.0;
    for (Detalle_Factura detalle : detalles) {
        subtotal += detalle.getprecio_venta() * detalle.getcantidad();}
    return redondear(subtotal);
}

@Override
public double calcularITBIS() {
	// TODO Auto-generated method stub
	double totalITBIS = 0.0;
    for (Detalle_Factura detalle : detalles) {
        double base = detalle.getprecio_venta() * detalle.getcantidad();
        totalITBIS += base * detalle.getITBIS();}
    return redondear(totalITBIS);
}

@Override
public double calcularTotal() {
	// TODO Auto-generated method stub
	return redondear(calcularSubtotal() + calcularITBIS());
}

@Override
public double calcularCambio() {
	// TODO Auto-generated method stub
	return redondear(pago - calcularTotal());
}
}
