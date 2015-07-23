package controladores.ccu;

import entidades.Consumidor;
import entidades.Curso;
import entidades.Departamento;
import entidades.exceptions.ConsumidorException;
import entidades.exceptions.CursoException;
import entidades.exceptions.DepartamentoException;
import entidades.value_objects.ConsumidorVO;
import entidades.value_objects.CursoVO;
import entidades.value_objects.DepartamentoVO;

public class GerirTickets
{
	private GerirTickets()
	{
		
	}
	
	private static GerirTickets gerirTickets;
	public static GerirTickets getInstance()
	{
		if (gerirTickets == null)
		{
			gerirTickets = new GerirTickets();
		}
		return gerirTickets;
	}
	
	
	public ConsumidorVO buscarConsumidor(ConsumidorVO consumidorVO) throws ConsumidorException
	{
		consumidorVO.setHabilitado(Boolean.TRUE);
		
		ConsumidorVO consumidorVOSaida = Consumidor.getInstance().recuperarConsumidor(consumidorVO);		
		
		return consumidorVOSaida;
	}
}
