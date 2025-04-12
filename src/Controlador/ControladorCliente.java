package Controlador;

import java.sql.SQLException;
import java.util.ArrayList;

import Modelo.Cliente;

public class ControladorCliente {
private ClienteDAO clientedao;

public ControladorCliente() throws SQLException {
	this.clientedao = new ClienteDAO();
}

public boolean Guardar(Cliente cliente) throws SQLException {
	return clientedao.Guardar(cliente);
	
}
public boolean Eliminar(int id) throws SQLException {
	return clientedao.Eliminar(id);
}

public boolean Actualizar(int idCliente, String Direccion_new, String Contato_new) throws SQLException{
	return clientedao.Actualizar(idCliente, Direccion_new, Contato_new);
}
public ArrayList<Cliente> Listar() throws SQLException{
	return clientedao.Listar();
}

}
