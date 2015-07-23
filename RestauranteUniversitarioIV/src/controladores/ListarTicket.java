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
import entidades.value_objects.TicketVO;

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
		String idString = request.getParameter("id");
		if (idString != null)
		{
			request.setAttribute("id", idString);
			Long id = Long.parseLong(idString);
			ConsumidorVO consumidorVO = new ConsumidorVO();
			consumidorVO.setId(id);
						
			try
			{
				Collection ticketVOs = GerirTickets.getInstance().buscarTicketConsumidor(consumidorVO);
				request.setAttribute("tickets",ticketVOs);
				
			} 
			catch (TicketException e)
			{				
				e.printStackTrace();
			}			
		}
		
		String atualizar = request.getParameter("atualizar");	
		if (atualizar != null && !atualizar.equals(""))
		{
			String idTicketString = request.getParameter("idTicket");
			Long idTicket = Long.parseLong(idTicketString);
			TicketVO ticketVO = new TicketVO();
			ticketVO.setId(idTicket);
			try
			{
				ticketVO = GerirTickets.getInstance().buscarTicket(ticketVO);
				
				if (request.getParameter("pagoModal") != null)
				{
					if (request.getParameter("pagoModal") != null && request.getParameter("pagoModal").equals("true"))
					{
						ticketVO.setPago(Boolean.TRUE);
					}
					else
					{
						ticketVO.setPago(Boolean.FALSE);
					}					
				}
				
				
				GerirTickets.getInstance().atualizarTicket(ticketVO);
				
				request.setAttribute("id", idString);
				Long id = Long.parseLong(idString);
				ConsumidorVO consumidorVO = new ConsumidorVO();
				consumidorVO.setId(id);
				
				Collection ticketVOs = GerirTickets.getInstance().buscarTicketConsumidor(consumidorVO);
				request.setAttribute("tickets",ticketVOs);
				
			} 
			catch (TicketException e)
			{				
				e.printStackTrace();
			}
		}
				
		
		request.getRequestDispatcher("WEB-INF/ListarTicket.jsp").forward(request,response);

	}
	
	
}
