package contextlistener;

import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
		System.out.println("Finalizando Tomcat");
		try {
			DataBaseInitializer.rootConection.close();
		} catch (SQLException e) {
			throw new RuntimeException("Impossivel fechar conexoes!");
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
			try {
				DataBaseInitializer.inicializarDb(arg0);
			} catch (ClassNotFoundException | SQLException e) {
				throw new java.lang.RuntimeException("Excesao nao tratada!", e);
			}
	}
	

}
