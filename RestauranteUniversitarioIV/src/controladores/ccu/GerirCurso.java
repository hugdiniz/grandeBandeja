package controladores.ccu;

import entidades.Curso;
import entidades.exceptions.CursoException;
import entidades.value_objects.CursoVO;

public class GerirCurso 
{
	private static GerirCurso gerirCurso;
	public static GerirCurso getInstance()
	{
		if (gerirCurso == null)
		{
			gerirCurso = new GerirCurso();
		}
		return gerirCurso;
	}
	
	public static Object listarCursos() throws CursoException 
	{		
		return Curso.getInstance().recuperarCursos(new CursoVO());
	}

	public static CursoVO buscarCurso(CursoVO vo) throws CursoException 
	{		
		CursoVO antigo = Curso.getInstance().recuperarCurso(vo);
		return antigo;
	}

	public static void criarCurso(CursoVO vo) throws CursoException 
	{
		Curso.getInstance().adicionarCurso(vo);
	}

	public static void atualizarCurso(CursoVO vo) throws CursoException
	{
		Curso.getInstance().atualizarCurso(vo);
	}			
}
