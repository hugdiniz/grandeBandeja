package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import entidades.value_objects.RefeicaoVO;
import entidades.value_objects.VO;

public class RepositorioRefeicao extends Repositorio
{
	private static RepositorioRefeicao repositorio;
	
	
	public static RepositorioRefeicao getInstance()
	{
		if (repositorio == null) 
		{
			repositorio = new RepositorioRefeicao();
		}
		return repositorio;
	}

	@Override
	public void inserirOuAtualizar(VO vo) throws SQLException
	{
		RefeicaoVO refeicaoVO = (RefeicaoVO) vo;
		Collection vars = new ArrayList();
		
		StringBuilder stringVars = new StringBuilder();
		
		StringBuilder campos = new StringBuilder();
		
		if (refeicaoVO.getId() != null)
		{
			campos.append("UPDATE refeicao SET (");
			stringVars.append(" = (");
		}
		else
		{
			campos.append("INSERT INTO refeicao (");
			stringVars.append("VALUES (");
			
		}	
		
		
		Boolean primeiroCampo = true;
		if (refeicaoVO.getTurno()!= null && !refeicaoVO.getTurno().equals(""))
		{			
			if (primeiroCampo)
			{
				primeiroCampo = false;
				campos.append("turno");
				stringVars.append("?");
			}
			else 
			{
				campos.append(",turno");
				stringVars.append(",?");
			}
			
			vars.add(refeicaoVO.getTurno());
		}
		if (refeicaoVO.getDescricao()!= null && !refeicaoVO.getDescricao().equals(""))
		{			
			if (primeiroCampo)
			{
				primeiroCampo = false;
				campos.append("descricao");
				stringVars.append("?");
			}
			else 
			{
				campos.append(",descricao");
				stringVars.append(",?");
			}
			
			vars.add(refeicaoVO.getDescricao());
		}
		if (refeicaoVO.getOp_vegetariana()!= null && !refeicaoVO.getOp_vegetariana().equals(""))
		{			
			if (primeiroCampo)
			{
				primeiroCampo = false;
				campos.append("op_vegetariana");
				stringVars.append("?");
			}
			else 
			{
				campos.append(",op_vegetariana");
				stringVars.append(",?");
			}
			
			vars.add(refeicaoVO.getOp_vegetariana());
		}
		campos.append(") ");
		
		campos.append(stringVars);
		
		if (refeicaoVO.getId() != null)
		{
			campos.append(") WHERE id=?;");
			vars.add(refeicaoVO.getId());
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
		RefeicaoVO refeicaoVO = (RefeicaoVO) vo;
		Collection vars = new ArrayList();
		
		StringBuilder campos = new StringBuilder();
		
		campos.append("select * from refeicao ");
		ResultSet resultSet = null;	
		
		
		Boolean primeiroCampo = true;
		if (refeicaoVO.getTurno()!= null && !refeicaoVO.getTurno().equals(""))
		{			
			if (primeiroCampo)
			{
				primeiroCampo = false;
				campos.append(" where turno like ");
				campos.append(" ? ");
			}
			else 
			{
				campos.append(" and turno like");
				campos.append(" ? ");
			}
			
			vars.add(refeicaoVO.getTurno());
		}
		if (refeicaoVO.getDescricao()!= null && !refeicaoVO.getDescricao().equals(""))
		{			
			if (primeiroCampo)
			{
				primeiroCampo = false;
				campos.append(" where descricao like ");
				campos.append(" ? ");
			}
			else 
			{
				campos.append("and descricao like");
				campos.append( " ? " );
			}
			
			vars.add(refeicaoVO.getDescricao());
		}
		if (refeicaoVO.getOp_vegetariana()!= null && !refeicaoVO.getOp_vegetariana().equals(""))
		{			
			if (primeiroCampo)
			{
				primeiroCampo = false;
				campos.append(" where op_vegetariana like ");
				campos.append(" ? ");
			}
			else 
			{
				campos.append("and op_vegetariana like");
				campos.append( " ? " );
			}
			
			vars.add(refeicaoVO.getOp_vegetariana());
		}
		if (refeicaoVO.getId()!= null)
		{			
			if (primeiroCampo)
			{
				primeiroCampo = false;
				campos.append(" where id like ");
				campos.append(" ? ");
			}
			else 
			{
				campos.append("and id like");
				campos.append( " ? " );
			}
			
			vars.add(refeicaoVO.getId());
		}	
		
		campos.append(";");
		System.out.println(campos);
		resultSet = executeSQL(campos.toString(), vars);		
		
		Collection vos = new ArrayList(); 
		while (resultSet.next()) 
		{
			RefeicaoVO refeicaoVOSaida = new RefeicaoVO();
			refeicaoVOSaida.setTurno(resultSet.getString("turno"));
			refeicaoVOSaida.setDescricao(resultSet.getString("descricao"));
			refeicaoVOSaida.setOp_vegetariana(resultSet.getString("op_vegetariana"));
			refeicaoVOSaida.setId(resultSet.getLong("id"));
			
			
			vos.add(refeicaoVOSaida);
			
		}
		
		return vos;
	}


	@Override
	public Collection remover(VO vo) 
	{		
		return null;
	}
	
}