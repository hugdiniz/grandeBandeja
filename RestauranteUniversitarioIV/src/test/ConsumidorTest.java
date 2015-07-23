package test;


import static org.junit.Assert.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import persistencia.ConnectionFactory;
import entidades.CPF;
import entidades.Consumidor;
import entidades.Departamento;
import entidades.exceptions.ConsumidorException;
import entidades.exceptions.DepartamentoException;
import entidades.value_objects.ConsumidorVO;
import entidades.value_objects.DepartamentoVO;

public class ConsumidorTest {
	
	public void testGetInstance() 
	{
		
		ConsumidorVO consumidorVO = new ConsumidorVO();
		assertNotNull(consumidorVO);
		
	}

	@Test
	public void testRecuperarConsumidor() throws ConsumidorException, SQLException {
		// Isto está errado
		ConsumidorVO consumidorVO = new ConsumidorVO();
		Consumidor.getInstance().recuperarConsumidor(consumidorVO);
		assertNotNull(consumidorVO);
		
	}

	@Test
	public void testRecuperarConsumidors() throws ConsumidorException {
		ConsumidorVO consumidorVO = new ConsumidorVO();
		Consumidor.getInstance().recuperarConsumidors(consumidorVO);
		assertNotNull(consumidorVO);
	}

	@Test
	public void testAdicionarConsumidor() {
		
	}

	@Test
	public void testAtualizarConsumidor() {
		//fail("Not yet implemented");
	}

	
		
	

}
