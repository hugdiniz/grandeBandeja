package persistencia;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import entidades.value_objects.CursoVO;

public class RepositorioCurso extends Repositorio<CursoVO> {
	
	public RepositorioCurso(HttpSession session) {
		super(session, "cursosDisponiveis");
	}
	
	@Override
	public void atualizar(CursoVO antigo, CursoVO novo) {
		Collection<CursoVO> cursosDisponiveis = listar();
		CursoVO cursoAntigo = buscar(antigo);
		cursoAntigo.setNome(novo.getNome());
		cursoAntigo.setDepartamento(novo.getDepartamento());
		cursosDisponiveis.add(cursoAntigo);
	}
		
	@Override
	public CursoVO buscar(CursoVO alvo) {
		Collection<CursoVO> cursosDisponiveis = listar();
		for(CursoVO di : cursosDisponiveis){
			if (alvo.getSigla().equals(di.getSigla()))
				return di;
		}
		return null;	
	}
}