package entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.servlet.http.HttpSession;
import persistencia.RepositorioCurso;
import entidades.value_objects.CursoVO;

public class Curso implements Serializable {
	// metodos de persistencia de CursoVO

	public static Collection<CursoVO> _listarCursosDisponiveis(CursoVO vo){
		
		return RepositorioCurso.getInstance().buscar(vo);
		
		
	}
	
	public static void _adicionarCurso(CursoVO vo) {
		 RepositorioCurso.getInstance().inserirOuAtualizar(vo);
	}

	public static void _atualizarCurso(CursoVO vo) {
		RepositorioCurso.getInstance().inserirOuAtualizar(vo);	
	}
}
