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

import controladores.ccu.GerirRefeicao;
import entidades.Refeicao;
import entidades.exceptions.RefeicaoException;
import entidades.value_objects.RefeicaoVO;

@WebServlet("/ListarRefeicao")
public class ListarRefeicao  extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		listarRefeicoes(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		String acao = (String) request.getParameter("acaoListar");

		if (acao == null) acao = "";

		switch (acao) 
		{
		case "Criar":
			request.getRequestDispatcher("CriarRefeicao").forward(request,response);

			break;
		case "Atualizar":
			request.getRequestDispatcher("AtualizarRefeicao").forward(request,response);
			break;
		case "Ver":
			request.getRequestDispatcher("VerRefeicao").forward(request,response);
			break;
		case "":
		default:
			listarRefeicoes(request,response);				
		}
	}

	private void listarRefeicoes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			request.setAttribute("refeicoes", GerirRefeicao.getInstance().listarRefeicoes());			
		}
		catch (RefeicaoException e)
		{			
			e.printStackTrace();
		}

		request.getRequestDispatcher("WEB-INF/ListarRefeicao.jsp").forward(request,response);		
	}

}

