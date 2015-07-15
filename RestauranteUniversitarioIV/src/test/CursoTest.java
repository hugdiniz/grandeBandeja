package Testes;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import entidades.Curso;
import entidades.Departamento;
import entidades.exceptions.CursoException;
import entidades.value_objects.CursoVO;
import entidades.exceptions.DepartamentoException;
import entidades.value_objects.DepartamentoVO;

public class CursoTest {

	@Test
	public void testGetInstance() {
		DepartamentoVO departamentoVO = new DepartamentoVO("Departamento","DCC");
		CursoVO cursoVO = new CursoVO("Curso","CC", departamentoVO);
		assertNotNull(cursoVO);
	}

	@Test
	public void testRecuperarCursos() throws CursoException {
		
		DepartamentoVO departamentoVO = new DepartamentoVO("Departamento","DCC");
		CursoVO cursoVO = new CursoVO("Curso","CC", departamentoVO);
		cursoVO.setSigla("CC");
		Curso.getInstance().recuperarCursos(cursoVO);
		assertNotNull(cursoVO);
	}

	@Test
	public void testRecuperarCurso() throws CursoException {
		
		DepartamentoVO departamentoVO = new DepartamentoVO("Departamento","DCC");
		CursoVO cursoVO = new CursoVO("Curso","CC", departamentoVO);
		cursoVO.setSigla("CC");
		Curso.getInstance().recuperarCurso(cursoVO);
		assertNotNull(cursoVO);
		
	}

	@Test
	public void testAdicionarCurso() throws CursoException, DepartamentoException {
		
		DepartamentoVO departamentoVO = new DepartamentoVO(null,"CC");
		Random random = new Random();
		String randNome = String.valueOf(random.nextLong());
		departamentoVO = Departamento.getInstance().recuperarDepartamento(departamentoVO);
		CursoVO cursoVO = new CursoVO("Curso Turismo","CT2cc", departamentoVO);
		cursoVO.setSigla(randNome);
		Curso.getInstance().adicionarCurso(cursoVO); 
		assertNotNull(cursoVO);
				
	}

	@Test
	public void testAtualizarCurso() throws CursoException, DepartamentoException {
		
		DepartamentoVO departamentoVO = new DepartamentoVO(null,"CC");
		departamentoVO = Departamento.getInstance().recuperarDepartamento(departamentoVO);
		CursoVO cursoVO = new CursoVO("Curso Direitoo","DDdd", departamentoVO);
		Random random = new Random();
		String randNome = String.valueOf(random.nextLong());
		cursoVO.setSigla(randNome);
		Curso.getInstance().atualizarCurso(cursoVO); 
		assertNotNull(cursoVO);

		
		
	}

}
