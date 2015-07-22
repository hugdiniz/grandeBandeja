package test;

import static org.junit.Assert.*;

import org.junit.Test;

import entidades.Aluno;
import entidades.value_objects.CursoVO;
import entidades.value_objects.DepartamentoVO;

public class AlunoTest {

	@Test
	public void testGetInstance() {
		
		AlunoVO alunvo = new AlunoVO("Departamento", "CC");
		assertNotNull(alunvo);
	}

	@Test
	public void testRecuperarAluno() {
		
		
	}

	@Test
	public void testRecuperarAlunos() {
		fail("Not yet implemented");
	}

	@Test
	public void testAdicionarAluno() {
		fail("Not yet implemented");
	}

	@Test
	public void testAtualizarAluno() {
		fail("Not yet implemented");
	}

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

}
