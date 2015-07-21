package entidades;

import java.sql.SQLException;
import java.util.Collection;

import persistencia.RepositorioConsumidor;
import entidades.exceptions.ConsumidorException;
import entidades.value_objects.ConsumidorVO;

public class Consumidor
{	
	private static Consumidor consumidor;
	
	public static Consumidor getInstance() 
	{
		if (consumidor == null) 
		{
			consumidor = new Consumidor();
		}
		return consumidor;

	}
	
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
		
		if (consumidorVOantigo != null && consumidorVOantigo.getId() == consumidorVO.getId())
		{
			//RN não pode ter mesmo consumidor (nome e sigla) 
			throw new ConsumidorException("erro.adiconar.consumidor.repositorio.consumidor.ja.existe");
		}
		
		Boolean cpfValido = CPF.getInstance().isValidCPF(consumidorVO.getCpf());
		
		if (cpfValido.equals(false))
		{
			throw new ConsumidorException("erro.adiconar.consumidor.cpf.nao.valido");
		}
		
		consumidorVO.setHabilitado(Boolean.TRUE);
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
		if (consumidorVOantigo == null || consumidorVOantigo.getId() != vo.getId())
		{			
			throw new ConsumidorException("erro.atualizar.consumidor.repositorio.consumidor.nao.existe");
		}
		
		Boolean cpfValido = CPF.getInstance().isValidCPF(vo.getCpf());
		if (cpfValido.equals(false))
		{
			throw new ConsumidorException("erro.adiconar.consumidor.cpf.nao.valido");
		}
		
		try
		{
			RepositorioConsumidor.getInstance().inserirOuAtualizar(vo);
		}
		catch (SQLException e)
		{			
			e.printStackTrace();
			throw new ConsumidorException("erro.atualizar.consumidor.repositorio.consumidor.inserirOuAtualizar");
		}	
	}
	public void removerConsumidor(ConsumidorVO vo) throws ConsumidorException
	{
		vo.setHabilitado(Boolean.FALSE);
		atualizarConsumidor(vo);
	}
}
