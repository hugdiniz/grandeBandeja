package controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.ccu.GerirRefeicao;
import controladores.ccu.exceptions.RefeicaoNotFound;
import entidades.exceptions.RefeicaoException;
import entidades.value_objects.RefeicaoVO;


@WebServlet("/VerRefeicao")
public class VerRefeicao extends HttpServlet  {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		String acao = (String) request.getParameter("acaoVer");

		RefeicaoVO refeicaoVO = new RefeicaoVO();

		if (request.getParameter("id") != null)
		{
			Long id = Long.parseLong(request.getParameter("id")); // ANALISAR SE ESTÁ CORRETO
			refeicaoVO.setId(id);
		}

		if (acao == null)
			acao = "";

		switch (acao) 
		{
		case "Voltar":
			request.getRequestDispatcher("ListarRefeicao").forward(request,response);
			break;
		default:
			RefeicaoVO refeicaoAntigo;
			try 
			{
				refeicaoAntigo = GerirRefeicao.getInstance().buscarRefeicao(refeicaoVO);
				request.setAttribute("refeicao antigo",refeicaoAntigo);
				request.getRequestDispatcher("WEB-INF/VerRefeicao.jsp").forward(request,response);
			} catch (RefeicaoException e) 
			{
				request.setAttribute("erro", e.getMessage());
				request.getRequestDispatcher("WEB-INF/VerRefeicao.jsp").forward(request,response);
			}
		}
	}
}