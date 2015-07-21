package controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.ccu.GerirDepartamento;
import controladores.ccu.exceptions.NomeNotFoundException;
import controladores.ccu.exceptions.SiglaAlreadyExistsException;
import controladores.ccu.exceptions.SiglaNotFoundException;
import entidades.exceptions.DepartamentoException;
import entidades.value_objects.DepartamentoVO;

@WebServlet("/CriarDepartamento")
public class CriarDepartamento extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/CriarDepartamento.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String acao = (String) request.getParameter("acaoCriar");
		
		if (acao != null)
		{
			switch (acao) 
			{
				case "Criar":
					criarDepartamento(request,response);
					break;
				default:
					request.getRequestDispatcher("ListarDepartamento").forward(request,response);
			}
		}
		else
		{
			request.getRequestDispatcher("WEB-INF/CriarDepartamento.jsp").forward(request,response);	
		}
	}

	private void criarDepartamento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String nome = (String) request.getParameter("nome");
		String sigla = (String) request.getParameter("sigla");
		
		DepartamentoVO departamentoVO = new DepartamentoVO();
		
		departamentoVO.setNome(nome);
		departamentoVO.setSigla(sigla);
		
		
		try 
		{
			GerirDepartamento.getInstance().criarDepartamento(departamentoVO);
			request.setAttribute("message", "Novo departamento criado!");
			request.getRequestDispatcher("ListarDepartamento").forward(request,response);
		} 
		catch (DepartamentoException e2)
		{
			request.setAttribute("erro", e2.getMessage());
			request.getRequestDispatcher("WEB-INF/CriarDepartamento.jsp").forward(request,response);
		}		
		
	}

}