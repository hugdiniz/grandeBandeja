package test;


import static org.junit.Assert.*;

import org.junit.Test;

import entidades.CPF;
import entidades.Departamento;
import entidades.exceptions.DepartamentoException;

public class ConsumidorTest {

	@Test
	public void testRecuperarConsumidor() {
		fail("Not yet implemented");
	}

	@Test
	public void testRecuperarConsumidors() {
		fail("Not yet implemented");
	}

	@Test
	public void testAdicionarConsumidor() {
		fail("Not yet implemented");
	}

	@Test
	public void testAtualizarConsumidor() {
		fail("Not yet implemented");
	}

	@Test
	public void testCPF() 
	{
		// CPF Valido
		assertTrue(CPF.getInstance().isValidCPF("13251198734"));
		
		assertFalse(CPF.getInstance().isValidCPF("cpfErradoQualquer"));
	}

}
