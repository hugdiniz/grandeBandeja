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

import controladores.ccu.GerirConsumidor;
import controladores.ccu.GerirTickets;
import entidades.Aluno;
import entidades.CPF;
import entidades.Consumidor;
import entidades.Funcionario;
import entidades.exceptions.ConsumidorException;
import entidades.exceptions.CursoException;
import entidades.exceptions.DepartamentoException;
import entidades.value_objects.ConsumidorVO;
import entidades.value_objects.CursoVO;
import entidades.value_objects.DepartamentoVO;

@WebServlet("/ListarTicket")
public class ListarTicket extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException
	{
		action(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		action(request, response);
	}
	
	
	
	private void action(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String matricula = request.getParameter("matricula");		
		if (matricula != null && !matricula.equals(""))
		{
			ConsumidorVO consumidorVO = new ConsumidorVO();
			consumidorVO.setMatricula(matricula);
			
			try
			{
				consumidorVO = GerirTickets.getInstance().buscarConsumidor(consumidorVO);
			}
			catch (ConsumidorException e)
			{				
				e.printStackTrace();
			}
			request.setAttribute("consumidor",consumidorVO);			
		}
		
		request.getRequestDispatcher("WEB-INF/ListarTicket.jsp").forward(request,response);

	}
	
	
}
