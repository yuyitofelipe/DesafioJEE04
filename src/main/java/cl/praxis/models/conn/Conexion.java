package cl.praxis.models.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
	private static Connection con = null;
	
	private Conexion() {

		try {
			Class.forName("org.postgresql.Driver");
			
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/proveedores","postgres","1234");
			boolean isValid = con.isValid(50000);
			System.out.println(isValid ? " CONN POSTGRESQL OK ": " CONN POSTGRESQL FAILED ");
		} catch(ClassNotFoundException | SQLException ex){
			System.out.println("Error de conexión con la base de datos: " + ex.getMessage());
		}
	}
	public static Connection getCon() {
		if (con == null) {
			new Conexion();
		}
		return con;
	}
}