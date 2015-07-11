package persistencia;

import java.util.Collection;
import java.util.Set;

import javax.servlet.http.HttpSession;

import entidades.value_objects.DepartamentoVO;
import entidades.value_objects.VO;

public class RepositorioDepartamento extends Repositorio
{
	private static RepositorioDepartamento repositorio;
	
	
	public static RepositorioDepartamento getInstance() {
		if (repositorio == null) {
			repositorio = new RepositorioDepartamento();
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