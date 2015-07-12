package controladores.ccu;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import controladores.ccu.exceptions.DepartamentoNotFound;
import controladores.ccu.exceptions.NomeNotFoundException;
import controladores.ccu.exceptions.SiglaAlreadyExistsException;
import controladores.ccu.exceptions.SiglaNotFoundException;
import entidades.Departamento;
import entidades.exceptions.DepartamentoException;
import entidades.value_objects.DepartamentoVO;

public class GerirDepartamento
{
	private static GerirDepartamento gerirDepartamento;
	public static GerirDepartamento getInstance()
	{
		if (gerirDepartamento == null)
		{
			gerirDepartamento = new GerirDepartamento();
		}
		return gerirDepartamento;
	}
	
	public Collection<DepartamentoVO> listarDepartamentos() throws DepartamentoException
	{
		return Departamento.getInstance().recuperarDepartamentos(new DepartamentoVO());
	}
	
	public DepartamentoVO buscarDepartamento(DepartamentoVO departamentoVO) throws DepartamentoException
	{			
		DepartamentoVO departamentoAntigo = Departamento.getInstance().recuperarDepartamento(departamentoVO);		
		
		return departamentoAntigo;
	}	
	
	public void criarDepartamento(DepartamentoVO departamentoVO) throws DepartamentoException 
	{
		Departamento.getInstance().adicionarDepartamento(departamentoVO);
	}
	
	public void atualizarDepartamento(DepartamentoVO departamentoVO) throws DepartamentoException
	{
		Departamento.getInstance().atualizarDepartamento(departamentoVO);
	}
}
