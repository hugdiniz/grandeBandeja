package entidades;

import java.sql.SQLException;
import java.util.Collection;

import entidades.exceptions.TicketException;
import entidades.value_objects.TicketVO;
import persistencia.RepositorioTicket;

public class Ticket {

	private static Ticket ticket;
	
	private Ticket()
	{
		
	}
	
	public static Ticket getInstance() 
	{
		if (ticket == null) 
		{
			ticket = new Ticket();
		}
		return ticket;

	}
	public Collection recuperarTickets(TicketVO ticketvo) throws TicketException
	{		
		try
		{
			return RepositorioTicket.getInstance().buscar(ticketvo);
		}
		catch (SQLException e)
		{			
			e.printStackTrace();
			throw new TicketException("erro.recuperar.tickets.repositorio.buscar");
		}		
	}
	
	public TicketVO recuperarTicket(TicketVO ticketvo) throws TicketException 
	{
		Collection ticketsVO = null;
		
		try
		{
			ticketsVO =  RepositorioTicket.getInstance().buscar(ticketvo);
			
			if (ticketsVO != null && !ticketsVO.isEmpty())
			{
				return (TicketVO) ticketsVO.iterator().next();
			}
			else
			{
				return null;
			}	
		}
		catch (SQLException e)
		{			
			e.printStackTrace();
			throw new TicketException("erro.recuperar.ticket.repositorio.buscar");
		}
		
		
	}
	
	@SuppressWarnings("null")
	public void adicionarTicket(TicketVO ticketvo) throws TicketException 
	{
		TicketVO ticketBusca = new TicketVO();
		ticketBusca.setId(ticketvo.getId());
		TicketVO ticketAntigo = recuperarTicket(ticketBusca);
		
		
		if (ticketAntigo != null && ticketAntigo.getId() == ticketvo.getId())
		{
			//RN não pode ter mesmo curso (nome e sigla) 
			throw new TicketException("erro.adiconar.ticket.repositorio.ticket.ja.existe");
		}
		if(ticketvo.getIdConsumidor() == null)
		{
			//RN Não existe curso sem o seu respectivo Departamento.
			throw new TicketException("erro.adicionar.ticket.repositorio.ticket.nao.existe.consumidor");
		}
		if(ticketvo.getIdRefeicao() == null)
		{
			//RN Não existe curso sem o seu respectivo Departamento.
			throw new TicketException("erro.adicionar.ticket.repositorio.ticket.nao.existe.refeicao");
		}
		
		 try
		{
			RepositorioTicket.getInstance().inserirOuAtualizar(ticketvo);
		}
		catch (SQLException e)
		{			
			e.printStackTrace();
			throw new TicketException("erro.adiconar.ticket.repositorio.inserirOuAtualizar");
		}	
	}

	public void atualizarTicket(TicketVO vo) throws TicketException 
	{	
		TicketVO ticketBusca = new TicketVO();
		ticketBusca.setId(vo.getId());
		TicketVO ticketAntigo = recuperarTicket(ticketBusca);
		
		ticketAntigo.setPago(vo.getPago());
		if(ticketAntigo.getConsumidorVO() == null || ticketAntigo.getConsumidorVO().getId() == null)
		{
			//RN Não existe curso sem o seu respectivo Departamento.
			throw new TicketException("erro.adicionar.ticket.repositorio.ticket.nao.existe.consumidor");
		}
		if(ticketAntigo.getRefeicaoVO() == null || ticketAntigo.getRefeicaoVO().getId() == null)
		{
			//RN Não existe curso sem o seu respectivo Departamento.
			throw new TicketException("erro.adicionar.ticket.repositorio.ticket.nao.existe.refeicao");
		}
		try
		{
			RepositorioTicket.getInstance().inserirOuAtualizar(ticketAntigo);
		}
		catch (SQLException e)
		{			
			e.printStackTrace();
			throw new TicketException("erro.atualizar.ticket.repositorio.inserirOuAtualizar");
		}	
	}
}
