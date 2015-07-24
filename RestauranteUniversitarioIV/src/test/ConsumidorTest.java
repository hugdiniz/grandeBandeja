package test;
import static org.junit.Assert.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.Test;
import entidades.Consumidor;
import entidades.Departamento;
import entidades.enumerados.SexoEnum;
import entidades.enumerados.TituloEnum;
import entidades.exceptions.ConsumidorException;
import entidades.exceptions.DepartamentoException;
import entidades.value_objects.ConsumidorVO;
import entidades.value_objects.DepartamentoVO;
import persistencia.ConnectionFactory;

public class ConsumidorTest {

	public void testGetInstance() 
	{

		ConsumidorVO consumidorVO = new ConsumidorVO();
		consumidorVO.setCpf("73117906118");
		consumidorVO.setIdCurso(new Long(123));
		consumidorVO.setIdDepartamento(new Long(1));
		consumidorVO.setNome("José Silva");
		consumidorVO.setMatricula("2015780000");
		consumidorVO.setSexo(SexoEnum.MASCULINO);
		consumidorVO.setTitulo(TituloEnum.ESPECIALIZACAO);
		consumidorVO.setAnoIngresso("2015");

		assertNotNull(consumidorVO);

	}
	@Test
	public void testRecuperarConsumidor() throws ConsumidorException, SQLException {

		ConsumidorVO consumidorVO = new ConsumidorVO();
		consumidorVO.setMatricula("2015780000");
		consumidorVO = Consumidor.getInstance().recuperarConsumidor(consumidorVO);
		assertNotNull(consumidorVO);
		assertEquals( "2015780000",consumidorVO.getMatricula());

	}
	@Test
	public void testRecuperarConsumidors() throws ConsumidorException {

		ConsumidorVO consumidorVO = new ConsumidorVO();
		Consumidor.getInstance().recuperarConsumidors(consumidorVO);
		assertNotNull(consumidorVO);
	}
	@Test
	public void testAdicionarConsumidor() throws ConsumidorException, SQLException {

		ConsumidorVO consumidorVO = new ConsumidorVO();
		consumidorVO.setCpf("73117906118");
		consumidorVO.setIdCurso(new Long(123));
		//consumidorVO.setIdDepartamento(new Long(1));
		consumidorVO.setNome("José Silva");
		consumidorVO.setMatricula("2015780000");
		consumidorVO.setSexo(SexoEnum.MASCULINO);
		consumidorVO.setTitulo(TituloEnum.ESPECIALIZACAO);
		consumidorVO.setAnoIngresso("2015");

		assertNotNull(consumidorVO);
		assertEquals( "José Silva", consumidorVO.getNome());
		assertEquals( "2015780000", consumidorVO.getMatricula());
		assertEquals( "2015", consumidorVO.getAnoIngresso());

		String matricula = null;

		try 
		{
			Consumidor.getInstance().manterConsumidor(consumidorVO);	
			PreparedStatement prep = ConnectionFactory.getConnection().prepareStatement("select * from consumidor where matricula like '2015780000'");
			ResultSet resultSet = prep.executeQuery();
			resultSet.next();
			matricula = resultSet.getString("matricula");
			assertEquals(consumidorVO.getMatricula(),matricula);

		} 
		catch (ConsumidorException e)
		{
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@Test
	public void testAtualizarConsumidor() throws ConsumidorException, SQLException {

		String nome = null;
		ConsumidorVO consumidorVO = new ConsumidorVO();

		consumidorVO.setCpf("59920837407");
		consumidorVO.setIdCurso(new Long(123));
		consumidorVO.setNome("Maria Silva");
		consumidorVO.setMatricula("2015780000");
		consumidorVO.setSexo(SexoEnum.FEMININO);
		consumidorVO.setTitulo(TituloEnum.ESPECIALIZACAO);
		consumidorVO.setAnoIngresso("2015");

		try 
		{
			Consumidor.getInstance().manterConsumidor(consumidorVO);

			PreparedStatement prep = ConnectionFactory.getConnection().prepareStatement("select * from consumidor where matricula like '2015780000'");			
			ResultSet resultSet = prep.executeQuery();
			resultSet.next();
			nome = resultSet.getString("nome");
			
			assertEquals( SexoEnum.FEMININO, consumidorVO.getSexo());
			assertEquals(consumidorVO.getNome() ,nome);
		}
		catch (ConsumidorException e)
		{
			throw new RuntimeException(e.getMessage(), e);
		}


	}

}