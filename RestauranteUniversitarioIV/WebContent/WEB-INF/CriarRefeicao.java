package controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.ccu.GerirRefeicao;
import entidades.exceptions.RefeicaoException;
import entidades.value_objects.RefeicaoVO;
import entidades.enumerados.Turno;

@WebServlet("/CriarRefeicao")
public class CriarRefeicao extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String acao = (String) request.getParameter("acaoCriar");
		
		if (acao != null)
		{
			switch (acao) 
			{
				case "Criar":
					criarRefeicao(request,response);
					break;
				default:
					request.getRequestDispatcher("ListarRefeicao").forward(request,response);
			}
		}
		else
		{
			request.getRequestDispatcher("WEB-INF/CriarRefeicao.jsp").forward(request,response);	
		}
	}

	private void criarRefeicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String turno = (String) request.getParameter("turno");
		String descricao = (String) request.getParameter("descricao");
		String op_vegetariana = (String) request.getParameter("op_vegetariana");
	
		if (descricao=="" || op_vegetariana=="" || turno=="")
		{
			request.setAttribute("erro", "Um refeicao deve conter uma descricao e uma opcao vegetariana");
			request.getRequestDispatcher("WEB-INF/AtualizarRefeicao.jsp").forward(request,response);
		}
		else
		{
			RefeicaoVO refeicaoVO = new RefeicaoVO();
			
			refeicaoVO.setTurno(Turno.valueOf(turno));
			refeicaoVO.setDescricao(descricao);
			refeicaoVO.setOp_vegetariana(op_vegetariana);
			
			try 
			{
				GerirRefeicao.getInstance().criarRefeicao(refeicaoVO);
				request.setAttribute("message", "Nova refeicao criada!");
				request.getRequestDispatcher("ListarRefeicao").forward(request,response);
			} 
			catch (RefeicaoException e2)
			{
				request.setAttribute("erro", e2.getMessage());
				request.getRequestDispatcher("WEB-INF/CriarRefeicao.jsp").forward(request,response);
			}
		}
		
	}

}