package persistencia;

import java.util.Collection;

import entidades.value_objects.VO;

public class RepositorioTicket extends Repositorio
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