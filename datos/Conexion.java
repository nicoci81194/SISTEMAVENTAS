package datos;

import java.sql.*;

public class Conexion {
	
	private static final String URL = "jdbc:mysql://localhost:3306/sistema_ventas";
	private static final String Usuario = "root";	
	private static final String Clave = "mysql";
	
	public Connection conectar() {
		Connection link=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			link = DriverManager.getConnection(URL, Usuario, Clave);
			return link;
		}catch(ClassNotFoundException | SQLException e) {
			System.out.println("ERROR CONECCION:" + e.getMessage());
		}
		return link;
	}
	
	
}
