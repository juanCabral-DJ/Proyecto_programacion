package Modelo;

import Modelo.Factura;

public interface Imprimible {

	public  void imprimirFactura(String filePath) throws Exception;
	public  void generarPDF(Factura factura, String rutaArchivo) throws Exception;
}
