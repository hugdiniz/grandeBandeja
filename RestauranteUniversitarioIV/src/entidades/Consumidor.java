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
	
	public void manterConsumidor(ConsumidorVO consumidorVO) throws ConsumidorException 
	{
		ConsumidorVO consumidorVOBusca = new ConsumidorVO();
			
		if (consumidorVO.getAtualizar() != null && consumidorVO.getAtualizar())
		{
			consumidorVOBusca.setId(consumidorVO.getId());
			ConsumidorVO consumidorVOantigo = recuperarConsumidor(consumidorVOBusca);
			
			if (consumidorVOantigo == null || consumidorVOantigo.getId() != consumidorVO.getId())
			{				
				throw new ConsumidorException("erro.adiconar.consumidor.repositorio.consumidor.nao.existe");
			}
		}
		else
		{
			consumidorVOBusca.setMatricula(consumidorVO.getMatricula());
			ConsumidorVO consumidorVOantigo = recuperarConsumidor(consumidorVOBusca);
			
			if (consumidorVOantigo != null && consumidorVOantigo.getId() == consumidorVO.getId())
			{
				//RN não pode ter mesmo consumidor (nome e sigla) 
				throw new ConsumidorException("erro.adiconar.consumidor.repositorio.consumidor.ja.existe");
			}
		    if(consumidorVOantigo != null && consumidorVOantigo.getMatricula().equals(consumidorVO.getMatricula()))
			{
				//RN não pode ter mesmo consumidor (nome e sigla) 
				throw new ConsumidorException("erro.adiconar.consumidor.repositorio.consumidor.nao.pode.cadastrar.consumidor.com.mesma.matricula");
			}
		    if(consumidorVOantigo != null && consumidorVOantigo.getCpf().equals(consumidorVO.getCpf()))
		    {
		    	//RN não pode ter mesmo consumidor (nome e sigla) 
				throw new ConsumidorException("erro.adiconar.consumidor.repositorio.consumidor.nao.pode.cadastrar.consumidor.com.mesmo.cpf");
		    }
		}	
		
		
		Boolean cpfValido = CPF.getInstance().isValidCPF(consumidorVO.getCpf());
		
		if (cpfValido.equals(false))
		{
			throw new ConsumidorException("erro.adiconar.consumidor.cpf.nao.valido");
		}
		
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
	
	@Deprecated
	public void adicionarConsumidor(ConsumidorVO consumidorVO) throws ConsumidorException 
	{
		consumidorVO.setAtualizar(Boolean.FALSE);
		manterConsumidor(consumidorVO);
	}
	
	
	@Deprecated
	public void atualizarConsumidor(ConsumidorVO vo) throws ConsumidorException 
	{	
		vo.setAtualizar(Boolean.TRUE);
		manterConsumidor(vo);
	}
	public void removerConsumidor(ConsumidorVO vo) throws ConsumidorException
	{
		ConsumidorVO consumidorVODesabilitado = new ConsumidorVO();
		consumidorVODesabilitado.setId(vo.getId());
		consumidorVODesabilitado = recuperarConsumidor(consumidorVODesabilitado);
		
		consumidorVODesabilitado.setHabilitado(Boolean.FALSE);
		consumidorVODesabilitado.setAtualizar(Boolean.TRUE);
		manterConsumidor(consumidorVODesabilitado);
	}
}
