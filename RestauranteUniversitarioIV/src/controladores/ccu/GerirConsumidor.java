package controladores.ccu;

import java.util.ArrayList;
import java.util.Collection;

import entidades.Aluno;
import entidades.Consumidor;
import entidades.Curso;
import entidades.Departamento;
import entidades.Funcionario;
import entidades.exceptions.AlunoException;
import entidades.exceptions.ConsumidorException;
import entidades.exceptions.CursoException;
import entidades.exceptions.DepartamentoException;
import entidades.exceptions.FuncionarioException;
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
	
	public Collection listarConsumidors() throws ConsumidorException
	{
		ConsumidorVO consumidorVO = new ConsumidorVO();
		consumidorVO.setHabilitado(true);
		Collection consumidors = Consumidor.getInstance().recuperarConsumidors(consumidorVO);
		if (consumidors == null)
		{
			consumidors = new ArrayList();
		}
		return consumidors;
		
	}
	public ConsumidorVO buscarConsumidor(ConsumidorVO consumidorVO) throws ConsumidorException, CursoException, DepartamentoException
	{
		ConsumidorVO consumidorVOSaida = Consumidor.getInstance().recuperarConsumidor(consumidorVO);
		if(consumidorVO.getIdCurso() != null)
		{
			CursoVO cursoVO = new CursoVO();
			cursoVO.setId(consumidorVO.getIdCurso());
			CursoVO cursoVOSaida = Curso.getInstance().recuperarCurso(cursoVO);
			consumidorVOSaida.setCursoVO(cursoVOSaida);
		}
		else
		{
			DepartamentoVO departamentoVO = new DepartamentoVO();
			departamentoVO.setId(consumidorVO.getIdCurso());
			DepartamentoVO cursoVOSaida = Departamento.getInstance().recuperarDepartamento(departamentoVO);
			consumidorVOSaida.setDepartamentoVO(cursoVOSaida);
		}	
		
		return consumidorVOSaida;
	}
	
	public Collection listarDepartamentos() throws DepartamentoException
	{
		Collection consumidors = Departamento.getInstance().recuperarDepartamentos(new DepartamentoVO());
		if (consumidors == null)
		{
			consumidors = new ArrayList();
		}
		return consumidors;

	}
	
	public Collection listarCursos() throws CursoException 
	{	
		Collection consumidors = Curso.getInstance().recuperarCursos(new CursoVO());
		if (consumidors == null)
		{
			consumidors = new ArrayList();
		}
		return consumidors;
	}
	
	public void criarConsumidor(ConsumidorVO consumidorVO) throws ConsumidorException 
	{
		consumidorVO.setAtualizar(Boolean.FALSE);
		consumidorVO.setHabilitado(Boolean.TRUE);
		if (consumidorVO.getIdCurso() != null)
		{
			Aluno.getInstance().manterAluno(consumidorVO);
		}
		else if(consumidorVO.getIdDepartamento() != null)
		{
			Funcionario.getInstance().manterFuncionario(consumidorVO);
		}	
		
	}
	
	public void atualizarConsumidor(ConsumidorVO consumidorVO) throws ConsumidorException 
	{
		consumidorVO.setAtualizar(Boolean.TRUE);
		consumidorVO.setHabilitado(Boolean.TRUE);
		if (consumidorVO.getIdCurso() != null)
		{
			Aluno.getInstance().manterAluno(consumidorVO);
		}
		else if(consumidorVO.getIdDepartamento() != null)
		{
			Funcionario.getInstance().manterFuncionario(consumidorVO);
		}
		else
		{
			throw new ConsumidorException("erro.");
		}	
		
	}
	
	public void excluirConsumidor(ConsumidorVO consumidorVO) throws ConsumidorException 
	{
		Consumidor.getInstance().removerConsumidor(consumidorVO);		
	}
}
