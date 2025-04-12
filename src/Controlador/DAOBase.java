package Controlador;

import java.sql.SQLException;
import java.util.ArrayList;

import Modelo.Factura;

public abstract class DAOBase<T> {

	public abstract boolean Guardar(T objeto) throws SQLException;
	public abstract boolean Eliminar(int id) throws SQLException;
	public abstract ArrayList<T> Listar() throws SQLException;
}
