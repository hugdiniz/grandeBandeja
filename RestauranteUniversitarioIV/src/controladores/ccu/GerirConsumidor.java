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
		Collection consumidors = Aluno.getInstance().recuperarConsumidors(consumidorVO);
		if (consumidors == null)
		{
			consumidors = new ArrayList();
		}
		return consumidors;
		
	}
	public ConsumidorVO buscarConsumidor(ConsumidorVO consumidorVO) throws ConsumidorException
	{
		return Aluno.getInstance().recuperarAluno(consumidorVO);
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
		if (consumidorVO.getIdCurso() != null)
		{
			Aluno.getInstance().adicionarAluno(consumidorVO);
		}
		else
		{
			Funcionario.getInstance().adicionarFuncionario(consumidorVO);
		}	
		
	}
	
	public void atualizarConsumidor(ConsumidorVO consumidorVO) throws ConsumidorException 
	{
		if (consumidorVO.getIdCurso() != null)
		{
			Aluno.getInstance().atualizarAluno(consumidorVO);
		}
		else
		{
			Funcionario.getInstance().atualizarFuncionario(consumidorVO);
		}	
		
	}
}
