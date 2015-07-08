package controladores;

import java.io.IOException;
import java.util.Collection;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controladores.ccu.GerirCurso;
import controladores.ccu.GerirDepartamento;
import controladores.ccu.exceptions.DepartamentoNotFound;
import controladores.ccu.exceptions.NomeNotFoundException;
import controladores.ccu.exceptions.SiglaAlreadyExistsException;
import controladores.ccu.exceptions.SiglaNotFoundException;
import entidades.Curso;
import entidades.Departamento;
import entidades.value_objects.CursoVO;
import entidades.value_objects.DepartamentoVO;

@WebServlet("/CriarCurso")
public class CriarCurso extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = (String) request.getParameter("acaoCriar");
		Collection<DepartamentoVO> departamentosDisponiveis = GerirDepartamento.listarDepartamentos(request.getSession());
		request.setAttribute("departamentosDisponiveis", departamentosDisponiveis);
		
		if (acao != null){
			switch (acao) {
				case "Criar":
					criarCurso(request,response);
					break;
				default:
					request.getRequestDispatcher("ListarCurso").forward(request,response);
			}
		}else{
			request.getRequestDispatcher("WEB-INF/CriarCurso.jsp").forward(request,response);	
		}
	}

	private void criarCurso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = (String) request.getParameter("nome");
		String sigla = (String) request.getParameter("sigla");
		
		try {
			GerirCurso.criarCurso(request.getSession(), nome, sigla, request.getParameter("departamento"));
			request.setAttribute("message", "Novo departamento criado!");
			request.getRequestDispatcher("ListarCurso").forward(request,response);
		} catch (SiglaNotFoundException | NomeNotFoundException e2) {
			request.setAttribute("erro", "Um curso deve conter um nome, uma sigla e um departamento");
			request.getRequestDispatcher("WEB-INF/CriarCurso.jsp").forward(request,response);
		}catch (SiglaAlreadyExistsException e) {
			request.setAttribute("erro", "Sigla informada já existe");
			request.getRequestDispatcher("WEB-INF/CriarCurso.jsp").forward(request,response);
		}catch (DepartamentoNotFound e) {
			request.setAttribute("erro", "Informe um departamento valido");
			request.getRequestDispatcher("WEB-INF/CriarCurso.jsp").forward(request,response);
		}
		
	}
}