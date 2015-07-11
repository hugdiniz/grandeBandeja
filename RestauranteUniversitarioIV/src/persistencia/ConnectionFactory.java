package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private static final String CONNECTION_URL = "jdbc:h2:~/jerry";
	private static final String USER = "admin";
	
	private ConnectionFactory () {
		
	}
	
	public static Connection getConnection() {
		try {
			return DriverManager.
			         getConnection(CONNECTION_URL, USER, "");
		} catch (SQLException e) {
			throw new RuntimeException("Nao foi possivel abrir uma conexao");
		}
	}
}
