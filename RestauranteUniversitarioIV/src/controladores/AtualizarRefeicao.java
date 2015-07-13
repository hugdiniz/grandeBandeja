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

@WebServlet("/AtualizarRefeicao")
public class AtualizarRefeicao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		String acao = (String) request.getParameter("acaoAtualizar");
		
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
			case "Cancelar":
			case "Voltar":
				request.getRequestDispatcher("ListarRefeicao").forward(request,response);
				break;
			case "Atualizar":
				atualizarRefeicaoAntigo(request,response);
				break;
			default:
				try 
				{
					RefeicaoVO refeicaoAntigo = GerirRefeicao.getInstance().buscarRefeicao(refeicaoVO);
					request.setAttribute("refeicao antigo",refeicaoAntigo);
					request.getRequestDispatcher("WEB-INF/AtualizarRefeicao.jsp").forward(request,response);
				} 
				catch (RefeicaoException e2) 
				{
					request.setAttribute("erro", e2.getMessage());
					request.getRequestDispatcher("WEB-INF/AtualizarRefeicao.jsp").forward(request,response);
				}				
		}
	}
	
	
	private void atualizarRefeicaoAntigo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String descricao = (String) request.getParameter("descricao");
		String op_vegetariana = (String) request.getParameter("op_vegetariana");
		
		if (descricao=="" || op_vegetariana=="")
		{
			request.setAttribute("erro", "Um refeicao deve conter uma descricao e uma opcao vegetariana");
			request.getRequestDispatcher("WEB-INF/AtualizarRefeicao.jsp").forward(request,response);
		}
		else
		{
			try 
			{
				RefeicaoVO refeicaoVO = new RefeicaoVO();
				refeicaoVO.setDescricao(descricao);
				refeicaoVO.setOp_vegetariana(op_vegetariana);
				GerirRefeicao.getInstance().atualizarRefeicao(refeicaoVO);
				request.getRequestDispatcher("ListarRefeicao").forward(request,response);
				
			} 
			catch (RefeicaoException e2)
			{
				request.setAttribute("erro",e2.getMessage());
				request.getRequestDispatcher("WEB-INF/AtualizarRefeicao.jsp").forward(request,response);
			}			
		}
	}
}