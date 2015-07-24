package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Random;

import org.junit.Test;

import persistencia.ConnectionFactory;
import entidades.Curso;
import entidades.exceptions.CursoException;
import entidades.value_objects.CursoVO;
import entidades.value_objects.DepartamentoVO;

public class CursoTest {

	@Test
	public void testGetInstance() 
	{
		assertNotNull(Curso.getInstance());
	}

	@Test
	public void testVerificaCursoJaExiste() throws SQLException 
	{
		DepartamentoVO depvo = new DepartamentoVO("Computacao", "DCC");
		CursoVO cursoVO = new CursoVO("cien", "cc", depvo);
		PreparedStatement prep1 = ConnectionFactory.getConnection().prepareStatement("INSERT INTO curso (nome,sigla) values ('cien','cc');");
		prep1.executeUpdate();
		try 
		{
			assertEquals((Curso.getInstance().verificaCursoJaExiste(cursoVO)), Boolean.FALSE);
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
		Collection  curso = Curso.getInstance().recuperarCursos(cursoVO);
		assertNotNull(curso);
	}

	
	@Test
	public void testAdicionarCurso() throws SQLException, CursoException 
	{
		String sigla;
		String siglac;
		String nome;
		Random random = new Random();
		String randNome1 = String.valueOf(random.nextLong());	
		CursoVO cursoVO = new CursoVO();//"Ccomp", randNome1, new Long(1)
		cursoVO.setNome("Ccomp");
		cursoVO.setSigla(randNome1);
		DepartamentoVO departamentoVO = new DepartamentoVO();
		departamentoVO.setId(new Long(1));
		cursoVO.setDepartamentoVO(departamentoVO);
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
			assertEquals("erro.adicionar.curso.repositorio.curso.nao.existe.departamento", e.getMessage()) ;
		}
	}

//	@Test
//	public void testAtualizarCurso() throws SQLException, CursoException 
//	{
//		String nome = null;
//		DepartamentoVO deptVo = new DepartamentoVO();
//		deptVo.setId(new Long(1));
//		Random random = new Random();
//		String randNome1 = String.valueOf(random.nextLong());	
//		CursoVO cursoVO = new CursoVO("cien",randNome1, deptVo);
//		try 
//		{
//			Curso.getInstance().atualizarCurso(cursoVO);
//			PreparedStatement prep = ConnectionFactory.getConnection().prepareStatement("select * from curso where sigla like 'cc'");
//			
//			ResultSet resultSet = prep.executeQuery();
//			resultSet.next();
//			nome = resultSet.getString("nome");
//			assertEquals(deptVo.getNome() ,nome);
//		}
//		catch (CursoException e)
//		{
//			assertEquals("erro.adiconar.curso.repositorio.curso.ja.existe", e.getMessage()) ;
//		}
//	}


}



