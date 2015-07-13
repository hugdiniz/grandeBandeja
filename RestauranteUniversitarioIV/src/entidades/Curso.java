package entidades;

import java.sql.SQLException;
import java.util.Collection;

import persistencia.RepositorioCurso;
import persistencia.RepositorioDepartamento;
import entidades.exceptions.CursoException;
import entidades.exceptions.DepartamentoException;
import entidades.value_objects.CursoVO;
import entidades.value_objects.DepartamentoVO;

public class Curso
{
	private static Curso curso;
	
	private Curso()
	{
		// TODO Auto-generated constructor stub
	}
	
	public static Curso getInstance() 
	{
		if (curso == null) 
		{
			curso = new Curso();
		}
		return curso;

	}

	public Collection recuperarCursos(CursoVO vo) throws CursoException
	{		
		try
		{
			return RepositorioCurso.getInstance().buscar(vo);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new CursoException("erro.recuperar.cursos.repositorio.curso.buscar");
		}		
	}
	
	public CursoVO recuperarCurso(CursoVO cursoVO) throws CursoException 
	{
		Collection departamentoVOs = null;
		try
		{
			departamentoVOs =  RepositorioCurso.getInstance().buscar(cursoVO);
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new CursoException("erro.recuperar.cursos.repositorio.curso.buscar");			
		}
		
		if (departamentoVOs != null && !departamentoVOs.isEmpty())
		{
			return (CursoVO) departamentoVOs.iterator().next();
		}
		else
		{
			return null;
		}	
	}
	
	@SuppressWarnings("null")
	public void adicionarCurso(CursoVO cursoVO) throws CursoException 
	{
		CursoVO cursoVOBusca = new CursoVO();
		cursoVOBusca.setSigla(cursoVO.getSigla());
		CursoVO cursoVOantigo = recuperarCurso(cursoVOBusca);
			
		
		if (cursoVOantigo != null )
		{
			//RN não pode ter mesmo curso (nome e sigla) 
			throw new CursoException("erro.adiconar.curso.repositorio.curso.ja.existe");
		}
		if(cursoVO.getDepartamentoVO().getId() == null)
		{
			//RN Não existe curso sem o seu respectivo Departamento.
			throw new CursoException("erro.adicionar.curso.repositorio.curso.nao.existe.departamento");
		}
		
		 try
		{
			RepositorioCurso.getInstance().inserirOuAtualizar(cursoVO);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new CursoException("erro.recuperar.adicionar.repositorio.curso.inserirOuAtualizar");
		}
					
		
	}

	public void atualizarCurso(CursoVO vo) throws CursoException 
	{
		CursoVO cursoVOBusca = new CursoVO();
		cursoVOBusca.setSigla(vo.getSigla());
		CursoVO cursoVOantigo = recuperarCurso(cursoVOBusca);
				
		try
		{
			RepositorioCurso.getInstance().inserirOuAtualizar(vo);
		}
		catch (SQLException e)
		{			
			e.printStackTrace();
			throw new CursoException("erro.recuperar.atualizar.repositorio.curso.inserirOuAtualizar");
		}	
	}
}
