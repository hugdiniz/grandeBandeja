package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import entidades.value_objects.ConsumidorVO;
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
		
		if (consumidorVO.getId() != null)
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
			
			vars.add(consumidorVO.getSexo());
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
			
			vars.add(consumidorVO.getTitulo());
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
		
		if (consumidorVO.getNome()!= null && !consumidorVO.getNome().equals(""))
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
			
			vars.add(consumidorVO.getNome());
		}
		
		
		/*
		 * Parte do Joins
		 */
		tabela.append("from consumidor ");
		//tabela.append(" join departamento on departamento.id = consumidor.departamento_fk");
		
		
		
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
			consumidorVOSaida.setIdDepartamento(resultSet.getLong("consumidor.curso_fk"));
						
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