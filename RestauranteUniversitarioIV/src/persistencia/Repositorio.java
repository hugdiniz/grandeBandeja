package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import entidades.value_objects.VO;

public abstract class Repositorio
{
		 
	protected Repositorio() {
		
	}
	
	private PreparedStatement createPreparedStatement(String sql,Collection vars) throws SQLException
	{
		PreparedStatement prep = ConnectionFactory.getConnection().prepareStatement(sql);
		
		Integer posicao = 1;
		for (Object object : vars)
		{
			prep.setObject(posicao,object);
			posicao++;
		}
	
		return prep;
	}
	protected void insertOrUpdateSQL(String sql,Collection vars) throws SQLException 
	{
		createPreparedStatement(sql, vars).executeUpdate();
	}
	protected ResultSet executeSQL(String sql,Collection vars) throws SQLException 
	{
		return createPreparedStatement(sql, vars).executeQuery();
	}
	
	public abstract void inserirOuAtualizar(VO vo) throws SQLException;
	public abstract Collection buscar(VO vo) throws SQLException;
	public abstract Collection remover(VO vo) throws SQLException;
}