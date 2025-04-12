package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Modelo.Cliente;
import Modelo.Databaseconnection;
import Modelo.Producto;

public class ProductoDAO extends DAOBase<Producto> {
private Connection connection;

public ProductoDAO() throws SQLException {
	this.connection = Databaseconnection.getInstancia().getConnection();
}

public boolean Actualizar(int idProducto, double precio,  int precio_venta) throws SQLException{
	String sql = "UPDATE producto SET precio = ?, precio_venta = ? WHERE idProducto = ?";
	
	try (PreparedStatement stmt = connection.prepareStatement(sql)){
		stmt.setDouble(1, precio);
		stmt.setInt(2, precio_venta);
		stmt.setInt(3, idProducto);   

		int resultado = stmt.executeUpdate();
		 return resultado > 0;
	}
}

@Override
public boolean Guardar(Producto producto) throws SQLException {
	// TODO Auto-generated method stub
	String sql = "INSERT INTO producto (idProducto, nombre, precio, color, tipo_pintura, precio_venta) VALUES (?, ?, ?, ?, ?, ?)";
	
	try (PreparedStatement stmt = connection.prepareStatement(sql)){
		stmt.setInt(1, producto.getidproducto());
		stmt.setString(2, producto.getnombre());
		stmt.setDouble(3, producto.getprecio());
		stmt.setString(4, producto.getcolor());
		stmt.setString(5, producto.gettipo_pintura());
		stmt.setInt(6, producto.getprecio_venta());
		
		int resultado = stmt.executeUpdate();
		 return resultado > 0;
	}
}

@Override
public ArrayList<Producto> Listar() throws SQLException{
	ArrayList<Producto> productos = new ArrayList<>();
	String sql = "SELECT idProducto, nombre, precio, color, tipo_pintura, precio_venta FROM producto";
	
	try (PreparedStatement stmt = connection.prepareStatement(sql)){
		ResultSet st = stmt.executeQuery();
		
		while(st.next()) {
			int idProducto = st.getInt("idProducto");
			String nombre = st.getString("nombre");
			double precio = st.getDouble("precio");
			String color = st.getString("color");
			String tipo_pintura = st.getString("tipo_pintura");
			int precio_venta = st.getInt("precio_venta");
			
			productos.add(new Producto(idProducto, nombre, precio, color, tipo_pintura, precio_venta));
		}
	}
	return productos;
}

@Override
public boolean Eliminar(int idproducto) throws SQLException {
	// TODO Auto-generated method stub
	String sql = "DELETE FROM producto Where idProducto = ?";
	
	try (PreparedStatement stmt = connection.prepareStatement(sql)){
		stmt.setInt(1, idproducto);
		
		int resultado = stmt.executeUpdate();
		 return resultado > 0;
	}
}

}
