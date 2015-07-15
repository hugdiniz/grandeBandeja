package test;

import static org.junit.Assert.*;

import org.junit.Test;

import entidades.Refeicao;
import entidades.enumerados.Turno;
import entidades.exceptions.RefeicaoException;
import entidades.value_objects.RefeicaoVO;

public class RefeicaoTest {

	@Test
	public void testGetInstance() {
		RefeicaoVO refeicaoVO = new RefeicaoVO(Turno.valueOf("TARDE"),"descricao","op_vegetariana");
		assertNotNull(refeicaoVO);
	}

	@Test
	public void testRecuperarRefeicoes() throws RefeicaoException {
		
		RefeicaoVO refeicaoVO = new RefeicaoVO(Turno.valueOf("TARDE"),"descricao","op_vegetariana");
		Refeicao.getInstance().recuperarRefeicao(refeicaoVO);
		assertNotNull(refeicaoVO);
	}

	@Test
	public void testAdicionarRefeicao() throws RefeicaoException {
		RefeicaoVO refeicaoVO = new RefeicaoVO(Turno.valueOf("TARDE"),"descricao","op_vegetariana");
		Refeicao.getInstance().adicionarRefeicao(refeicaoVO);
		assertNotNull(refeicaoVO);
	}

	@Test
	public void testRecuperarRefeicao() throws RefeicaoException {
		RefeicaoVO refeicaoVO = new RefeicaoVO(Turno.valueOf("TARDE"),"descricao","op_vegetariana");
		Refeicao.getInstance().recuperarRefeicao(refeicaoVO);
		assertNotNull(refeicaoVO);
	}

	@Test
	public void testAtualizarRefeicao() throws RefeicaoException {

		RefeicaoVO refeicaoVO = new RefeicaoVO(Turno.valueOf("TARDE"),"descricao","op_vegetariana");
		Refeicao.getInstance().atualizarRefeicao(refeicaoVO);
		assertNotNull(refeicaoVO);
		
	}

}