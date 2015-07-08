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
import controladores.ccu.exceptions.NomeNotFoundException;
import controladores.ccu.exceptions.SiglaAlreadyExistsException;
import controladores.ccu.exceptions.SiglaNotFoundException;
import entidades.Curso;
import entidades.Departamento;
import entidades.value_objects.CursoVO;
import entidades.value_objects.DepartamentoVO;

@WebServlet("/AtualizarCurso")
public class AtualizarCurso extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String acao = (String) request.getParameter("acaoAtualizar");
		Collection<DepartamentoVO> departamentosDisponiveis = GerirDepartamento.listarDepartamentos(request.getSession());
		request.setAttribute("departamentosDisponiveis", departamentosDisponiveis);
		
		if (acao == null)
			acao = "";

		switch (acao) {
			case "Cancelar":
			case "Voltar":
				request.getRequestDispatcher("ListarCurso").forward(request,response);
				break;
			case "Atualizar":
				atualizarCursoAntigo(request,response);
				break;
			default:
				try {
					CursoVO cursoAntigo = GerirCurso.buscarCurso(request.getSession(),request.getParameter("sigla"));
					request.setAttribute("curso antigo",cursoAntigo);
					request.getRequestDispatcher("WEB-INF/AtualizarCurso.jsp").forward(request,response);
				} catch (CursoNotFound e2) {
					request.setAttribute("erro", "O curso informado nao existe");
					request.getRequestDispatcher("WEB-INF/AtualizarCurso.jsp").forward(request,response);
				}	
		}
	}
	
	
	private void atualizarCursoAntigo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = (String) request.getParameter("nome");
		String sigla = (String) request.getParameter("sigla");
		
		if (nome=="" || sigla=="" || request.getParameter("departamento") == null){
			request.setAttribute("erro", "Um curso deve conter um nome, uma sigla e um departamento");
			request.getRequestDispatcher("WEB-INF/AtualizarCurso.jsp").forward(request,response);
		}else{
			try {
				GerirCurso.atualizarCurso(request.getSession(), nome, sigla, request.getParameter("departamento"));
				request.setAttribute("message", "Novo curso criado!");
				request.getRequestDispatcher("ListarCurso").forward(request,response);
			}catch (DepartamentoNotFound e) {
				request.setAttribute("erro", "Informe um departamento valido");
				request.getRequestDispatcher("WEB-INF/CriarCurso.jsp").forward(request,response);
			} catch (CursoNotFound e) {
				request.setAttribute("erro", "O curso informado nao existe");
				request.getRequestDispatcher("WEB-INF/CriarCurso.jsp").forward(request,response);
			}			
		}
	}
}