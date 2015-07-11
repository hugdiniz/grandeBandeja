package controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controladores.ccu.GerirDepartamento;

@WebServlet("")
public class MenuPrincipal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		initJsp(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		initJsp(request,response);				
		
	}

	private void initJsp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("departamentos", GerirDepartamento.getInstance().listarDepartamentos(request.getSession()));
		request.getRequestDispatcher("WEB-INF/MenuPrincipal.jsp").forward(request,response);
	}

}
