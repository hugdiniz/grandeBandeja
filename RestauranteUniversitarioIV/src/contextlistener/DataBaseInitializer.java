package contextlistener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import persistencia.ConnectionFactory;

public class DataBaseInitializer {
	
	private static final String DRIVER_CLASS = "org.h2.Driver";
	//Manter essa conexão aberta para não fechar a conexão com o database
	//Fechar essa conexão ao destruir o contexto
	public static Connection rootConection;
	
	private DataBaseInitializer (){
		super();
	}
	
	public static void inicializarDb() throws ClassNotFoundException, SQLException {
		int id = 0;
		System.out.println("Registrando driver JDBC");
		Class.forName(DRIVER_CLASS);
		System.out.println("Sucesso");
	    System.out.println("Abrindo Conexao");
		rootConection = ConnectionFactory.getConnection();
		System.out.println("Sucesso");
	    //TODO - Criar scripts de inicialização do banco
		/*String sql = "CREATE TABLE TESTE " +
                "(id INTEGER not NULL, " +
                " teste VARCHAR(255), " +  
                " PRIMARY KEY ( id ))"; 
	    Statement stmt = rootConection.createStatement();
	    System.out.println("Criando tabela");
	    stmt.executeUpdate(sql);
	    stmt.close();
	    System.out.println("Sucesso");
	    sql = "INSERT INTO TESTE (id, teste) values (?, ?) ";
	    PreparedStatement pstmt = rootConection.prepareStatement(sql);
	    pstmt.setInt(1, id++);
	    pstmt.setString(2, "Testando");
	    System.out.println("Inserindo dados");
	    pstmt.executeUpdate();
	    pstmt.close();
	    System.out.println("Sucesso");
	    stmt = rootConection.createStatement();
	    sql =  "SELECT id, teste FROM TESTE";
	    System.out.println("Executando query");
	    ResultSet rs = stmt.executeQuery(sql);
	    System.out.println("Lendo Resultados");
	    while(rs.next()){
	    	System.out.println("id: " + String.valueOf(rs.getInt("id")));
	    	System.out.println("nome: " + rs.getString("teste"));
	    }
	    System.out.println("Sucesso");
	    stmt.close();*/
	}
	
	
}
