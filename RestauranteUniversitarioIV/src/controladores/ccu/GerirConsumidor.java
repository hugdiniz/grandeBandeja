package controladores.ccu;

import java.util.Collection;

import entidades.Consumidor;
import entidades.Curso;
import entidades.Departamento;
import entidades.exceptions.ConsumidorException;
import entidades.exceptions.CursoException;
import entidades.exceptions.DepartamentoException;
import entidades.value_objects.ConsumidorVO;
import entidades.value_objects.CursoVO;
import entidades.value_objects.DepartamentoVO;

public class GerirConsumidor
{
	private static GerirConsumidor gerirConsumidor;
	public static GerirConsumidor getInstance()
	{
		if (gerirConsumidor == null)
		{
			gerirConsumidor = new GerirConsumidor();
		}
		return gerirConsumidor;
	}
	
	/*public Collection listarConsumidors() throws ConsumidorException
	{
		return Consumidor.getInstance().recuperarConsumidors(new ConsumidorVO());
	}*/
	
	public Collection listarDepartamentos() throws DepartamentoException
	{
		return Departamento.getInstance().recuperarDepartamentos(new DepartamentoVO());
	}
	
	public static Object listarCursos() throws CursoException 
	{		
		return Curso.getInstance().recuperarCursos(new CursoVO());
	}
	
	/*public ConsumidorVO buscarConsumidor(ConsumidorVO departamentoVO) throws ConsumidorException
	{			
		ConsumidorVO departamentoAntigo = Consumidor.getInstance().recuperarConsumidor(departamentoVO);		
		
		return departamentoAntigo;
	}	
	
	public void criarConsumidor(ConsumidorVO departamentoVO) throws ConsumidorException 
	{
		Consumidor.getInstance().adicionarConsumidor(departamentoVO);
	}
	
	public void atualizarConsumidor(ConsumidorVO departamentoVO) throws ConsumidorException
	{
		Consumidor.getInstance().atualizarConsumidor(departamentoVO);
	}*/
}
