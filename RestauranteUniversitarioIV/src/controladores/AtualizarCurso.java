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
import controladores.ccu.exceptions.CursoNotFound;
import controladores.ccu.exceptions.DepartamentoNotFound;
import entidades.exceptions.CursoException;
import entidades.exceptions.DepartamentoException;
import entidades.value_objects.CursoVO;
import entidades.value_objects.DepartamentoVO;

@WebServlet("/AtualizarCurso")
public class AtualizarCurso extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		String acao = (String) request.getParameter("acaoAtualizar");
		Collection departamentosDisponiveis = null;
		
		try
		{
			departamentosDisponiveis = GerirDepartamento.getInstance().listarDepartamentos();
		}
		catch (DepartamentoException e)
		{			
			e.printStackTrace();
		}
		
		request.setAttribute("departamentosDisponiveis", departamentosDisponiveis);
		
		if (acao == null)
			acao = "";

		switch (acao)
		{
			case "Cancelar":
			case "Voltar":
				request.getRequestDispatcher("ListarCurso").forward(request,response);
				break;
			case "Atualizar":
				atualizarCursoAntigo(request,response);
				break;
			default:
				try
				{
					String nome = (String) request.getParameter("nome");
					String sigla = (String) request.getParameter("sigla");
					
					CursoVO cursoVO = new CursoVO();
					cursoVO.setNome(nome);
					cursoVO.setSigla(sigla);
					
					CursoVO cursoAntigo = GerirCurso.getInstance().buscarCurso(cursoVO);
					request.setAttribute("curso antigo",cursoAntigo);
					request.getRequestDispatcher("WEB-INF/AtualizarCurso.jsp").forward(request,response);
				}
				catch (CursoException e2)
				{
					request.setAttribute("erro", e2.getMessage());
					request.getRequestDispatcher("WEB-INF/AtualizarCurso.jsp").forward(request,response);
				}	
		}
	}
	
	
	private void atualizarCursoAntigo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String nome = (String) request.getParameter("nome");
		String sigla = (String) request.getParameter("sigla");
		
		Long idDepartamento = Long.parseLong(request.getParameter("departamento"));
		
		DepartamentoVO departamentoVO = new DepartamentoVO();
		departamentoVO.setId(idDepartamento);
		
		CursoVO cursoVO = new CursoVO();
		cursoVO.setNome(nome);
		cursoVO.setSigla(sigla);
		cursoVO.setDepartamentoVO(departamentoVO);
		
		if (nome=="" || sigla=="" || request.getParameter("departamento") == null)
		{
			request.setAttribute("erro", "Um curso deve conter um nome, uma sigla e um departamento");
			request.getRequestDispatcher("WEB-INF/AtualizarCurso.jsp").forward(request,response);
		}
		else
		{
			try 
			{
				GerirCurso.getInstance().atualizarCurso(cursoVO);
				request.setAttribute("message", "Novo curso criado!");
				request.getRequestDispatcher("ListarCurso").forward(request,response);
			}
			catch (CursoException e)
			{
				request.setAttribute("erro", e.getMessage());
				request.getRequestDispatcher("WEB-INF/CriarCurso.jsp").forward(request,response);
			} 			
		}
	}
}