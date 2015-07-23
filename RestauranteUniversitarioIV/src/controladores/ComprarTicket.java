package controladores;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controladores.ccu.GerirTickets;
import entidades.enumerados.Turno;
import entidades.exceptions.ConsumidorException;
import entidades.exceptions.RefeicaoException;
import entidades.exceptions.TicketException;
import entidades.value_objects.ConsumidorVO;

@WebServlet("/ComprarTicket")
public class ComprarTicket extends HttpServlet 
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
				Collection ticketVOs = GerirTickets.getInstance().buscarTicketConsumidor(consumidorVO);
				Collection refeicaoVOs = GerirTickets.getInstance().listarRefeicao();
				consumidorVO = GerirTickets.getInstance().buscarConsumidor(consumidorVO);
				
				request.setAttribute("turnoNomes", Turno.names());
				request.setAttribute("consumidor",consumidorVO);
				request.setAttribute("refeicaos",refeicaoVOs);
				request.setAttribute("tickets",ticketVOs);
			}			
			catch (TicketException | ConsumidorException | RefeicaoException e)
			{
				request.setAttribute("erro",e.getMessage());
				e.printStackTrace();
			}
						
		}		
		request.getRequestDispatcher("WEB-INF/ComprarTicket.jsp").forward(request,response);

	}
	
	
}
