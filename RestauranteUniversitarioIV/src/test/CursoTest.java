package test;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import com.sun.corba.se.impl.orbutil.RepositoryIdStrings;

import entidades.Aluno;
import entidades.Curso;
import entidades.Departamento;
import entidades.exceptions.CursoException;
import entidades.value_objects.CursoVO;
import entidades.exceptions.DepartamentoException;
import entidades.value_objects.DepartamentoVO;

public class CursoTest {

	@Test
	public void testCursoExistente() {
		DepartamentoVO depvo = new DepartamentoVO("Departamento", "CC");
		CursoVO vo = new CursoVO("Ccomp", "CC", depvo);
		try {
			assertTrue(Curso.getInstance().verificaCursoJaExiste(vo));
		} catch (CursoException e) {
			throw new RuntimeException("Erro no BD");
		}
	}

}
