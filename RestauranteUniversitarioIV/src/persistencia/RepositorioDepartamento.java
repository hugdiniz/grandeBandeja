package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import entidades.value_objects.DepartamentoVO;
import entidades.value_objects.VO;

public class RepositorioDepartamento extends Repositorio
{
	private static RepositorioDepartamento repositorio;
	
	
	public static RepositorioDepartamento getInstance()
	{
		if (repositorio == null) 
		{
			repositorio = new RepositorioDepartamento();
		}
		return repositorio;
	}

	@Override
	public void inserirOuAtualizar(VO vo) throws SQLException
	{
		DepartamentoVO departamentoVO = (DepartamentoVO) vo;
		Collection vars = new ArrayList();
		
		StringBuilder stringVars = new StringBuilder();
		
		StringBuilder campos = new StringBuilder();
		
		if (departamentoVO.getId() != null)
		{
			campos.append("UPDATE departamento SET (");
			stringVars.append(" = (");
		}
		else
		{
			campos.append("INSERT INTO departamento (");
			stringVars.append("VALUES (");
			
		}	
		
		
		Boolean primeiroCampo = true;
		if (departamentoVO.getNome()!= null && !departamentoVO.getNome().equals(""))
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
			
			vars.add(departamentoVO.getNome());
		}
		if (departamentoVO.getSigla()!= null && !departamentoVO.getSigla().equals(""))
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
			
			vars.add(departamentoVO.getSigla());
		}
		campos.append(") ");
		
		campos.append(stringVars);
		
		if (departamentoVO.getId() != null)
		{
			campos.append(") WHERE id=?;");
			vars.add(departamentoVO.getId());
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
		DepartamentoVO departamentoVO = (DepartamentoVO) vo;
		Collection vars = new ArrayList();
		
		StringBuilder campos = new StringBuilder();
		
		campos.append("select * from departamento ");
		ResultSet resultSet = null;	
		
		
		Boolean primeiroCampo = true;
		if (departamentoVO.getNome()!= null && !departamentoVO.getNome().equals(""))
		{			
			if (primeiroCampo)
			{
				primeiroCampo = false;
				campos.append(" where nome like ");
				campos.append(" ? ");
			}
			else 
			{
				campos.append(" and nome like");
				campos.append(" ? ");
			}
			
			vars.add(departamentoVO.getNome());
		}
		if (departamentoVO.getSigla()!= null && !departamentoVO.getSigla().equals(""))
		{			
			if (primeiroCampo)
			{
				primeiroCampo = false;
				campos.append(" where sigla like ");
				campos.append(" ? ");
			}
			else 
			{
				campos.append("and sigla like");
				campos.append( " ? " );
			}
			
			vars.add(departamentoVO.getSigla());
		}	
		
		campos.append(";");
		System.out.println(campos);
		resultSet = executeSQL(campos.toString(), vars);		
		
		Collection vos = new ArrayList(); 
		while (resultSet.next()) 
		{
			DepartamentoVO departamentoVOSaida = new DepartamentoVO();
			departamentoVOSaida.setNome(resultSet.getString("nome"));
			departamentoVOSaida.setId(resultSet.getLong("id"));
			departamentoVOSaida.setSigla(resultSet.getString("sigla"));
			
			vos.add(departamentoVOSaida);
			
		}
		
		return vos;
	}


	@Override
	public Collection remover(VO vo) 
	{		
		return null;
	}
	
}