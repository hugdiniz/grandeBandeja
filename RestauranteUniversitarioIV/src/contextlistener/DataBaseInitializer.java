package contextlistener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContextEvent;

import org.h2.tools.RunScript;

import persistencia.ConnectionFactory;

public class DataBaseInitializer {

	private static final String DRIVER_CLASS = "org.h2.Driver";
	//Manter essa conex�o aberta para n�o fechar a conex�o com o database
	//Fechar essa conex�o ao destruir o contexto
	public static Connection rootConection;

	private DataBaseInitializer (){
		super();
	}

	public static void inicializarDb(ServletContextEvent evt) throws ClassNotFoundException, SQLException {
		System.out.println("Registrando driver JDBC");
		Class.forName(DRIVER_CLASS);
		System.out.println("Sucesso");
		System.out.println("Abrindo Conexao");
		rootConection = ConnectionFactory.getConnection();
		System.out.println("Sucesso");
		InputStream is = DataBaseInitializer.class.getResourceAsStream("../sql/schema_create.sql");
		Reader reader = new InputStreamReader(is);
		System.out.println("Executando Script de Criacao do Banco de dados");
		RunScript.execute(rootConection, reader);
		Connection con = ConnectionFactory.getConnection();
		con.createStatement().executeQuery("SELECT 1 FROM CONSUMIDOR");
		System.out.println("Sucesso!");
	}
	public static void inicializarDbDrop(ServletContextEvent evt) throws ClassNotFoundException, SQLException {
		inicializarDb(evt);
		
		InputStream is = DataBaseInitializer.class.getResourceAsStream("../sql/schema_drop.sql");
		Reader reader = new InputStreamReader(is);
		RunScript.execute(rootConection, reader);
		is = DataBaseInitializer.class.getResourceAsStream("../sql/schema_create.sql");
		reader = new InputStreamReader(is);
		RunScript.execute(rootConection, reader);
	}


}
