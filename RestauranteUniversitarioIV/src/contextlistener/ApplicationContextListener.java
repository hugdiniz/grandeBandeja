package contextlistener;

import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("Finalizando Tomcat");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
			try {
				DataBaseInitializer.inicializarDb();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Tratar ou logar exceção
				throw new java.lang.RuntimeException("Exceção não tratada!", e);
			}
	}

}
