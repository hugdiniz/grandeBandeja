package controladores;

import static org.junit.Assert.*;

import org.junit.Test;

import entidades.value_objects.CursoVO;
import entidades.value_objects.DepartamentoVO;

public class CriarCursoTest {

	@Test
	
	
	public void testCriarCurso(){
		DepartamentoVO departamentoVO = new DepartamentoVO("Departamento","DCC");
		CursoVO cursoVO = new CursoVO("nome", "sigla",departamentoVO);
		assertNotNull(cursoVO);//Afirma que um objeto é nulo.
		
		
		
	}
	

}
