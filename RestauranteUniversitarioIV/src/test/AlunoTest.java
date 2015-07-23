package test;

import static org.junit.Assert.*;

import org.junit.Test;

import entidades.Aluno;

public class AlunoTest {

	@Test
	public void testGetInstance() {
		Aluno aluno = new Aluno();
		assertNotNull(aluno);
	}

	@Test
	public void testRecuperarAluno() {
		
	}

	@Test
	public void testRecuperarAlunos() {
		fail("Not yet implemented");
	}

	@Test
	public void testManterAluno() {
		fail("Not yet implemented");
	}

}
