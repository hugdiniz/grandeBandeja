package persistencia;

import java.util.Collection;

import entidades.value_objects.VO;

public abstract class Repositorio
{
	
	
	
	
	public abstract void inserirOuAtualizar(VO vo);
	public abstract Collection buscar(VO vo);
	public abstract Collection remover(VO vo);
}