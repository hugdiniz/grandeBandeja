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
	
	protected ResultSet executeSQL(String sql,Collection vars) throws SQLException
	{
		PreparedStatement prep = ConnectionFactory.getConnection().prepareStatement(sql);
		
		Integer posicao = 0;
		for (Object object : vars)
		{
			prep.setObject(posicao,object);
		}
		
		ResultSet rs = prep.executeQuery();
		return rs;
	}
	
	public abstract void inserirOuAtualizar(VO vo);
	public abstract Collection buscar(VO vo);
	public abstract Collection remover(VO vo);
}