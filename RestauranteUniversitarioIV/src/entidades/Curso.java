package entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.servlet.http.HttpSession;
import persistencia.RepositorioCurso;
import entidades.value_objects.CursoVO;

public class Curso implements Serializable {
	// metodos de persistencia de CursoVO

	public static Collection<CursoVO> _listarCursosDisponiveis(HttpSession session){
		RepositorioCurso repositorio = new RepositorioCurso(session);
		return repositorio.listar();
	}
	
	public static void _adicionarCurso(HttpSession session, CursoVO curso) {
		RepositorioCurso repositorio = new RepositorioCurso(session);
		repositorio.adicionar(curso);
	}

	public static CursoVO _buscarCurso(HttpSession session, String sigla) {
		RepositorioCurso repositorio = new RepositorioCurso(session);
		return repositorio.buscar(new CursoVO(null, sigla, null));
	}

	public static void _atualizarCurso(HttpSession session, CursoVO curso) {
		RepositorioCurso repositorio = new RepositorioCurso(session);
		repositorio.atualizar(new CursoVO(null,curso.getSigla(),null), curso);	
	}
}
