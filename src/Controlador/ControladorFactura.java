package Controlador;

import java.sql.SQLException;
import java.util.ArrayList;

import Modelo.Factura;;

public class ControladorFactura {
		private FacturaDAO facturadao;	
		
		public ControladorFactura() throws SQLException {
			this.facturadao = new FacturaDAO();
		}
		
		public boolean Guardar(Factura factura) throws SQLException {
			return facturadao.Guardar(factura);
			
		}
		public boolean Eliminar(int id) throws SQLException {
			return facturadao.Eliminar(id);
		}
		public ArrayList<Factura> Listar() throws SQLException{
			return facturadao.Listar();
		}
	
}
