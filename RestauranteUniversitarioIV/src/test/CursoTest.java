package test;

import static org.junit.Assert.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import org.junit.Test;

import persistencia.ConnectionFactory;

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
	public void testGetInstance() 
	{
		DepartamentoVO depvo = new DepartamentoVO("Departamento", "CC");
		CursoVO cursoVO = new CursoVO("Curso Ingles","CI", depvo);
		assertNotNull(cursoVO);
	}

	@Test
	public void testVerificaCursoJaExiste() 
	{
		DepartamentoVO depvo = new DepartamentoVO("Computacao", "DCC");
		CursoVO cursoVO = new CursoVO("cien", "cc", depvo);
		try 
		{
			assertTrue(Curso.getInstance().verificaCursoJaExiste(cursoVO));
		} 
		catch (CursoException e) 
		{
			throw new RuntimeException("Erro no BD");
		}
	}

	@Test
	public void testRecuperarCursos() throws CursoException 
	{
		CursoVO cursoVO = new CursoVO();
		Curso.getInstance().recuperarCursos(cursoVO);
		assertNotNull(cursoVO);
	}

	@Test
	public void testRecuperarCurso() throws CursoException 
	{
		DepartamentoVO depvo = new DepartamentoVO("Departamento", "CC");
		CursoVO cursoVO = new CursoVO("Ccomp", "CC", depvo);
		Curso.getInstance().recuperarCurso(cursoVO);
		assertNotNull(cursoVO);
	}

	@Test
	public void testAdicionarCurso() throws SQLException, CursoException 
	{
		String sigla;
		String siglac;
		String nome;
		Random random = new Random();
		String randNome1 = String.valueOf(random.nextLong());
		PreparedStatement prep = ConnectionFactory.getConnection().prepareStatement("select * from departamento");		
		ResultSet resultSet = prep.executeQuery();
		resultSet.next();
		nome = resultSet.getString("nome");
		sigla = resultSet.getString("sigla");
		
		DepartamentoVO depvo = new DepartamentoVO(nome, sigla);
		CursoVO cursoVO = new CursoVO("Ccomp", randNome1, depvo);
		try 
		{
			Curso.getInstance().adicionarCurso(cursoVO);			
			PreparedStatement prep1 = ConnectionFactory.getConnection().prepareStatement("select * from curso where sigla like '"+randNome1+"'");
			ResultSet resultSet1 = prep1.executeQuery();
			resultSet1.next();
			siglac = resultSet1.getString("sigla");
			assertEquals(cursoVO.getSigla() ,siglac);
			
		} 
		catch (CursoException e)
		{
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@Test
	public void testAtualizarCurso() throws SQLException, CursoException 
	{
		String nome = null;
		Random random = new Random();
		String randNome = String.valueOf(random.nextLong());
		String randNome1 = String.valueOf(random.nextLong());
		DepartamentoVO deptVo = new DepartamentoVO("Computacao", "DCC");
		CursoVO cursoVO = new CursoVO("cien","cc", deptVo);
		try 
		{
			Curso.getInstance().atualizarCurso(cursoVO);
			PreparedStatement prep = ConnectionFactory.getConnection().prepareStatement("select * from curso where sigla like 'cc'");
			
			ResultSet resultSet = prep.executeQuery();
			resultSet.next();
			nome = resultSet.getString("nome");
			assertEquals(deptVo.getNome() ,nome);
		}
		catch (CursoException e)
		{
			throw new RuntimeException(e.getMessage(), e);
		}
	}

}




