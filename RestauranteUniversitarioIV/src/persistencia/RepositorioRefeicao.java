package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import entidades.value_objects.CursoVO;
import entidades.value_objects.DepartamentoVO;
import entidades.value_objects.RefeicaoVO;
import entidades.value_objects.VO;
import entidades.enumerados.Turno;

public class RepositorioRefeicao extends Repositorio {
	private static RepositorioRefeicao repositorio;

	public static RepositorioRefeicao getInstance() {
		if (repositorio == null) {
			repositorio = new RepositorioRefeicao();
		}
		return repositorio;
	}

	@Override
	public void inserirOuAtualizar(VO vo) throws SQLException {
		RefeicaoVO refeicaoVO = (RefeicaoVO) vo;
		Collection vars = new ArrayList();

		StringBuilder stringVars = new StringBuilder();

		StringBuilder campos = new StringBuilder();

		if (refeicaoVO.getId() != null) {
			campos.append("UPDATE refeicao SET (");
			stringVars.append(" = (");
		} else {
			campos.append("INSERT INTO refeicao (");
			stringVars.append("VALUES (");

		}

		Boolean primeiroCampo = true;
		if (refeicaoVO.getTurno() != null && !refeicaoVO.getTurno().equals("")) {
			if (primeiroCampo) {
				primeiroCampo = false;
				campos.append("turno");
				stringVars.append("?");
			} else {
				campos.append(",turno");
				stringVars.append(",?");
			}

			vars.add(refeicaoVO.getTurno().name());
		}
		if (refeicaoVO.getDescricao() != null
				&& !refeicaoVO.getDescricao().equals("")) {
			if (primeiroCampo) {
				primeiroCampo = false;
				campos.append("descricao");
				stringVars.append("?");
			} else {
				campos.append(",descricao");
				stringVars.append(",?");
			}

			vars.add(refeicaoVO.getDescricao());
		}
		if (refeicaoVO.getOp_vegetariana() != null
				&& !refeicaoVO.getOp_vegetariana().equals("")) {
			if (primeiroCampo) {
				primeiroCampo = false;
				campos.append("opcaoVegan");
				stringVars.append("?");
			} else {
				campos.append(",opcaoVegan");
				stringVars.append(",?");
			}

			vars.add(refeicaoVO.getOp_vegetariana());
		}
		campos.append(") ");

		campos.append(stringVars);

		if (refeicaoVO.getId() != null) {
			campos.append(") WHERE id=?;");
			vars.add(refeicaoVO.getId());
		} else {
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
		StringBuilder sql = new StringBuilder();
		StringBuilder restricao = new StringBuilder();
		StringBuilder tabela = new StringBuilder();
		StringBuilder projecao = new StringBuilder();
		ResultSet resultSet = null;	
		
		projecao.append("select * ");		
		
		Boolean primeiroCampo = true;
		Boolean joinDepartamento = false;
		
		if(refeicaoVO.getId()!= null && refeicaoVO.getDescricao() == null 
									 && refeicaoVO.getOp_vegetariana()== null
									 && refeicaoVO.getTurno() == null)
		{
			
			primeiroCampo = false;
			restricao.append(" where refeicao.id = ");
			restricao.append(" ? ");
			
			vars.add(refeicaoVO.getId());
		}
		else if(refeicaoVO.getId()!= null)
		{			
			primeiroCampo = false;
			restricao.append(" where refeicao.id = ");
			restricao.append(" ? ");
			
			vars.add(refeicaoVO.getId());
		}
		else
		{	
			if (refeicaoVO.getDescricao() != null && !refeicaoVO.getDescricao().equals(""))
			{			
				if (primeiroCampo)
				{
					primeiroCampo = false;
					restricao.append(" where refeicao.descricao like ");
					restricao.append(" ? ");
				}
				else 
				{
					restricao.append(" and refeicao.descricao like");
					restricao.append(" ? ");
				}
				
				vars.add(refeicaoVO.getDescricao());
			}
			if (refeicaoVO.getOp_vegetariana()!= null && !refeicaoVO.getOp_vegetariana().equals(""))
			{			
				if (primeiroCampo)
				{
					primeiroCampo = false;
					restricao.append(" where refeicao.opcaoVegan like ");
					restricao.append(" ? ");
				}
				else 
				{
					restricao.append("and refeicao.opcaoVegan like");
					restricao.append( " ? " );
				}
				
				vars.add(refeicaoVO.getOp_vegetariana());
			}
			if (refeicaoVO.getTurno() != null && !refeicaoVO.getTurno().name().equals(""))
			{
				if (primeiroCampo)
				{
					primeiroCampo = false;
					restricao.append(" where refeicao.turno like ");
					restricao.append(" ? ");
				}
				else 
				{
					restricao.append("and refeicao.turno like");
					restricao.append( " ? " );
				}
				
				vars.add(refeicaoVO.getTurno().name());
				
			}
		}
		
		/*
		 * Parte do Joins
		 */
		tabela.append("from refeicao ");
		//tabela.append(" join departamento on departamento.id = curso.departamento_fk");
		
		
		
		sql.append(projecao);
		sql.append(tabela);
		sql.append(restricao);
		sql.append(";");
		
		System.out.println(sql);
		resultSet = executeSQL(sql.toString(), vars);		
		
		Collection vos = new ArrayList(); 
		
		while (resultSet.next()) 
		{
			System.out.println(resultSet.getString("refeicao.descricao"));
			RefeicaoVO refeicaoSaida = new RefeicaoVO();
			refeicaoSaida.setDescricao(resultSet.getString("refeicao.descricao"));
			refeicaoSaida.setId(resultSet.getLong("refeicao.id"));
			refeicaoSaida.setOp_vegetariana(resultSet.getString("refeicao.opcaoVegan"));
			refeicaoSaida.setTurno(Turno.valueOf(resultSet.getString("refeicao.turno")));
			
			vos.add(refeicaoSaida);
			
		}
		
		return vos;
	}

	@Override
	public Collection remover(VO vo) {
		return null;
	}

}