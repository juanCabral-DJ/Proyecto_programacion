package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Modelo.Cliente;
import Modelo.Databaseconnection;


public class ClienteDAO extends DAOBase<Cliente>{
	private Connection connection;
	
	public ClienteDAO() throws SQLException {
		this.connection = Databaseconnection.getInstancia().getConnection();
	}
	
	@Override
	public boolean Guardar(Cliente cliente) throws SQLException{
		String sql = "INSERT INTO cliente (idCliente, nombre, Direccion, Contacto) VALUES (?, ?, ?, ?)";
		
		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setInt(1, cliente.getidcliente());
			stmt.setString(2, cliente.getnombre());
			stmt.setString(3, cliente.getdireccion());
			stmt.setString(4, cliente.getContacto());
			
			int resultado = stmt.executeUpdate();
			 return resultado > 0;
		} 	
		
	}
	
	@Override
	public boolean Eliminar(int idcliente) throws SQLException{
		String sql = "DELETE FROM cliente Where IdCliente = ?";
		
		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setInt(1, idcliente);
			
		
			int resultado = stmt.executeUpdate();
			 return resultado > 0;
		}
	}
	
	public boolean Actualizar(int idCliente, String Direccion_new, String Contato_new) throws SQLException{
		String sql = "UPDATE cliente SET Direccion = ?, Contacto = ? WHERE idCliente = ?";
		
		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setString(1, Direccion_new);
			stmt.setString(2, Contato_new);
			stmt.setInt(3, idCliente);          
	
			int resultado = stmt.executeUpdate();
			 return resultado > 0;
		}
	}
	
	@Override
	public ArrayList<Cliente> Listar() throws SQLException{
		ArrayList<Cliente> clientes = new ArrayList<>();
		String sql = "SELECT idCliente, nombre, Direccion, Contacto FROM cliente";
		
		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			ResultSet st = stmt.executeQuery();
			
			while(st.next()) {
				int idCliente = st.getInt("idCliente");
				String nombre = st.getString("nombre");
				String direccion = st.getString("Direccion");
				String contacto = st.getString("Contacto");
				
				clientes.add(new Cliente(idCliente, nombre, direccion, contacto));
			}
		}
		return clientes;
	}
	
}
