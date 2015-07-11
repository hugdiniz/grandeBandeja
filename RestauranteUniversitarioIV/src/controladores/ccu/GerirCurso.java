package controladores.ccu;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import controladores.ccu.exceptions.CursoNotFound;
import controladores.ccu.exceptions.DepartamentoNotFound;
import controladores.ccu.exceptions.NomeNotFoundException;
import controladores.ccu.exceptions.SiglaAlreadyExistsException;
import controladores.ccu.exceptions.SiglaNotFoundException;
import entidades.Curso;
import entidades.Departamento;
import entidades.value_objects.CursoVO;
import entidades.value_objects.DepartamentoVO;

public class GerirCurso {
	public static Object listarCursos(CursoVO vo) {
		return Curso._listarCursosDisponiveis(vo);
	}

	public static CursoVO buscarCurso(CursoVO vo, String sigla) throws CursoNotFound{
		CursoVO cursoAntigo = new CursoVO("", sigla,null);
		try {
			Collection antigo = Curso._listarCursosDisponiveis(cursoAntigo);
		} catch (NullPointerException e) {
			throw new CursoNotFound();
		}
		if (cursoAntigo == null){
			throw new CursoNotFound();
		}
		
		return cursoAntigo;
	}

	public static void criarCurso(CursoVO vo, String nome, String sigla, String siglaDpto) throws SiglaNotFoundException, NomeNotFoundException, SiglaAlreadyExistsException, DepartamentoNotFound {
		DepartamentoVO dpto = Departamento.getInstance().buscarDepartamento(vo,siglaDpto);
		
		if (dpto == null){
			throw new DepartamentoNotFound();
		} else{
			CursoVO curso = new CursoVO(nome,sigla,dpto);
			
			if (Curso._buscarCurso(vo,sigla) == null){
				if (sigla==""){
					throw new SiglaNotFoundException();
				}else{
					if (nome==""){
						throw new NomeNotFoundException();
					}else{
						Curso._adicionarCurso(vo,curso);
					}
				}
			}else{
				throw new SiglaAlreadyExistsException(sigla);
			}
		}
	}

	public static void atualizarCurso(HttpSession session, String nome, String sigla, String siglaDpto) throws CursoNotFound, DepartamentoNotFound{
		DepartamentoVO dpto = Departamento.getInstance().buscarDepartamento(session,siglaDpto);
		
		if (dpto == null){
			throw new DepartamentoNotFound();
		} else{
			CursoVO cursoAntigo = buscarCurso(session,sigla);
			if (cursoAntigo == null){
				throw new CursoNotFound();
			}else{
				cursoAntigo.setNome(nome);
				cursoAntigo.setDepartamento(dpto);
				Curso._atualizarCurso(session, cursoAntigo);
			}
		}
	}			
}
