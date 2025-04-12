package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Databaseconnection {
private static Databaseconnection instancia;
private Connection conexion;
private final String URL = "jdbc:mysql://localhost:3306/sis_facturacion";
private final String USUARIO = "root";
private final String CONTRASEÑA = "123456";

private Databaseconnection() throws SQLException {
	try {
		conexion = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
		System.out.println("✅ Conexión establecida");
		
		 Runtime.getRuntime().addShutdownHook(new Thread(() -> cerrarConexion()));
	}catch(SQLException e) {
		throw new SQLException("Error al conectar con la base de datos",e);
	}
}

public static Databaseconnection getInstancia() throws SQLException {
	if(instancia == null) {
		instancia = new Databaseconnection();
	}
	return instancia;
}

public Connection getConnection() {
	return conexion;
}

public void cerrarConexion() {
    try {
        if (conexion != null) {
            conexion.close();
            System.out.println("✅ Conexión cerrada");
        }
    } catch (SQLException e) {
         e.printStackTrace();
    }
}

}
