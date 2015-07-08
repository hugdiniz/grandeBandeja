package entidades;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import persistencia.RepositorioDepartamento;
import entidades.value_objects.DepartamentoVO;

public class Departamento {
	private static Departamento departamento;
	public static Departamento getInstance() {
		if (departamento == null) {
			departamento = new Departamento();
		}
		return departamento;

	}
	public Collection<DepartamentoVO> listarDepartamentosDisponiveis(HttpSession session){
		RepositorioDepartamento repositorio = new RepositorioDepartamento(session);
		return 	repositorio.listar();
	}

	// metodos de persistencia para Departamento
	
	public void adicionarDepartamento(HttpSession session, DepartamentoVO dpto) {
		RepositorioDepartamento repositorio = new RepositorioDepartamento(session);
		repositorio.adicionar(dpto);
	}

	public DepartamentoVO buscarDepartamento(HttpSession session, String sigla) {
		RepositorioDepartamento repositorio = new RepositorioDepartamento(session);
		return 	repositorio.buscar(new DepartamentoVO(null,sigla));
	}

	public void atualizarDepartamento (HttpSession session, DepartamentoVO dpto){
		RepositorioDepartamento repositorio = new RepositorioDepartamento(session);
		repositorio.atualizar(new DepartamentoVO(null,dpto.getSigla()), dpto);
	}
}
