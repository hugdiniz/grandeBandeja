package test;

import static org.junit.Assert.*;

import org.junit.Test;

import entidades.Departamento;
import entidades.exceptions.DepartamentoException;
import entidades.value_objects.DepartamentoVO;

public class DepartamentoTest {

	@Test
	public void testaDepartamentoExiste() {
		DepartamentoVO deptvo = new DepartamentoVO("Departamento", "CC");
		try{
			assertTrue(Departamento.getInstance().verificaDepartamentoJaExiste(deptvo));
		} catch(DepartamentoException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	

}
