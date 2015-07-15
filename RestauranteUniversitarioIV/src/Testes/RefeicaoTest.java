package Testes;

import static org.junit.Assert.*;

import org.junit.Test;

import entidades.Refeicao;
import entidades.value_objects.DepartamentoVO;
import entidades.value_objects.RefeicaoVO;

import entidades.enumerados.*;
public class RefeicaoTest {

	@Test
	public void testGetInstance() {
		
		
		RefeicaoVO refeicaoVO = new RefeicaoVO(Turno.valueOf("MANHA"),"descricao", "op_vegetariana");
		assertNotNull(refeicaoVO);
			
		
	}

	
}
