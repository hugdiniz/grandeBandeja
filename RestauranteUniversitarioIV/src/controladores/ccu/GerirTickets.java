package controladores.ccu;

import java.util.Collection;

import entidades.Consumidor;
import entidades.Curso;
import entidades.Departamento;
import entidades.Refeicao;
import entidades.Ticket;
import entidades.exceptions.ConsumidorException;
import entidades.exceptions.CursoException;
import entidades.exceptions.DepartamentoException;
import entidades.exceptions.RefeicaoException;
import entidades.exceptions.TicketException;
import entidades.value_objects.ConsumidorVO;
import entidades.value_objects.CursoVO;
import entidades.value_objects.DepartamentoVO;
import entidades.value_objects.RefeicaoVO;
import entidades.value_objects.TicketVO;

public class GerirTickets
{
	private GerirTickets()
	{
		
	}
	
	private static GerirTickets gerirTickets;
	public static GerirTickets getInstance()
	{
		if (gerirTickets == null)
		{
			gerirTickets = new GerirTickets();
		}
		return gerirTickets;
	}
	
	/**
	 * 
	 * @param consumidorVO
	 * @return Collection de tickets VOs de um consumidor
	 * @throws ConsumidorException
	 * @throws TicketException 
	 */
	public Collection buscarTicketConsumidor(ConsumidorVO consumidorVO) throws TicketException
	{
		consumidorVO.setHabilitado(Boolean.TRUE);
		TicketVO ticketVO = new TicketVO();
		ticketVO.setConsumidorVO(consumidorVO);
		
		Collection ticketVOs = Ticket.getInstance().recuperarTickets(ticketVO);		
		
		return ticketVOs;
	}
	public TicketVO buscarTicket(TicketVO ticketVO) throws TicketException
	{		
		TicketVO ticketVOSaida = Ticket.getInstance().recuperarTicket(ticketVO);		
		
		return ticketVOSaida;
	}
	
	public ConsumidorVO buscarConsumidor(ConsumidorVO consumidorVO) throws ConsumidorException
	{
		consumidorVO.setHabilitado(Boolean.TRUE);
		
		ConsumidorVO consumidorVOSaida = Consumidor.getInstance().recuperarConsumidor(consumidorVO);		
		
		return consumidorVOSaida;
	}
	public Collection listarRefeicao() throws RefeicaoException
	{
		RefeicaoVO refeicaoVO = new RefeicaoVO();		
		
		Collection refeicaoVOSaida = Refeicao.getInstance().recuperarRefeicoes(refeicaoVO);		
		
		return refeicaoVOSaida;
	}
	public void adicionarTicket(TicketVO ticketVO) throws TicketException
	{
		Ticket.getInstance().adicionarTicket(ticketVO);
	}
	public void atualizarTicket(TicketVO ticketVO) throws TicketException
	{
		Ticket.getInstance().atualizarTicket(ticketVO);
	}
}
