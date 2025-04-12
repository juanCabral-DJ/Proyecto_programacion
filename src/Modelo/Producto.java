package Modelo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Producto extends Exportar_Excel<Producto> {
private int idproducto;
private String nombre;
private double precio;
private String color;
private String tipo_pintura;
private int precio_venta;

public Producto() {}

public Producto(int idproducto, String nombre, double precio, String color, String tipo_pintura, int precio_venta) {
	this.idproducto = idproducto;
	this.nombre = nombre;
	this.precio = precio;
	this.color = color;
	this.tipo_pintura = tipo_pintura;
	this.precio_venta = precio_venta;
}

public void setidproducto() {
	this.idproducto = idproducto;
}
public int getidproducto() {
	return idproducto;
}

public void setnombre() {
	this.nombre = nombre;
}
public String getnombre() {
	return nombre;
}

public void setprecio() {
	this.precio = precio;
}
public double getprecio() {
	return precio;
}

public void setcolor() {
	this.color = color;
}
public String getcolor() {
	return color;
}

public void settipo_pintura() {
	this.tipo_pintura = tipo_pintura;
}
public String gettipo_pintura() {
	return tipo_pintura;
}

public void setprecio_venta() {
	this.precio_venta = precio_venta;
}
public int getprecio_venta() {
	return precio_venta;
}

@Override
public void exportarAExcel(ArrayList<Producto> producto, String rutaArchivo) {
	// TODO Auto-generated method stub
	 Workbook workbook = new XSSFWorkbook();
     Sheet hoja = workbook.createSheet("Clientes");

     // Cabecera
     Row header = hoja.createRow(0);
     header.createCell(0).setCellValue("id");
     header.createCell(1).setCellValue("nombre");
     header.createCell(2).setCellValue("precio de producción");
     header.createCell(3).setCellValue("color");
     header.createCell(4).setCellValue("tipo");
     header.createCell(5).setCellValue("precio de venta");

     // Contenido
     int rowNum = 1;
     for (Producto p : producto) {
         Row fila = hoja.createRow(rowNum++);
         fila.createCell(0).setCellValue(p.getidproducto());
         fila.createCell(1).setCellValue(p.getnombre());
         fila.createCell(2).setCellValue(p.getprecio());
         fila.createCell(3).setCellValue(p.getcolor());
         fila.createCell(4).setCellValue(p.gettipo_pintura());
         fila.createCell(5).setCellValue(p.getprecio_venta());
     }

     // Ajustar el tamaño de columnas
     for (int i = 0; i < 5; i++) {
         hoja.autoSizeColumn(i);
     }

     // Guardar archivo
     try (FileOutputStream out = new FileOutputStream(rutaArchivo)) {
         workbook.write(out);
         workbook.close();
         System.out.println("Archivo Excel creado exitosamente en: " + rutaArchivo);
     } catch (IOException e) {
         e.printStackTrace();
     }
 }
}
