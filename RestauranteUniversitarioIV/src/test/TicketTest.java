package test;

import static org.junit.Assert.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;

import org.junit.Test;

import persistencia.ConnectionFactory;
import entidades.Consumidor;
import entidades.Ticket;
import entidades.enumerados.SexoEnum;
import entidades.enumerados.TituloEnum;
import entidades.enumerados.Turno;
import entidades.exceptions.TicketException;
import entidades.value_objects.ConsumidorVO;
import entidades.value_objects.RefeicaoVO;
import entidades.value_objects.TicketVO;

public class TicketTest {

	@Test
	public void testGetInstance() {
	
		assertNotNull(Ticket.getInstance());
	}
	
	@Test
	public void testRecuperarTickets() throws TicketException {
		
		TicketVO ticketVO = new TicketVO();
		Collection  tickets = Ticket.getInstance().recuperarTickets(ticketVO);
		assertNotNull(tickets);
	}
	
	
	@Test
	public void testAdicionarTicket() throws TicketException {
		ConsumidorVO consumidorVO = new ConsumidorVO();
		consumidorVO.setCpf("73117906118");
		consumidorVO.setIdCurso(new Long(123));
		//consumidorVO.setIdDepartamento(new Long(1));
		consumidorVO.setNome("Jose Silva");
		consumidorVO.setMatricula("2015780000");
		consumidorVO.setSexo(SexoEnum.MASCULINO);
		consumidorVO.setTitulo(TituloEnum.ESPECIALIZACAO);
		consumidorVO.setAnoIngresso("2015");
	}
}