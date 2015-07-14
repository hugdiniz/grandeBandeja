package controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.ccu.GerirDepartamento;
import entidades.value_objects.ConsumidorVO;
import entidades.value_objects.DepartamentoVO;

@WebServlet("/CriarConsumidor")
public class CriarConsumidor extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		initJsp(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		initJsp(request,response);				
		
	}

	private void initJsp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String nome = (String) request.getParameter("nome");
		String matricula = (String) request.getParameter("matricula");
		String anoIngresso = (String) request.getParameter("anoIngresso");
		String sexo = (String) request.getParameter("sexo");
		String titulo = (String) request.getParameter("titulo");
		String cpf = (String) request.getParameter("cpf");
		
		ConsumidorVO consumidorVO = new ConsumidorVO();
		
		consumidorVO.setNome(nome);
		consumidorVO.setMatricula(matricula);
		consumidorVO.setCpf(cpf);
		consumidorVO.setAnoIngresso(anoIngresso);
		request.setAttribute("message", "Novo departamento criado!");
		//request.setAttribute("departamentos", GerirDepartamento.getInstance().listarDepartamentos(request.getSession()));
		request.getRequestDispatcher("WEB-INF/CriarConsumidor.jsp").forward(request,response);
	}

}
