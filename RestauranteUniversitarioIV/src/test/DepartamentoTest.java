package test;

import static org.junit.Assert.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Random;

import org.junit.Test;

import persistencia.ConnectionFactory;
import entidades.Departamento;
import entidades.exceptions.DepartamentoException;
import entidades.value_objects.DepartamentoVO;

public class DepartamentoTest {
	
	@Test
	public void testGetInstance() 
	{		
		assertNotNull(Departamento.getInstance());		
	}
	
	
	@Test
	public void testAdicionarDepartamento() throws SQLException 
	{
		
		String sigla = null;
		Random random = new Random();
		String randNome = String.valueOf(random.nextLong());
		DepartamentoVO deptVo = new DepartamentoVO("Departamento Libra", randNome);
		try 
		{
			Departamento.getInstance().adicionarDepartamento(deptVo);			
			PreparedStatement prep = ConnectionFactory.getConnection().prepareStatement("select * from departamento where sigla like '"+randNome+"'");
			ResultSet resultSet = prep.executeQuery();
			resultSet.next();
			sigla = resultSet.getString("sigla");
			assertEquals(deptVo.getSigla() ,sigla);
			
		} 
		catch (DepartamentoException e)
		{
			assertEquals("erro.adicionar.departamento.repositorio.departamento", e.getMessage()) ;
		}
			
	}
	
	
	@Test
	public void testAdicionarSemSigla() throws SQLException 
	{
		
		
		DepartamentoVO deptVo = new DepartamentoVO("Departamento Ciencia", "");
		try 
		{
			Departamento.getInstance().adicionarDepartamento(deptVo);
			fail();
		} catch (DepartamentoException e) 
		{
			assertEquals("erro.adicionar.departamento.repositorio.departamento", e.getMessage()) ;
		}		
	}


	@Test
	public void testVerificaDepartamentoJaExiste()
	{
		
		DepartamentoVO deptvo = new DepartamentoVO("Computacao", "DCC");
		try
		{
			assertTrue(Departamento.getInstance().verificaDepartamentoJaExiste(deptvo));
		}
		catch(DepartamentoException e) 
		{
			assertEquals("erro.adicionar.departamento.repositorio.departamento", e.getMessage()) ;
		}
	}
	


	@Test
	public void testRecuperarDepartamento() throws DepartamentoException 
	{
		
		DepartamentoVO deptVO = new DepartamentoVO("Departamento","DCC");
		
		try
		{
			Departamento.getInstance().recuperarDepartamento(deptVO);
			assertNotNull(deptVO);
		}
		catch (DepartamentoException e)
		{
			assertEquals("erro.recuperar.departamentos.repositorio.departamento.buscar", e.getMessage()) ;
		}
	}

	@Test
	public void testAtualizarDepartamento() throws SQLException
	{
		
		String nome = null;
		DepartamentoVO deptVo = new DepartamentoVO("Computacao","DCC");
		
		try 
		{
			Departamento.getInstance().atualizarDepartamento(deptVo); 
			PreparedStatement prep = ConnectionFactory.getConnection().prepareStatement("select * from departamento where sigla like 'DCC'");			
			ResultSet resultSet = prep.executeQuery();
			resultSet.next();
			nome = resultSet.getString("nome");
			assertEquals(deptVo.getNome() ,nome);
		}
		catch (DepartamentoException e)
		{
			assertEquals("erro.atualizar.departamento.nao.encontrado", e.getMessage()) ;
		}

	}

	@Test
	public void testRecuperarDepartamentos() throws DepartamentoException 
	{
		
		DepartamentoVO deptVO = new DepartamentoVO();
		
		try
		{
			Collection departamentos = Departamento.getInstance().recuperarDepartamentos(deptVO);
			assertNotNull(departamentos);
		}
		catch (DepartamentoException e)
		{
			assertEquals("erro.recuperar.departamentos.repositorio.departamento.buscar", e.getMessage()) ;
		}
		
	
	}
	

	
	@Test(expected=DepartamentoException.class)
	public void testaSigla() throws DepartamentoException
	{
		DepartamentoVO depVo = new DepartamentoVO();
		depVo.setSigla("SIGLA_FAKE");
		depVo.setNome("NOME FAKE");
		Departamento.getInstance().adicionarDepartamento(depVo);
		Departamento.getInstance().adicionarDepartamento(depVo);
	}
	
	

}