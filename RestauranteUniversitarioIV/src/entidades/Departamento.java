package entidades;

import java.sql.SQLException;
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
	public Collection<DepartamentoVO> listarDepartamentosDisponiveis(DepartamentoVO departamentoVO){
		
		try
		{
			return RepositorioDepartamento.getInstance().buscar(departamentoVO);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	// metodos de persistencia para Departamento
	
	public void adicionarDepartamento(DepartamentoVO departamentoVO) {
		RepositorioDepartamento.getInstance().inserirOuAtualizar(departamentoVO);
		
	}

	public DepartamentoVO buscarDepartamento(HttpSession session, String sigla) {
		//RepositorioDepartamento repositorio = new RepositorioDepartamento(session);
		return null;
	}

	public void atualizarDepartamento (HttpSession session, DepartamentoVO dpto){
		//RepositorioDepartamento repositorio = new RepositorioDepartamento(session);
		//repositorio.atualizar(new DepartamentoVO(null,dpto.getSigla()), dpto);
	}
}