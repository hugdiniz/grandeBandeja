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
			throw new RuntimeException("Impossivel fechar conex�es!");
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
			try {
				DataBaseInitializer.inicializarDb();
			} catch (ClassNotFoundException | SQLException e) {
				throw new java.lang.RuntimeException("Exce��o n�o tratada!", e);
			}
	}

}
