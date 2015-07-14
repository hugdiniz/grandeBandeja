package entidades;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

import persistencia.RepositorioFuncionario;
import persistencia.RepositorioConsumidor;
import entidades.exceptions.FuncionarioException;
import entidades.value_objects.ConsumidorVO;

public class Funcionario extends Consumidor
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
	
	
	public void adicionarFuncionario(ConsumidorVO consumidorVO) throws FuncionarioException 
	{
		ConsumidorVO consumidorVOBusca = new ConsumidorVO();
		consumidorVOBusca.setId(consumidorVO.getId());
		ConsumidorVO consumidorVOantigo = recuperarFuncionario(consumidorVOBusca);		
		
		if (consumidorVOantigo != null && consumidorVOantigo.getId() == consumidorVO.getId())
		{
			//RN não pode ter mesmo consumidor (nome e sigla) 
			throw new FuncionarioException("erro.adiconar.funcionario.funcionario.ja.existe");
		}
		
		/*if(consumidorVO.getDepartamentoVO().getId() == null)
		{
			//RN Não existe consumidor sem o seu respectivo Departamento.
			throw new FuncionarioException("erro.adicionar.consumidor.repositorio.consumidor.nao.existe.departamento");
		}*/
		
		try
		{
			RepositorioFuncionario.getInstance().inserirOuAtualizar(consumidorVO);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new FuncionarioException("erro.adicionar.funcionario.repositorio.funcionario.inserirOuAtualizar");
		}
		try
		{
			RepositorioConsumidor.getInstance().inserirOuAtualizar(consumidorVO);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new FuncionarioException("erro.adicionar.funcionario.repositorio.consumidor.inserirOuAtualizar");
		}	
	}

	public void atualizarFuncionario(ConsumidorVO vo) throws FuncionarioException 
	{	
		ConsumidorVO consumidorVOBusca = new ConsumidorVO();
		consumidorVOBusca.setId(vo.getId());
		ConsumidorVO consumidorVOantigo = recuperarFuncionario(consumidorVOBusca);
		
		//Verifica se objeto recuperado na base é o mesmo alterado no front-end. Caso não seja, lança erro.
		if (consumidorVOantigo == null || consumidorVOantigo.getId() != vo.getId())
		{			
			throw new FuncionarioException("erro.atualizar.funcionario.nao.existe");
		}
		
		try
		{
			RepositorioFuncionario.getInstance().inserirOuAtualizar(vo);
		}
		catch (SQLException e)
		{			
			e.printStackTrace();
			throw new FuncionarioException("erro.atualizar.funcionario.repositorio.funcionario.inserirOuAtualizar");
		}	
		
		try
		{
			RepositorioConsumidor.getInstance().inserirOuAtualizar(vo);
		}
		catch (SQLException e)
		{			
			e.printStackTrace();
			throw new FuncionarioException("erro.atualizar.funcionario.repositorio.consumidor.inserirOuAtualizar");
		}	
	}
}
