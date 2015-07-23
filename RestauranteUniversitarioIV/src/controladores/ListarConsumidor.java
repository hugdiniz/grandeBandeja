package controladores;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controladores.ccu.GerirConsumidor;
import entidades.Aluno;
import entidades.CPF;
import entidades.Consumidor;
import entidades.Curso;
import entidades.Departamento;
import entidades.Funcionario;
import entidades.exceptions.ConsumidorException;
import entidades.value_objects.ConsumidorVO;
import entidades.value_objects.CursoVO;
import entidades.value_objects.DepartamentoVO;

@WebServlet("/ListarConsumidor")
public class ListarConsumidor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String acao = (String) request.getParameter("acaoListar");
		
		if (acao == null) acao = "";
		
		switch (acao) {
			case "Criar":
				request.getRequestDispatcher("CriarConsumidor").forward(request,response);
				break;
			case "Atualizar":
				request.getRequestDispatcher("AtualizarConsumidor").forward(request,response);
				break;
			case "Excluir":
				request.getRequestDispatcher("ExcluirConsumidor").forward(request,response);
				break;
			case "":
			default:
			try
			{
				listarConsumidores(request,response);
			} catch (ConsumidorException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = (String) request.getParameter("acaoListar");
		
		if (acao == null) acao = "";
		
		switch (acao)
		{
			case "Criar":
				request.getRequestDispatcher("CriarConsumidor").forward(request,response);
				break;
			case "Atualizar":
				request.getRequestDispatcher("AtualizarConsumidor").forward(request,response);
				break;
			case "Excluir":
				request.getRequestDispatcher("ExcluirConsumidor").forward(request,response);
				try
				{
					listarConsumidores(request,response);
				} catch (ConsumidorException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case "":
			default:
			try
			{
				listarConsumidores(request,response);
			} catch (ConsumidorException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}
	}
	
	

	private void listarConsumidores(HttpServletRequest request, HttpServletResponse response) throws ConsumidorException, ServletException, IOException  
	{
		request.setAttribute("consumidors",GerirConsumidor.getInstance().listarConsumidors());
		request.getRequestDispatcher("WEB-INF/ListarConsumidor.jsp").forward(request,response);
	}

	// metodos de persistencia de Consumidor


}
