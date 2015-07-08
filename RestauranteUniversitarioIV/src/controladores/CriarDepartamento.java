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

@WebServlet("/CriarDepartamento")
public class CriarDepartamento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = (String) request.getParameter("acaoCriar");
		
		if (acao != null){
			switch (acao) {
				case "Criar":
					criarDepartamento(request,response);
					break;
				default:
					request.getRequestDispatcher("ListarDepartamento").forward(request,response);
			}
		}else{
			request.getRequestDispatcher("WEB-INF/CriarDepartamento.jsp").forward(request,response);	
		}
	}

	private void criarDepartamento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = (String) request.getParameter("nome");
		String sigla = (String) request.getParameter("sigla");
		
		try {
			GerirDepartamento.criarDepartamento(request.getSession(), nome, sigla);
			request.setAttribute("message", "Novo departamento criado!");
			request.getRequestDispatcher("ListarDepartamento").forward(request,response);
		} catch (SiglaNotFoundException | NomeNotFoundException e2) {
			request.setAttribute("erro", "Um departamento deve conter um nome e uma sigla");
			request.getRequestDispatcher("WEB-INF/CriarDepartamento.jsp").forward(request,response);
		}catch (SiglaAlreadyExistsException e) {
			request.setAttribute("erro", "Sigla informada já existe");
			request.getRequestDispatcher("WEB-INF/CriarDepartamento.jsp").forward(request,response);
		}		
		
	}

}