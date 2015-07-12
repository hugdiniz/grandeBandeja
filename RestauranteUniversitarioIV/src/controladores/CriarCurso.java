package controladores;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.ccu.GerirCurso;
import controladores.ccu.GerirDepartamento;
import controladores.ccu.exceptions.DepartamentoNotFound;
import controladores.ccu.exceptions.NomeNotFoundException;
import controladores.ccu.exceptions.SiglaAlreadyExistsException;
import controladores.ccu.exceptions.SiglaNotFoundException;
import entidades.exceptions.CursoException;
import entidades.exceptions.DepartamentoException;
import entidades.value_objects.CursoVO;
import entidades.value_objects.DepartamentoVO;

@WebServlet("/CriarCurso")
public class CriarCurso extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = (String) request.getParameter("acaoCriar");
		Collection departamentosDisponiveis = null;
		
		try
		{
			departamentosDisponiveis = GerirDepartamento.getInstance().listarDepartamentos();
		}
		catch (DepartamentoException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("departamentosDisponiveis", departamentosDisponiveis);
		
		if (acao != null)
		{
			switch (acao) 
			{
				case "Criar":
					criarCurso(request,response);
					break;
				default:
					request.getRequestDispatcher("ListarCurso").forward(request,response);
			}
		}
		else
		{
			request.getRequestDispatcher("WEB-INF/CriarCurso.jsp").forward(request,response);	
		}
	}

	private void criarCurso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String nome = (String) request.getParameter("nome");
		String sigla = (String) request.getParameter("sigla");
		
		CursoVO cursoVO = new CursoVO();
		cursoVO.setNome(nome);
		cursoVO.setSigla(sigla);
		
		try 
		{
			GerirCurso.getInstance().criarCurso(cursoVO);
			request.setAttribute("message", "Novo departamento criado!");
			request.getRequestDispatcher("ListarCurso").forward(request,response);
		} 
		catch (CursoException e2) 
		{
			request.setAttribute("erro", e2.getMessage());
			request.getRequestDispatcher("WEB-INF/CriarCurso.jsp").forward(request,response);		
		}
		
	}
}