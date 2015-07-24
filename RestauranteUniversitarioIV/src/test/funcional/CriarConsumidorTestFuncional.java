package test.funcional;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.h2.H2DataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;

import controladores.ccu.GerirConsumidor;
import controladores.ccu.GerirCurso;
import entidades.Consumidor;
import entidades.Departamento;
import entidades.enumerados.SexoEnum;
import entidades.enumerados.TituloEnum;
import entidades.value_objects.ConsumidorVO;
import entidades.value_objects.CursoVO;
import entidades.value_objects.DepartamentoVO;

public class CriarConsumidorTestFuncional  extends DBTestCase
{
	private FlatXmlDataSet bancoCarregado;

	@Before
	public void setUp() throws Exception
	{
		System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.h2.Driver" );
		System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:h2:file:~/jerry" );
		System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "admin" );
		System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "foca" );

	}

	public void testCriarConsumidor() throws Exception
	{
		DepartamentoVO departamentoVO = new DepartamentoVO();
		departamentoVO.setNome("Departamento de Ciencia da Computacao3");
		departamentoVO.setSigla("DCC3");
		Departamento.getInstance().adicionarDepartamento(departamentoVO);
		departamentoVO = Departamento.getInstance().recuperarDepartamento(departamentoVO);
		
		CursoVO cursoVO = new CursoVO();
		cursoVO.setDepartamentoVO(departamentoVO);
		cursoVO.setNome("Ciencia da Computacao");
		cursoVO.setSigla("CC");
		GerirCurso.criarCurso(cursoVO);
		cursoVO = GerirCurso.buscarCurso(new CursoVO());
		ConsumidorVO consumidorVO = new ConsumidorVO();
		consumidorVO.setNome("nome");
		consumidorVO.setMatricula("2011785123");
		consumidorVO.setCpf("37951129666");
		consumidorVO.setHabilitado(Boolean.TRUE);
		consumidorVO.setAnoIngresso("2011");
		consumidorVO.setSexo(SexoEnum.FEMININO);
		consumidorVO.setTitulo(TituloEnum.ESPECIALIZACAO);
		
		consumidorVO.setIdCurso(cursoVO.getId());
		
		GerirConsumidor.getInstance().criarConsumidor(consumidorVO);
		
		IDataSet dadosSetBanco1 = getConnection().createDataSet();
		ITable dadosNoBanco1 = dadosSetBanco1.getTable("consumidor");

		//remove coluna da tabela.
		ITable filteredTable1 = DefaultColumnFilter.excludedColumnsTable(dadosNoBanco1, new String[]{"id"});

		IDataSet dadosSetEsperado1 = new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/funcional/gerirDepartamentoDataset.xml"));
		ITable dadosEsperados1 = dadosSetEsperado1.getTable("consumidor");

		Assertion.assertEquals(dadosEsperados1, filteredTable1);
	}

	//Antes de executar o teste.
	protected DatabaseOperation getSetUpOperation() throws Exception
	{
		return DatabaseOperation.REFRESH;		
	}

	
	protected DatabaseOperation getTearDownOperation() throws Exception
	{		
		return DatabaseOperation.NONE;
		
	}

	@Override
	protected void setUpDatabaseConfig(DatabaseConfig config)
	{
		config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new H2DataTypeFactory());		
	}

	@Override
	protected IDataSet getDataSet() throws Exception
	{
		bancoCarregado = new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/funcional/gerirDepartamentoDataset.xml"));
		return bancoCarregado;
	}
}
