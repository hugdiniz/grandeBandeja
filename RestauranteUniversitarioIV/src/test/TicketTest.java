package test;

import static org.junit.Assert.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.junit.Test;

import persistencia.ConnectionFactory;
import entidades.Consumidor;
import entidades.Curso;
import entidades.Ticket;
import entidades.enumerados.SexoEnum;
import entidades.enumerados.TituloEnum;
import entidades.enumerados.Turno;
import entidades.exceptions.CursoException;
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
	public void testAdicionarTicket() throws TicketException, SQLException {
		String matricula;
		String matriculac;
		
		ConsumidorVO consumidorVO = new ConsumidorVO();
		consumidorVO.setCpf("73117906118");
		consumidorVO.setIdCurso(new Long(123));
		//consumidorVO.setIdDepartamento(new Long(1));
		consumidorVO.setNome("Jose Silva");
		consumidorVO.setMatricula("2015780000");
		consumidorVO.setSexo(SexoEnum.MASCULINO);
		consumidorVO.setTitulo(TituloEnum.ESPECIALIZACAO);
		consumidorVO.setAnoIngresso("2015");
		
		RefeicaoVO refeicaoVO = new RefeicaoVO(Turno.valueOf("TARDE"),"descricao","op_vegetariana");
		TicketVO ticketVO = new TicketVO(consumidorVO.getId(), refeicaoVO.getId(), true);
		
		try
		{
			Ticket.getInstance().adicionarTicket(ticketVO);	
			PreparedStatement prep1 = ConnectionFactory.getConnection().prepareStatement("select * from ticket join consumidor on consumidor.id = consumidor_fk where matricula like '2015780000'");
			ResultSet resultSet1 = prep1.executeQuery();
			resultSet1.next();
			matriculac = resultSet1.getString("matricula");
			assertEquals(consumidorVO.getMatricula() ,matriculac);
		}
		catch (TicketException e)
		{
			if(e.equals("erro.adiconar.ticket.repositorio.ticket.ja.existe"))
			{
				assertEquals("erro.adiconar.ticket.repositorio.ticket.ja.existe" , e.getMessage());
			}
			else if(e.equals("erro.adicionar.ticket.repositorio.ticket.nao.existe.consumidor"))
			{
				assertEquals("erro.adicionar.ticket.repositorio.ticket.nao.existe.consumidor" , e.getMessage()) ;
			}
			else if(e.equals("erro.adicionar.ticket.repositorio.ticket.nao.existe.refeicao"))
			{
				assertEquals("erro.adicionar.ticket.repositorio.ticket.nao.existe.refeicao" , e.getMessage()) ;
			}
			
		}
		
	}
	
//	@Test
//	public void testAtualizarTicket() throws TicketException, SQLException {
//		
//		String pago;
//		
//		ConsumidorVO consumidorVO = new ConsumidorVO();
//		consumidorVO.setCpf("73117906118");
//		consumidorVO.setIdCurso(new Long(123));
//		//consumidorVO.setIdDepartamento(new Long(1));
//		consumidorVO.setNome("Jose Silva");
//		consumidorVO.setMatricula("2015780000");
//		consumidorVO.setSexo(SexoEnum.MASCULINO);
//		consumidorVO.setTitulo(TituloEnum.ESPECIALIZACAO);
//		consumidorVO.setAnoIngresso("2015");
//		
//		RefeicaoVO refeicaoVO = new RefeicaoVO(Turno.valueOf("TARDE"),"descricao","op_vegetariana");
//		TicketVO ticketVO = new TicketVO(consumidorVO.getId(), refeicaoVO.getId(), true);
//		
//		ticketVO.setPago(false);
//		
//		try
//		{
//			Ticket.getInstance().atualizarTicket(ticketVO);	
//			PreparedStatement prep1 = ConnectionFactory.getConnection().prepareStatement("select * from ticket join consumidor on consumidor.id = consumidor_fk where matricula like '2015780000'");
//			ResultSet resultSet1 = prep1.executeQuery();
//			resultSet1.next();
//			pago = resultSet1.getString("pago");
//			assertEquals(ticketVO.getPago() ,pago);
//		}
//		catch (TicketException e)
//		{
//			if(e.equals("erro.adiconar.ticket.repositorio.ticket.ja.existe"))
//			{
//				assertEquals("erro.adiconar.ticket.repositorio.ticket.ja.existe" , e.getMessage());
//			}
//			else if(e.equals("erro.adicionar.ticket.repositorio.ticket.nao.existe.consumidor"))
//			{
//				assertEquals("erro.adicionar.ticket.repositorio.ticket.nao.existe.consumidor" , e.getMessage()) ;
//			}
//			else if(e.equals("erro.adicionar.ticket.repositorio.ticket.nao.existe.refeicao"))
//			{
//				assertEquals("erro.adicionar.ticket.repositorio.ticket.nao.existe.refeicao" , e.getMessage()) ;
//			}
//			
//		}
//		
//	}
}