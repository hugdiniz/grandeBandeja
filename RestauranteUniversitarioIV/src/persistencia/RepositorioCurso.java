package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import entidades.value_objects.CursoVO;
import entidades.value_objects.DepartamentoVO;
import entidades.value_objects.VO;

public class RepositorioCurso extends Repositorio
{
	private static RepositorioCurso repositorio;
	
	private RepositorioCurso() {
		super();
	}
	
	public static RepositorioCurso getInstance()
	{
		if (repositorio == null) 
		{
			repositorio = new RepositorioCurso();
		}	
		return repositorio;
	}

	@Override
	public void inserirOuAtualizar(VO vo) throws SQLException
	{
		CursoVO cursoVO = (CursoVO) vo;
		Collection vars = new ArrayList();
		
		StringBuilder stringVars = new StringBuilder();
		
		StringBuilder campos = new StringBuilder();
		
		if (cursoVO.getId() != null)
		{
			campos.append("UPDATE curso SET (");
			stringVars.append(" = (");
		}
		else
		{
			campos.append("INSERT INTO curso (");
			stringVars.append("VALUES (");
			
		}	
		
		
		Boolean primeiroCampo = true;
		if (cursoVO.getNome()!= null && !cursoVO.getNome().equals(""))
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
			
			vars.add(cursoVO.getNome());
		}
		if (cursoVO.getSigla()!= null && !cursoVO.getSigla().equals(""))
		{			
			if (primeiroCampo)
			{
				primeiroCampo = false;
				campos.append("sigla");
				stringVars.append("?");
			}
			else 
			{
				campos.append(",sigla");
				stringVars.append(",?");
			}
			
			vars.add(cursoVO.getSigla());
		}
		
		if (cursoVO.getDepartamentoVO() != null && cursoVO.getDepartamentoVO().getId() != null)
		{			
			if (primeiroCampo)
			{
				primeiroCampo = false;
				campos.append("departamento_fk");
				stringVars.append("?");
			}
			else 
			{
				campos.append(",sigla");
				stringVars.append(",?");
			}
			
			vars.add(cursoVO.getDepartamentoVO().getId());
		}
		campos.append(") ");
		
		campos.append(stringVars);
		
		if (cursoVO.getId() != null)
		{
			campos.append(") WHERE id=?;");
			vars.add(cursoVO.getId());
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
		CursoVO cursoVO = (CursoVO) vo;
		Collection vars = new ArrayList();		
		StringBuilder sql = new StringBuilder();
		StringBuilder restricao = new StringBuilder();
		StringBuilder tabela = new StringBuilder();
		StringBuilder projecao = new StringBuilder();
		ResultSet resultSet = null;	
		
		projecao.append("select * ");		
		
		Boolean primeiroCampo = true;
		Boolean joinDepartamento = false;
		
		if (cursoVO.getNome()!= null && !cursoVO.getNome().equals(""))
		{			
			if (primeiroCampo)
			{
				primeiroCampo = false;
				restricao.append(" where curso.nome like ");
				restricao.append(" ? ");
			}
			else 
			{
				restricao.append(" and curso.nome like");
				restricao.append(" ? ");
			}
			
			vars.add(cursoVO.getNome());
		}
		if (cursoVO.getSigla()!= null && !cursoVO.getSigla().equals(""))
		{			
			if (primeiroCampo)
			{
				primeiroCampo = false;
				restricao.append(" where curso.sigla like ");
				restricao.append(" ? ");
			}
			else 
			{
				restricao.append("and curso.sigla like");
				restricao.append( " ? " );
			}
			
			vars.add(cursoVO.getSigla());
		}
		if (cursoVO.getDepartamentoVO() != null)
		{
			DepartamentoVO departamentoVO = cursoVO.getDepartamentoVO();
			
			if (departamentoVO.getNome()!= null && !departamentoVO.getNome().equals(""))
			{	
				joinDepartamento = true;
				
				if (primeiroCampo)
				{
					primeiroCampo = false;
					restricao.append(" where departamento.nome like ");
					restricao.append(" ? ");
				}
				else 
				{
					restricao.append(" and departamento.nome like");
					restricao.append(" ? ");
				}
				
				vars.add(departamentoVO.getNome());
			}
			if (departamentoVO.getSigla()!= null && !departamentoVO.getSigla().equals(""))
			{	
				joinDepartamento = true;
				
				if (primeiroCampo)
				{
					primeiroCampo = false;
					
					restricao.append(" where departamento.sigla like ");
					restricao.append(" ? ");
				}
				else 
				{
					restricao.append("and departamento.sigla like");
					restricao.append( " ? " );
				}
				
				vars.add(departamentoVO.getSigla());
			}
			if (departamentoVO.getId()!= null)
			{			
				if (primeiroCampo)
				{
					primeiroCampo = false;
					restricao.append(" where departamento.id like ");
					restricao.append(" ? ");
				}
				else 
				{
					restricao.append("and departamento.id like");
					restricao.append( " ? " );
				}
				
				vars.add(departamentoVO.getId());
			}	
		}
		
		/*
		 * Parte do Joins
		 */
		tabela.append("from curso ");
		if (joinDepartamento)
		{
			tabela.append(" join departamento on departamento.id = curso.departamento_fk");
		}
		
		
		sql.append(projecao);
		sql.append(tabela);
		sql.append(restricao);
		sql.append(";");
		
		System.out.println(sql);
		resultSet = executeSQL(sql.toString(), vars);		
		
		Collection vos = new ArrayList(); 
		while (resultSet.next()) 
		{
			CursoVO cursoVOSaida = new CursoVO();
			cursoVOSaida.setNome(resultSet.getString("nome"));
			cursoVOSaida.setId(resultSet.getLong("id"));
			cursoVOSaida.setSigla(resultSet.getString("sigla"));
			
			vos.add(cursoVOSaida);
			
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