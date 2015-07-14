package entidades;

import java.sql.SQLException;
import java.util.Collection;

import persistencia.RepositorioConsumidor;
import entidades.exceptions.ConsumidorException;
import entidades.value_objects.ConsumidorVO;

public abstract class Consumidor
{
	public ConsumidorVO recuperarConsumidor(ConsumidorVO consumidorVO) throws ConsumidorException
	{
		Collection consumidorVOs = null;
		
		try
		{
			consumidorVOs =  RepositorioConsumidor.getInstance().buscar(consumidorVO);
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsumidorException("erro.recuperar.consumidors.repositorio.consumidor.buscar");			
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
	
	public Collection recuperarConsumidors(ConsumidorVO consumidorVO) throws ConsumidorException
	{
		Collection consumidorVOs = null;
		
		try
		{
			consumidorVOs =  RepositorioConsumidor.getInstance().buscar(consumidorVO);
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsumidorException("erro.recuperar.consumidors.repositorio.consumidor.buscar");			
		}
		
		return consumidorVOs;
	}	
	
	
	public void adicionarConsumidor(ConsumidorVO consumidorVO) throws ConsumidorException 
	{
		ConsumidorVO consumidorVOBusca = new ConsumidorVO();
		consumidorVOBusca.setId(consumidorVO.getId());
		ConsumidorVO consumidorVOantigo = recuperarConsumidor(consumidorVOBusca);		
		
		if (consumidorVOantigo != null )
		{
			//RN não pode ter mesmo consumidor (nome e sigla) 
			throw new ConsumidorException("erro.adiconar.consumidor.repositorio.consumidor.ja.existe");
		}
		
		/*if(consumidorVO.getDepartamentoVO().getId() == null)
		{
			//RN Não existe consumidor sem o seu respectivo Departamento.
			throw new ConsumidorException("erro.adicionar.consumidor.repositorio.consumidor.nao.existe.departamento");
		}*/
		
		 try
		{
			RepositorioConsumidor.getInstance().inserirOuAtualizar(consumidorVO);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConsumidorException("erro.recuperar.adicionar.repositorio.consumidor.inserirOuAtualizar");
		}	
	}

	public void atualizarConsumidor(ConsumidorVO vo) throws ConsumidorException 
	{	
		ConsumidorVO consumidorVOBusca = new ConsumidorVO();
		consumidorVOBusca.setId(vo.getId());
		ConsumidorVO consumidorVOantigo = recuperarConsumidor(consumidorVOBusca);
		
		//Verifica se objeto recuperado na base é o mesmo alterado no front-end. Caso não seja, lança erro.
		if (consumidorVOantigo != null && consumidorVOantigo.getId() != vo.getId())
		{			
			throw new ConsumidorException("erro.adiconar.consumidor.repositorio.consumidor.ja.existe");
		}
		
		try
		{
			RepositorioConsumidor.getInstance().inserirOuAtualizar(vo);
		}
		catch (SQLException e)
		{			
			e.printStackTrace();
			throw new ConsumidorException("erro.recuperar.atualizar.repositorio.consumidor.inserirOuAtualizar");
		}	
	}
}
