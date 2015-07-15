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
		return RepositorioTicket.getInstance().buscar(ticketvo);		
	}
	
	public TicketVO recuperarTicket(TicketVO ticketvo) throws TicketException 
	{
		Collection ticketsVO = null;
		
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
		
		 RepositorioTicket.getInstance().inserirOuAtualizar(ticketvo);	
	}

	public void atualizarTicket(TicketVO vo) throws TicketException 
	{	
		TicketVO ticketBusca = new TicketVO();
		ticketBusca.setId(vo.getId());
		TicketVO ticketAntigo = recuperarTicket(ticketBusca);
		
		//Verifica se objeto recuperado na base é o mesmo alterado no front-end. Caso não seja, lança erro.
		if (ticketAntigo != null && ticketAntigo.getId() == vo.getId())
		{	
			//RN não pode ter mesmo curso (nome e sigla) 
			throw new TicketException("erro.adiconar.ticket.repositorio.ticket.ja.existe");
		}
		if(vo.getIdConsumidor() == null)
		{
			//RN Não existe curso sem o seu respectivo Departamento.
			throw new TicketException("erro.adicionar.ticket.repositorio.ticket.nao.existe.consumidor");
		}
		if(vo.getIdRefeicao() == null)
		{
			//RN Não existe curso sem o seu respectivo Departamento.
			throw new TicketException("erro.adicionar.ticket.repositorio.ticket.nao.existe.refeicao");
		}
		RepositorioTicket.getInstance().inserirOuAtualizar(vo);	
	}
}
