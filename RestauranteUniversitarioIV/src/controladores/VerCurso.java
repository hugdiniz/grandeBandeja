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
import entidades.Curso;
import entidades.exceptions.CursoException;
import entidades.value_objects.CursoVO;
import entidades.value_objects.DepartamentoVO;

@WebServlet("/VerCurso")
public class VerCurso extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		String acao = (String) request.getParameter("acaoVer");
		if (acao == null)
			acao = "";

		CursoVO cursoVO = new CursoVO();
		
		if (request.getParameter("sigla") != null)
		{
			cursoVO.setSigla(request.getParameter("sigla"));
		}
		
		switch (acao) 
		{
			case "Voltar":
				request.getRequestDispatcher("ListarCurso").forward(request,response);
				break;
			default:
				CursoVO cursoAntigo;
				try 
				{
					cursoAntigo = GerirCurso.getInstance().buscarCurso(cursoVO);
					request.setAttribute("curso antigo",cursoAntigo);
					request.getRequestDispatcher("WEB-INF/VerCurso.jsp").forward(request,response);
				} 
				catch (CursoException e) 
				{
					request.setAttribute("erro", e.getMessage());
					request.getRequestDispatcher("WEB-INF/VerCurso.jsp").forward(request,response);
				}				
		}
	}
}