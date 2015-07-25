package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import entidades.enumerados.SexoEnum;
import entidades.enumerados.TituloEnum;
import entidades.value_objects.ConsumidorVO;
import entidades.value_objects.CursoVO;
import entidades.value_objects.DepartamentoVO;
import entidades.value_objects.VO;

public class RepositorioConsumidor extends Repositorio
{
	private static RepositorioConsumidor repositorio;
	
	private RepositorioConsumidor()
	{
		super();
	}
	
	public static RepositorioConsumidor getInstance()
	{
		if (repositorio == null) 
		{
			repositorio = new RepositorioConsumidor();
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
			campos.append("UPDATE consumidor SET (");
			stringVars.append(" = (");
		}
		else
		{
			campos.append("INSERT INTO consumidor (");
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
		if (consumidorVO.getNome()!= null && !consumidorVO.getNome().equals(""))
		{			
			if (primeiroCampo)
			{
				primeiroCampo = false;
				campos.append("nome");
				stringVars.append("?");
			}
			else 
			{
				campos.append(",nome");
				stringVars.append(",?");
			}
			
			vars.add(consumidorVO.getNome());
		}
		if (consumidorVO.getMatricula()!= null && !consumidorVO.getMatricula().equals(""))
		{			
			if (primeiroCampo)
			{
				primeiroCampo = false;
				campos.append("matricula");
				stringVars.append("?");
			}
			else 
			{
				campos.append(",matricula");
				stringVars.append(",?");
			}
			
			vars.add(consumidorVO.getMatricula());
		}

		if (consumidorVO.getAnoIngresso()!= null && !consumidorVO.getAnoIngresso().equals(""))
		{			
			if (primeiroCampo)
			{
				primeiroCampo = false;
				campos.append("anoIngresso");
				stringVars.append("?");
			}
			else 
			{
				campos.append(",anoIngresso");
				stringVars.append(",?");
			}
			
			vars.add(consumidorVO.getAnoIngresso());
		}
		if (consumidorVO.getSexo()!= null && !consumidorVO.getSexo().equals(""))
		{			
			if (primeiroCampo)
			{
				primeiroCampo = false;
				campos.append("sexo");
				stringVars.append("?");
			}
			else 
			{
				campos.append(",sexo");
				stringVars.append(",?");
			}
			
			vars.add(consumidorVO.getSexo().name());
		}
		if (consumidorVO.getTitulo()!= null && !consumidorVO.getTitulo().equals(""))
		{			
			if (primeiroCampo)
			{
				primeiroCampo = false;
				campos.append("titulo");
				stringVars.append("?");
			}
			else 
			{
				campos.append(",titulo");
				stringVars.append(",?");
			}
			
			vars.add(consumidorVO.getTitulo().name());
		}
		if (consumidorVO.getCpf()!= null && !consumidorVO.getCpf().equals(""))
		{			
			if (primeiroCampo)
			{
				primeiroCampo = false;
				campos.append("cpf");
				stringVars.append("?");
			}
			else 
			{
				campos.append(",cpf");
				stringVars.append(",?");
			}
			
			vars.add(consumidorVO.getCpf());
		}
		if (consumidorVO.getHabilitado()!= null && !consumidorVO.getHabilitado().equals(""))
		{			
			if (primeiroCampo)
			{
				primeiroCampo = false;
				campos.append("habilitado");
				stringVars.append("?");
			}
			else 
			{
				campos.append(",habilitado");
				stringVars.append(",?");
			}
			
			vars.add(consumidorVO.getHabilitado());
		}

		
		
		
		campos.append(") ");
		
		campos.append(stringVars);
		
		if (consumidorVO.getAtualizar() != null && consumidorVO.getAtualizar())
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
		
		
		if (consumidorVO.getId() != null && !consumidorVO.getId().equals(""))
		{			
			if (primeiroCampo)
			{
				primeiroCampo = false;
				restricao.append(" where consumidor.id like ");
				restricao.append(" ? ");
			}
			else 
			{
				restricao.append(" and consumidor.id like");
				restricao.append(" ? ");
			}
			
			vars.add(consumidorVO.getId());
		}
		if (consumidorVO.getNome()!= null && !consumidorVO.getNome().equals(""))
		{			
			if (primeiroCampo)
			{
				primeiroCampo = false;
				restricao.append(" where consumidor.nome like ");
				restricao.append(" ? ");
			}
			else 
			{
				restricao.append(" and consumidor.nome like");
				restricao.append(" ? ");
			}
			
			vars.add(consumidorVO.getNome());
		}
		if (consumidorVO.getHabilitado()!= null && !consumidorVO.getHabilitado().equals(""))
		{			
			if (primeiroCampo)
			{
				primeiroCampo = false;
				restricao.append(" where consumidor.habilitado like ");
				restricao.append(" ? ");
			}
			else 
			{
				restricao.append(" and consumidor.habilitado like");
				restricao.append(" ? ");
			}
			
			vars.add(consumidorVO.getHabilitado());
		}
		if (consumidorVO.getMatricula()!= null && !consumidorVO.getMatricula().equals(""))
		{			
			if (primeiroCampo)
			{
				primeiroCampo = false;
				restricao.append(" where consumidor.matricula like ");
				restricao.append(" ? ");
			}
			else 
			{
				restricao.append(" and consumidor.matricula like");
				restricao.append(" ? ");
			}
			
			vars.add(consumidorVO.getMatricula());
		}
		
		/*
		 * Parte do Joins
		 */
		tabela.append("from consumidor ");
		tabela.append(" left join funcionario on funcionario.id = consumidor.id ");
		tabela.append(" left join aluno on aluno.id = consumidor.id ");
		tabela.append(" left join curso on aluno.curso_fk = curso.id ");
		tabela.append(" left join departamento on funcionario.departamento_fk = departamento.id ");
		
		
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
			consumidorVOSaida.setId(resultSet.getLong("consumidor.id"));
			consumidorVOSaida.setAnoIngresso(resultSet.getString("consumidor.anoIngresso"));
			consumidorVOSaida.setNome(resultSet.getString("consumidor.nome"));
			consumidorVOSaida.setMatricula(resultSet.getString("consumidor.matricula"));
			consumidorVOSaida.setCpf(resultSet.getString("consumidor.cpf"));
			consumidorVOSaida.setTitulo(TituloEnum.valueOf(resultSet.getString("consumidor.titulo")));
			consumidorVOSaida.setSexo(SexoEnum.valueOf(resultSet.getString("consumidor.sexo")));
			
			CursoVO cursoVO = new CursoVO();
			cursoVO.setId(resultSet.getLong("curso.id"));
			cursoVO.setNome(resultSet.getString("curso.nome"));
			cursoVO.setSigla(resultSet.getString("curso.sigla"));
			consumidorVOSaida.setCursoVO(cursoVO);
			
			DepartamentoVO departamentoVO = new DepartamentoVO();
			
			departamentoVO.setId(resultSet.getLong("departamento.id"));
			departamentoVO.setNome(resultSet.getString("departamento.nome"));
			departamentoVO.setSigla(resultSet.getString("departamento.sigla"));
			consumidorVOSaida.setDepartamentoVO(departamentoVO);
			
			vos.add(consumidorVOSaida);
			
		}
		
		return vos;
	}

	@Override
	public Collection remover(VO vo) throws SQLException
	{
		String sql = "UPDATE consumidor SET (habilitado) = (false) where id =" + ((ConsumidorVO)vo).getId();
		executeSQL(sql, new ArrayList());
		return null;
	}

	
}