package test;

import static org.junit.Assert.*;

import org.junit.Test;

import entidades.CPF;

public class CPFTest {

	@Test
	public void test() {
			assertTrue(CPF.getInstance().isValidCPF("14062498740"));
		
			assertFalse(CPF.getInstance().isValidCPF("12345678912"));
			
			
	}
			
}