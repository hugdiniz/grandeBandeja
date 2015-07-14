package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import entidades.value_objects.ConsumidorVO;
import entidades.value_objects.VO;

public class RepositorioAluno extends Repositorio
{
	private static RepositorioAluno repositorio;
	
	private RepositorioAluno()
	{
		super();
	}
	
	public static RepositorioAluno getInstance()
	{
		if (repositorio == null) 
		{
			repositorio = new RepositorioAluno();
		}	
		return repositorio;
	}

	@Override
	public void inserirOuAtualizar(VO vo) throws SQLException
	{
		ConsumidorVO consumidorVO = (ConsumidorVO) vo;
		Collection vars = new ArrayList();
		
		StringBuilder stringVars = new StringBuilder();
		
		StringBuilder campos = new StringBuilder();
		
		if (consumidorVO.getAtualizar() != null && consumidorVO.getAtualizar())
		{
			campos.append("UPDATE aluno SET (");
			stringVars.append(" = (");
		}
		else
		{
			campos.append("INSERT INTO aluno (");
			stringVars.append("VALUES (");
			
		}	
		
		
		Boolean primeiroCampo = true;
		if (consumidorVO.getId()!= null && !consumidorVO.getId().equals(""))
		{			
			if (primeiroCampo)
			{
				primeiroCampo = false;
				campos.append("id");
				stringVars.append("?");
			}
			else 
			{
				campos.append(",id");
				stringVars.append(",?");
			}
			
			vars.add(consumidorVO.getId());
		}
		if (consumidorVO.getIdCurso()!= null && !consumidorVO.getIdCurso().equals(""))
		{			
			if (primeiroCampo)
			{
				primeiroCampo = false;
				campos.append("curso_fk");
				stringVars.append("?");
			}
			else 
			{
				campos.append(",curso_fk");
				stringVars.append(",?");
			}
			
			vars.add(consumidorVO.getIdCurso());
		}
		
		campos.append(") ");
		
		campos.append(stringVars);
		
		if (consumidorVO.getId() != null)
		{
			campos.append(") WHERE id=?;");
			vars.add(consumidorVO.getId());
		}
		else
		{
			campos.append(");");
		}
		
		
		System.out.println(campos);
		insertOrUpdateSQL(campos.toString(), vars);	
		
	}

	@Override
	public Collection buscar(VO vo) throws SQLException
	{
		ConsumidorVO consumidorVO = (ConsumidorVO) vo;
		Collection vars = new ArrayList();		
		StringBuilder sql = new StringBuilder();
		StringBuilder restricao = new StringBuilder();
		StringBuilder tabela = new StringBuilder();
		StringBuilder projecao = new StringBuilder();
		ResultSet resultSet = null;	
		
		projecao.append("select * ");		
		
		Boolean primeiroCampo = true;
		Boolean joinDepartamento = false;
		
		if (consumidorVO.getId()!= null && !consumidorVO.getId().equals(""))
		{			
			if (primeiroCampo)
			{
				primeiroCampo = false;
				restricao.append(" where aluno.id like ");
				restricao.append(" ? ");
			}
			else 
			{
				restricao.append(" and aluno.id like");
				restricao.append(" ? ");
			}
			
			vars.add(consumidorVO.getId());
		}
		if (consumidorVO.getIdCurso()!= null && !consumidorVO.getIdCurso().equals(""))
		{			
			if (primeiroCampo)
			{
				primeiroCampo = false;
				restricao.append(" where aluno.curso_fk like ");
				restricao.append(" ? ");
			}
			else 
			{
				restricao.append(" and aluno.curso_fk like");
				restricao.append(" ? ");
			}
			
			vars.add(consumidorVO.getIdCurso());
		}
		
		
		/*
		 * Parte do Joins
		 */
		tabela.append("from aluno ");
		//tabela.append(" join departamento on departamento.id = aluno.departamento_fk");
		
		
		
		sql.append(projecao);
		sql.append(tabela);
		sql.append(restricao);
		sql.append(";");
		
		System.out.println(sql);
		resultSet = executeSQL(sql.toString(), vars);		
		
		Collection vos = new ArrayList(); 
		while (resultSet.next()) 
		{
			ConsumidorVO consumidorVOSaida = new ConsumidorVO();
			consumidorVOSaida.setId(resultSet.getLong("aluno.id"));
			consumidorVOSaida.setIdDepartamento(resultSet.getLong("aluno.curso_fk"));
						
			vos.add(consumidorVOSaida);
			
		}
		
		return vos;
	}

	@Override
	public Collection remover(VO vo) throws SQLException
	{
		// TODO Auto-generated method stub
		return null;
	}

	
}