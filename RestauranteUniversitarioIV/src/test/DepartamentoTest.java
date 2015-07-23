package test;

import static org.junit.Assert.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import org.junit.Test;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import persistencia.ConnectionFactory;
import persistencia.Repositorio;
import entidades.Departamento;
import entidades.exceptions.DepartamentoException;
import entidades.value_objects.DepartamentoVO;

public class DepartamentoTest {
	
	@Test
	public void testGetInstance() 
	{
		
		DepartamentoVO deptVO = new DepartamentoVO("Departamento","DCC");
		assertNotNull(deptVO);
		
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
			throw new RuntimeException(e.getMessage(), e);
		}
			
	}
	
	
	@Test
	public void testAdicionarSemSigla() throws SQLException 
	{
		
		
		DepartamentoVO deptVo = new DepartamentoVO("Departamento Ciencia", " ");
		try {
			Departamento.getInstance().adicionarDepartamento(deptVo);
			fail();
		} catch (DepartamentoException e) {
			assertEquals("erro.adiconar.departamento.repositorio.departamento", e.getMessage()) ;
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
			assertEquals("erro.adiconar.departamento.repositorio.departamento", e.getMessage()) ;
		}
	}
	


	@Test
	public void testRecuperarDepartamento() throws DepartamentoException 
	{
		
		DepartamentoVO deptVO = new DepartamentoVO("Departamento","DCC");
		Departamento.getInstance().recuperarDepartamento(deptVO);
		assertNotNull(deptVO);
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
			throw new RuntimeException(e.getMessage(), e);
		}

	}

	@Test
	public void testRecuperarDepartamentos() throws DepartamentoException 
	{
		
		DepartamentoVO deptVO = new DepartamentoVO();
		Departamento.getInstance().recuperarDepartamentos(deptVO);
		assertNotNull(deptVO);
	
	}
	
	

}