package Modelo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Cliente extends Exportar_Excel<Cliente> {
private int idCliente;
private String nombre;
private String direccion;
private String Contacto;

public Cliente() {}

public Cliente(int idCliente, String nombre, String direccion, String Contacto) {
	this.idCliente = idCliente;
	this.nombre = nombre;
	this.direccion = direccion;
	this.Contacto = Contacto;
}

public void setidcliente() {
	this.idCliente = idCliente;
}
public int getidcliente() {
	return idCliente;
}

public void setnombre() {
	this.nombre = nombre;
}
public String getnombre() {
	return nombre;
}

public void setdireccion() {
	this.direccion = direccion;
}
public String getdireccion() {
	return direccion;
}

public void setContacto() {
	this.Contacto = Contacto;
}
public String getContacto() {
	return Contacto;
}

@Override
public void exportarAExcel(ArrayList<Cliente> clientes, String rutaArchivo) {
	// TODO Auto-generated method stub
	 Workbook workbook = new XSSFWorkbook();
     Sheet hoja = workbook.createSheet("Clientes");

     // Cabecera
     Row header = hoja.createRow(0);
     header.createCell(0).setCellValue("id");
     header.createCell(1).setCellValue("nombre");
     header.createCell(2).setCellValue("direccion");
     header.createCell(3).setCellValue("contacto");

     // Contenido
     int rowNum = 1;
     for (Cliente c : clientes) {
         Row fila = hoja.createRow(rowNum++);
         fila.createCell(0).setCellValue(c.getidcliente());
         fila.createCell(1).setCellValue(c.getnombre());
         fila.createCell(2).setCellValue(c.getdireccion());
         fila.createCell(3).setCellValue(c.getContacto());
     }

     // Ajustar el tamaño de columnas
     for (int i = 0; i < 4; i++) {
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

