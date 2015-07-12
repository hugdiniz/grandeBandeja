package controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.ccu.GerirDepartamento;
import controladores.ccu.exceptions.DepartamentoNotFound;
import entidades.exceptions.DepartamentoException;
import entidades.value_objects.DepartamentoVO;

@WebServlet("/AtualizarDepartamento")
public class AtualizarDepartamento extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		String acao = (String) request.getParameter("acaoAtualizar");
		
		DepartamentoVO departamentoVO = new DepartamentoVO();
		if (request.getParameter("sigla") != null)
		{
			departamentoVO.setSigla(request.getParameter("sigla"));
		}
		
		if (acao == null)
			acao = "";

		switch (acao)
		{
			case "Cancelar":
			case "Voltar":
				request.getRequestDispatcher("ListarDepartamento").forward(request,response);
				break;
			case "Atualizar":
				atualizarDepartamentoAntigo(request,response);
				break;
			default:
				try 
				{
					DepartamentoVO departamentoAntigo = GerirDepartamento.getInstance().buscarDepartamento(departamentoVO);
					request.setAttribute("departamento antigo",departamentoAntigo);
					request.getRequestDispatcher("WEB-INF/AtualizarDepartamento.jsp").forward(request,response);
				} 
				catch (DepartamentoException e2) 
				{
					request.setAttribute("erro", e2.getMessage());
					request.getRequestDispatcher("WEB-INF/AtualizarDepartamento.jsp").forward(request,response);
				}				
		}
	}
	
	
	private void atualizarDepartamentoAntigo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String nome = (String) request.getParameter("nome");
		String sigla = (String) request.getParameter("sigla");
		
		if (nome=="" || sigla=="")
		{
			request.setAttribute("erro", "Um departamento deve conter um nome e uma sigla");
			request.getRequestDispatcher("WEB-INF/AtualizarDepartamento.jsp").forward(request,response);
		}
		else
		{
			try 
			{
				DepartamentoVO departamentoVO = new DepartamentoVO();
				departamentoVO.setNome(nome);
				departamentoVO.setSigla(sigla);
				GerirDepartamento.getInstance().atualizarDepartamento(departamentoVO);
				request.getRequestDispatcher("ListarDepartamento").forward(request,response);
				
			} 
			catch (DepartamentoException e2)
			{
				request.setAttribute("erro",e2.getMessage());
				request.getRequestDispatcher("WEB-INF/AtualizarDepartamento.jsp").forward(request,response);
			}			
		}
	}
}