package entidades;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

import persistencia.RepositorioAluno;
import persistencia.RepositorioConsumidor;
import entidades.exceptions.AlunoException;
import entidades.exceptions.ConsumidorException;
import entidades.value_objects.ConsumidorVO;

public class Aluno
{	
	private static Aluno aluno;
	
	public static Aluno getInstance() 
	{
		if (aluno == null) 
		{
			aluno = new Aluno();
		}
		return aluno;

	}
	
	
	public ConsumidorVO recuperarAluno(ConsumidorVO vo) throws AlunoException
	{
		Collection consumidorVOs = null;		
		
		try
		{
			consumidorVOs = recuperarAlunos(vo);			
		} 
		catch (AlunoException e)
		{
			e.printStackTrace();
			throw new AlunoException("erro.recuperar.aluno.repositorio.aluno.buscar");			
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
	
	public Collection recuperarAlunos(ConsumidorVO vo) throws AlunoException
	{
		Collection consumidorVOs = null;
		Collection alunosVOs = null;
		
		try
		{
			alunosVOs =  RepositorioAluno.getInstance().buscar(vo);
			consumidorVOs =  RepositorioConsumidor.getInstance().buscar(vo);			
			Iterator iteratorConsumidors = consumidorVOs.iterator();
			
			while(iteratorConsumidors != null && iteratorConsumidors.hasNext())
			{
				ConsumidorVO consumidorVO = (ConsumidorVO) iteratorConsumidors.next();
				
				Boolean localizado = false;
				Iterator iteratorAlunos = alunosVOs.iterator();
				while (!localizado &&  iteratorAlunos != null && iteratorAlunos.hasNext())
				{
					ConsumidorVO alunoVO = (ConsumidorVO) iteratorAlunos.next();
					if (alunoVO.getId() == consumidorVO.getId())
					{
						consumidorVO.setIdCurso(alunoVO.getIdCurso());
					}
				}
			}
			
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new AlunoException("erro.recuperar.alunos.repositorio.aluno.buscar");			
		}
		
		
		return consumidorVOs;
	}	
	
	
	public void adicionarAluno(ConsumidorVO consumidorVO) throws ConsumidorException 
	{
		ConsumidorVO consumidorVOBusca = new ConsumidorVO();
		consumidorVOBusca.setNome(consumidorVO.getNome());
		
		ConsumidorVO consumidorVOantigo = recuperarAluno(consumidorVOBusca);		
		
		if (consumidorVOantigo != null && consumidorVOantigo.getId() == consumidorVO.getId())
		{
			//RN não pode ter mesmo consumidor (nome e sigla) 
			throw new AlunoException("erro.adiconar.aluno.aluno.ja.existe (nome ou sigla)");
		}
		
		consumidorVO.setHabilitado(true);
		Consumidor.getInstance().adicionarConsumidor(consumidorVO);
		ConsumidorVO consumidorVOAdicionado = Consumidor.getInstance().recuperarConsumidor(consumidorVO);
		consumidorVOAdicionado.setIdCurso(consumidorVO.getIdCurso());		
		
		try
		{
			RepositorioAluno.getInstance().inserirOuAtualizar(consumidorVOAdicionado);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new AlunoException("erro.adicionar.aluno.repositorio.aluno.inserirOuAtualizar");
		}
	}

	public void atualizarAluno(ConsumidorVO vo) throws ConsumidorException
	{	
		ConsumidorVO consumidorVOBusca = new ConsumidorVO();
		consumidorVOBusca.setId(vo.getId());
		ConsumidorVO consumidorVOantigo = recuperarAluno(consumidorVOBusca);
		
		
		//Verifica se objeto recuperado na base é o mesmo alterado no front-end. Caso não seja, lança erro.
		if (consumidorVOantigo == null || consumidorVOantigo.getId() != vo.getId())
		{			
			throw new AlunoException("erro.atualizar.aluno.nao.existe");
		}
		vo.setAtualizar(Boolean.TRUE);
		
		
		Consumidor.getInstance().adicionarConsumidor(vo);
		try
		{
			RepositorioAluno.getInstance().inserirOuAtualizar(vo);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new AlunoException("erro.adicionar.aluno.repositorio.aluno.inserirOuAtualizar");
		}
	}
}
