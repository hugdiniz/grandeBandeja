package entidades;

import static org.junit.Assert.*;



import org.junit.Test;
import entidades.exceptions.DepartamentoException;
import entidades.value_objects.DepartamentoVO;

public class DepartamentoTest {

	@Test
	public void testGetInstance() {
		
		DepartamentoVO departamentoVO = new DepartamentoVO("Departamento","DCC");
		assertNotNull(departamentoVO);
		
	}

	@Test
	public void testRecuperarDepartamentos() throws DepartamentoException {
		
		DepartamentoVO departamentoVO = new DepartamentoVO("Departamento","DCC");
		departamentoVO.setSigla("DCC");
		Departamento.getInstance().recuperarDepartamentos(departamentoVO);
		assertNotNull(departamentoVO);
	
	}

	@Test
	public void testAdicionarDepartamento() throws DepartamentoException {
		
		DepartamentoVO departamentoVO = new DepartamentoVO("Departamento Letras","LL");
		departamentoVO.setSigla("LL");
		Departamento.getInstance().adicionarDepartamento(departamentoVO);
		assertNotNull(departamentoVO);
		
		
		
	}

	@Test
	public void testRecuperarDepartamento() throws DepartamentoException 
	{
		
		DepartamentoVO departamentoVO = new DepartamentoVO("Departamento","DCC");
		departamentoVO.setSigla("DCC");
		Departamento.getInstance().recuperarDepartamento(departamentoVO);
		assertNotNull(departamentoVO);
	}

	@Test
	public void testAtualizarDepartamento() throws DepartamentoException 
	{
		
		DepartamentoVO departamentoVO = new DepartamentoVO("Departamento","CC");
		departamentoVO.setSigla("CC");
		Departamento.getInstance().atualizarDepartamento(departamentoVO);
		assertNotNull(departamentoVO);
		
		
		
	}

}
