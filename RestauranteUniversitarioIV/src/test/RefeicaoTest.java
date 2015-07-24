package test;

import static org.junit.Assert.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.junit.Test;

import persistencia.ConnectionFactory;
import entidades.Refeicao;
import entidades.enumerados.Turno;
import entidades.exceptions.RefeicaoException;
import entidades.value_objects.RefeicaoVO;

public class RefeicaoTest {

	

	@Test
	public void testRecuperarRefeicoes() throws RefeicaoException {
		
		RefeicaoVO refeicaoVO = new RefeicaoVO(Turno.valueOf("TARDE"),"descricao","op_vegetariana");
		Collection refeicaos = Refeicao.getInstance().recuperarRefeicoes(refeicaoVO);
		assertNotNull(refeicaos);
	}

	@Test
	public void testAdicionarRefeicao() throws RefeicaoException, SQLException 
	{
		RefeicaoVO refeicaoVO = new RefeicaoVO(Turno.valueOf("TARDE"),"descricao","op_vegetariana");
		Refeicao.getInstance().adicionarRefeicao(refeicaoVO);
		
		PreparedStatement prep = ConnectionFactory.getConnection().prepareStatement("select * from refeicao where descricao like 'descricao'");
		ResultSet resultSet = prep.executeQuery();
		resultSet.next();
		String descricao = resultSet.getString("descricao");
		
		assertEquals(refeicaoVO.getDescricao(),descricao);
		
	}

	@Test
	public void testRecuperarRefeicao() throws RefeicaoException {
		RefeicaoVO refeicaoVO = new RefeicaoVO(Turno.valueOf("TARDE"),"descricao","op_vegetariana");
		refeicaoVO = Refeicao.getInstance().recuperarRefeicao(refeicaoVO);
		assertNotNull(refeicaoVO.getId());
	}

	@Test
	public void testAtualizarRefeicao() throws RefeicaoException, SQLException {

		RefeicaoVO refeicaoVO = new RefeicaoVO(Turno.valueOf("TARDE"),"descricao2","op_vegetariana");
		Refeicao.getInstance().atualizarRefeicao(refeicaoVO);
		
		PreparedStatement prep = ConnectionFactory.getConnection().prepareStatement("select * from refeicao where descricao like 'descricao2'");
		ResultSet resultSet = prep.executeQuery();
		resultSet.next();
		String descricao = resultSet.getString("descricao");
		
		assertEquals(refeicaoVO.getDescricao(),descricao);
		
	}

}