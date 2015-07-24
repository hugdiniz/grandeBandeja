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
		String matriculaFinal = request.getParameter("matriculaFinal");
		if ((matriculaFinal != null && !matriculaFinal.equals("")) || matricula != null && !matricula.equals(""))
		{
			if (matriculaFinal != null && !matriculaFinal.equals(""))
			{
				matricula = matriculaFinal;
			}
			
			ConsumidorVO consumidorVO = new ConsumidorVO();
			consumidorVO.setMatricula(matricula);
			
			try
			{
				Collection ticketVOs = GerirTickets.getInstance().buscarTicketConsumidor(consumidorVO);
				Collection refeicaoVOs = GerirTickets.getInstance().listarRefeicao();
				consumidorVO = GerirTickets.getInstance().buscarConsumidor(consumidorVO);
				
				if (consumidorVO.getCursoVO() != null && consumidorVO.getCursoVO().getId() != null)
				{
					request.setAttribute("valors", Turno.valoresAluno().toArray());
				}
				else
				{
					request.setAttribute("valors", Turno.valoresFuncionario().toArray());
				}	
				request.setAttribute("qtdTurno", Turno.names().size());
				request.setAttribute("turnos", Turno.names().toArray());
				request.setAttribute("consumidor",consumidorVO);
				request.setAttribute("refeicaos",refeicaoVOs);
				request.setAttribute("tickets",ticketVOs);
			}			
			catch (TicketException | ConsumidorException | RefeicaoException e)
			{
				request.setAttribute("erro",e.getMessage());
				e.printStackTrace();
			}
			
			String comprar = request.getParameter("comprar");	
			if (comprar != null && !comprar.equals("") && request.getParameter("refeicao") != null && !request.getParameter("refeicao").equals(""))
			{
				TicketVO ticketVO = new TicketVO();
				
				if (request.getParameter("refeicao") != null)
				{
					ticketVO.setIdRefeicao((Long.parseLong(request.getParameter("refeicao"))));
				}
				if (request.getParameter("pago") != null)
				{
					if (request.getParameter("pago") != null && request.getParameter("pago").equals("on"))
					{
						ticketVO.setPago(Boolean.TRUE);
					}
					else
					{
						ticketVO.setPago(Boolean.FALSE);
					}					
				}
				ticketVO.setIdConsumidor(consumidorVO.getId());	
				
				try
				{
					GerirTickets.getInstance().adicionarTicket(ticketVO);
				} 
				catch (TicketException e)
				{
					request.setAttribute("erro",e.getMessage());
					e.printStackTrace();
				}
			}
			else if( request.getParameter("refeicao") == null || request.getParameter("refeicao").equals(""))
			{
				request.setAttribute("erro","Refeicao nao selecionada");				
			}			
		}
		
		
		
		request.getRequestDispatcher("WEB-INF/ComprarTicket.jsp").forward(request,response);

	}
	
	
}
