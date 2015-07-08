package controladores;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controladores.ccu.GerirCurso;
import controladores.ccu.GerirDepartamento;
import entidades.Curso;

@WebServlet("/ListarCurso")
public class ListarCurso extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		listarCursos(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = (String) request.getParameter("acaoListar");
		
		if (acao == null) acao = "";
		
		switch (acao) {
			case "Criar":
				request.getRequestDispatcher("CriarCurso").forward(request,response);
				break;
			case "Atualizar":
				request.getRequestDispatcher("AtualizarCurso").forward(request,response);
				break;
			case "Ver":
				request.getRequestDispatcher("VerCurso").forward(request,response);
				break;
			case "":
			default:
				listarCursos(request,response);				
		}
	}

	
	private void listarCursos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("cursos", GerirCurso.listarCursos(request.getSession()));
		request.getRequestDispatcher("WEB-INF/ListarCurso.jsp").forward(request,response);
	}

}
