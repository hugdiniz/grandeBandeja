package entidades;

import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.http.HttpSession;

import persistencia.RepositorioRefeicao;
import entidades.exceptions.RefeicaoException;
import entidades.value_objects.RefeicaoVO;

public class Refeicao
{
	private static Refeicao refeicao;
	
	public static Refeicao getInstance() 
	{
		if (refeicao == null) 
		{
			refeicao = new Refeicao();
		}
		return refeicao;

	}
	
	public Collection recuperarRefeicoes(RefeicaoVO refeicaoVO) throws RefeicaoException
	{
		
		try
		{
			return RepositorioRefeicao.getInstance().buscar(refeicaoVO);
		} 
		catch (SQLException e)
		{
			throw new RefeicaoException("erro.recuperar.refeicoes.repositorio.refeicao.buscar");			
		}
	}

	
	
	public void adicionarRefeicao(RefeicaoVO refeicaoVO) throws RefeicaoException 
	{
				
		try
		{
			RepositorioRefeicao.getInstance().inserirOuAtualizar(refeicaoVO);
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new RefeicaoException("erro.adiconar.refeicao.repositorio.refeicao.inserirOuAtualizar");			
		}
				
	}
	
	public RefeicaoVO recuperarRefeicao(RefeicaoVO refeicaoVO) throws RefeicaoException 
	{
		Collection refeicaoVOs = null;
		try
		{
			refeicaoVOs =  RepositorioRefeicao.getInstance().buscar(refeicaoVO);
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new RefeicaoException("erro.recuperar.refeicoes.repositorio.refeicao.buscar");			
		}
		
		if (refeicaoVOs != null && !refeicaoVOs.isEmpty())
		{
			return (RefeicaoVO) refeicaoVOs.iterator().next();
		}
		else
		{
			return null;
		}	
	}

	public void atualizarRefeicao (RefeicaoVO refeicaoVO) throws RefeicaoException
	{

		RefeicaoVO refeicaoVOantigo = new RefeicaoVO();
		refeicaoVOantigo.setId(refeicaoVO.getId());
		refeicaoVOantigo = recuperarRefeicao(refeicaoVOantigo);

		if (refeicaoVOantigo != null)
		{
			refeicaoVO.setDescricao(refeicaoVOantigo.getDescricao());
			refeicaoVO.setOp_vegetariana(refeicaoVOantigo.getOp_vegetariana());
		}
		else
		{				
			throw new RefeicaoException("erro.atualizar.refeicao.nao.encontrado");	
		}	

		try
		{
			RepositorioRefeicao.getInstance().inserirOuAtualizar(refeicaoVO);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new RefeicaoException("erro.atualizar.refeicao.repositorio.inserirOuAtualizar");	
		}
	}
}