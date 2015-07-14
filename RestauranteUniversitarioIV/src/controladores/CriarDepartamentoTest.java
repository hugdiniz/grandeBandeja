package controladores;

import static org.junit.Assert.*;

import org.junit.Test;

import entidades.value_objects.CursoVO;
import entidades.value_objects.DepartamentoVO;

public class CriarDepartamentoTest {

	@Test
	public void testCriarDepartamento(){
		DepartamentoVO departamentoVO = new DepartamentoVO("Departamento","DCC");
		assertNotNull(departamentoVO);//Afirma que um objeto é nulo.
		
		
		
	}
	

}
