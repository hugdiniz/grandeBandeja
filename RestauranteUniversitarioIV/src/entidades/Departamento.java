package entidades;

import java.sql.SQLException;
import java.util.Collection;

import persistencia.RepositorioDepartamento;
import entidades.exceptions.DepartamentoException;
import entidades.value_objects.DepartamentoVO;

public class Departamento
{
	private static Departamento departamento;
	
	public static Departamento getInstance() 
	{
		if (departamento == null) 
		{
			departamento = new Departamento();
		}
		return departamento;

	}
	
	public Boolean verificaDepartamentoJaExiste(DepartamentoVO departamentoVOBusca) throws DepartamentoException
	{
		DepartamentoVO departamentoVOantigo = recuperarDepartamento(departamentoVOBusca);
		if (departamentoVOantigo != null)
		{
			return Boolean.TRUE;
		}
		else
		{
			return Boolean.FALSE;
		}
	}
	
	public Collection recuperarDepartamentos(DepartamentoVO departamentoVO) throws DepartamentoException
	{
		
		try
		{
			return RepositorioDepartamento.getInstance().buscar(departamentoVO);
		} 
		catch (SQLException e)
		{
			throw new DepartamentoException("erro.recuperar.departamentos.repositorio.departamento.buscar");			
		}
	}
	
	public void adicionarDepartamento(DepartamentoVO departamentoVO) throws DepartamentoException 
	{
		DepartamentoVO departamentoVOBusca = new DepartamentoVO();
		departamentoVOBusca.setSigla(departamentoVO.getSigla());
		
		if (verificaDepartamentoJaExiste(departamentoVOBusca))
		{
			throw new DepartamentoException("erro.adicionar.departamento.repositorio.departamento");
		}
		
		try
		{
			RepositorioDepartamento.getInstance().inserirOuAtualizar(departamentoVO);
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new DepartamentoException("erro.adicionar.departamento.repositorio.departamento.inserirOuAtualizar");			
		}
				
	}
	
	public DepartamentoVO recuperarDepartamento(DepartamentoVO departamentoVO) throws DepartamentoException 
	{
		Collection departamentoVOs = null;
		try
		{
			departamentoVOs =  RepositorioDepartamento.getInstance().buscar(departamentoVO);
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new DepartamentoException("erro.recuperar.departamentos.repositorio.departamento.buscar");			
		}
		
		if (departamentoVOs != null && !departamentoVOs.isEmpty())
		{
			return (DepartamentoVO) departamentoVOs.iterator().next();
		}
		else
		{
			return null;
		}	
	}
	
	
	public void atualizarDepartamento (DepartamentoVO departamentoVO) throws DepartamentoException
	{
		if (departamentoVO.getId() == null)
		{
			DepartamentoVO departamentoVOantigo = new DepartamentoVO();
			departamentoVOantigo.setSigla(departamentoVO.getSigla());
			departamentoVOantigo = recuperarDepartamento(departamentoVOantigo);
			
			if (verificaDepartamentoJaExiste(departamentoVOantigo))
			{
				departamentoVO.setId(departamentoVOantigo.getId());
			}
			else
			{				
				throw new DepartamentoException("erro.atualizar.departamento.nao.encontrado");	
			}	
		}
		
		
		try
		{
			RepositorioDepartamento.getInstance().inserirOuAtualizar(departamentoVO);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new DepartamentoException("erro.atualizar.departamento.repositorio.inserirOuAtualizar");	
		}
	}
}