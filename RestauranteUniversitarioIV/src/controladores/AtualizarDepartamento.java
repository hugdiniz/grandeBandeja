package controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.ccu.GerirDepartamento;
import controladores.ccu.exceptions.DepartamentoNotFound;
import entidades.value_objects.DepartamentoVO;

@WebServlet("/AtualizarDepartamento")
public class AtualizarDepartamento extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String acao = (String) request.getParameter("acaoAtualizar");
		if (acao == null)
			acao = "";

		switch (acao) {
			case "Cancelar":
			case "Voltar":
				request.getRequestDispatcher("ListarDepartamento").forward(request,response);
				break;
			case "Atualizar":
				atualizarDepartamentoAntigo(request,response);
				break;
			default:
				try {
					DepartamentoVO departamentoAntigo = GerirDepartamento.buscarDepartamento(request.getSession(),request.getParameter("sigla"));
					request.setAttribute("departamento antigo",departamentoAntigo);
					request.getRequestDispatcher("WEB-INF/AtualizarDepartamento.jsp").forward(request,response);
				} catch (DepartamentoNotFound e2) {
					request.setAttribute("erro", "O departamento informado nao existe");
					request.getRequestDispatcher("WEB-INF/AtualizarDepartamento.jsp").forward(request,response);
				}				
		}
	}
	
	
	private void atualizarDepartamentoAntigo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = (String) request.getParameter("nome");
		String sigla = (String) request.getParameter("sigla");
		
		if (nome=="" || sigla==""){
			request.setAttribute("erro", "Um departamento deve conter um nome e uma sigla");
			request.getRequestDispatcher("WEB-INF/AtualizarDepartamento.jsp").forward(request,response);
		}else{
			try {
				GerirDepartamento.atualizarDepartamento(request.getSession(), nome, sigla);
				request.getRequestDispatcher("ListarDepartamento").forward(request,response);
				
			} catch (DepartamentoNotFound e2) {
				request.setAttribute("erro", "O departamento informado nao existe");
				request.getRequestDispatcher("WEB-INF/AtualizarDepartamento.jsp").forward(request,response);
			}			
		}
	}
}