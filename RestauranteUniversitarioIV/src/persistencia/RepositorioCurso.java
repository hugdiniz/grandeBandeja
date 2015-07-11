package persistencia;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import entidades.value_objects.CursoVO;
import entidades.value_objects.VO;

public class RepositorioCurso extends Repositorio
{
	private static RepositorioCurso repositorio;
	
	
	public static RepositorioCurso getInstance() {
		if (repositorio == null) {
			repositorio = new RepositorioCurso();
		}
		return repositorio;
	}

	@Override
	public void inserirOuAtualizar(VO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection buscar(VO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection remover(VO vo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}