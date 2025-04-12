package Controlador;

import java.sql.SQLException;
import java.util.ArrayList;

import Modelo.Producto;

public class ControladorProducto {
	private ProductoDAO productodao;

	public ControladorProducto() throws SQLException {
		this.productodao = new ProductoDAO();
	}

	public boolean Guardar(Producto producto) throws SQLException {
		return productodao.Guardar(producto);
		
	}
	public boolean Eliminar(int id) throws SQLException {
		return productodao.Eliminar(id);
	}

	public boolean Actualizar(int idProducto, double precio,  int precio_venta) throws SQLException{
		return productodao.Actualizar(idProducto, precio, precio_venta);
	}
	public ArrayList<Producto> Listar() throws SQLException{
		return productodao.Listar();
	}
}
