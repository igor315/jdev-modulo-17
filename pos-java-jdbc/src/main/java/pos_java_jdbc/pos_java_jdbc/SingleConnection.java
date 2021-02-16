package pos_java_jdbc.pos_java_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {

	// Cria a classe define os atributos e os métodos;
	
	private static String url = "jdbc:postgresql://localhost:5432/posjava";
	private static String password = "admin";
	private static String user ="postgres";
	private static Connection connection = null;
	
	
	//construtor
	static {
		conectar();
	}
	
	public SingleConnection() {
		
	}

	private static void conectar() {
		try {
			
			if(connection == null) {
				Class.forName("org.postgresql.Driver"); //precisamos carregador o driver do postgres
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false);
				System.out.println("Conectou com Sucesso");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		return connection;
	}
}
