package entidades;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

import persistencia.RepositorioDepartamento;
import persistencia.RepositorioFuncionario;
import persistencia.RepositorioConsumidor;
import entidades.exceptions.ConsumidorException;
import entidades.exceptions.FuncionarioException;
import entidades.value_objects.ConsumidorVO;

public class Funcionario
{	
	private static Funcionario funcionario;
	
	public static Funcionario getInstance() 
	{
		if (funcionario == null) 
		{
			funcionario = new Funcionario();
		}
		return funcionario;

	}
	
	
	public ConsumidorVO recuperarFuncionario(ConsumidorVO vo) throws FuncionarioException
	{
		Collection consumidorVOs = null;		
		
		try
		{
			consumidorVOs = recuperarFuncionarios(vo);			
		} 
		catch (FuncionarioException e)
		{
			e.printStackTrace();
			throw new FuncionarioException("erro.recuperar.funcionario.repositorio.funcionario.buscar");			
		}
		
		if (consumidorVOs != null && !consumidorVOs.isEmpty())
		{
			return (ConsumidorVO) consumidorVOs.iterator().next();
		}
		else
		{
			return null;
		}
	}
	
	public Collection recuperarFuncionarios(ConsumidorVO vo) throws FuncionarioException
	{
		Collection consumidorVOs = null;
		Collection funcionariosVOs = null;
		
		try
		{
			funcionariosVOs =  RepositorioFuncionario.getInstance().buscar(vo);
			consumidorVOs =  RepositorioConsumidor.getInstance().buscar(vo);			
			Iterator iteratorConsumidors = consumidorVOs.iterator();
			
			while(iteratorConsumidors != null && iteratorConsumidors.hasNext())
			{
				ConsumidorVO consumidorVO = (ConsumidorVO) iteratorConsumidors.next();
				
				Boolean localizado = false;
				Iterator iteratorFuncionarios = funcionariosVOs.iterator();
				while (!localizado &&  iteratorFuncionarios != null && iteratorFuncionarios.hasNext())
				{
					ConsumidorVO funcionarioVO = (ConsumidorVO) iteratorFuncionarios.next();
					if (funcionarioVO.getId() == consumidorVO.getId())
					{
						consumidorVO.setIdCurso(funcionarioVO.getIdCurso());
					}
				}
			}
			
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new FuncionarioException("erro.recuperar.funcionarios.repositorio.funcionario.buscar");			
		}
		
		
		return consumidorVOs;
	}	
	
	
	
	public void manterFuncionario(ConsumidorVO consumidorVO) throws ConsumidorException 
	{
		ConsumidorVO consumidorVOBusca = new ConsumidorVO();
		consumidorVOBusca.setId(consumidorVO.getId());
		ConsumidorVO consumidorVOantigo = recuperarFuncionario(consumidorVOBusca);		
		
		if (consumidorVOantigo != null && consumidorVOantigo.getId() == consumidorVO.getId())
		{
			//RN não pode ter mesmo consumidor (nome e sigla) 
			throw new FuncionarioException("erro.adiconar.funcionario.funcionario.ja.existe");
		}
		
		consumidorVO.setHabilitado(true);
		Consumidor.getInstance().manterConsumidor(consumidorVO);
		ConsumidorVO consumidorVOAdicionado = Consumidor.getInstance().recuperarConsumidor(consumidorVO);
		consumidorVOAdicionado.setIdDepartamento(consumidorVO.getIdDepartamento());	
		
		try
		{
			RepositorioFuncionario.getInstance().inserirOuAtualizar(consumidorVOAdicionado);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new FuncionarioException("erro.adicionar.funcionario.repositorio.consumidor.inserirOuAtualizar");
		}	
	}
	
	
}
