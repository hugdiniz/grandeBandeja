package persistencia;

import java.util.Collection;
import java.util.Set;

import javax.servlet.http.HttpSession;

import entidades.value_objects.DepartamentoVO;

public class RepositorioDepartamento extends Repositorio<DepartamentoVO> {
	
	public RepositorioDepartamento(HttpSession session) {
		super(session, "departamentosDisponiveis");
	}
	
	@Override
	public void atualizar(DepartamentoVO antigo, DepartamentoVO novo) {
		Collection<DepartamentoVO> departamentosDisponiveis = listar();
		DepartamentoVO departamentoAntigo = buscar(antigo);
		departamentoAntigo.setNome(novo.getNome());
		departamentosDisponiveis.add(departamentoAntigo);
	}
		
	@Override
	public DepartamentoVO buscar(DepartamentoVO alvo) {
		Collection<DepartamentoVO> departamentosDisponiveis = listar();
		for(DepartamentoVO di : departamentosDisponiveis){
			if (alvo.getSigla().equals(di.getSigla()))
				return di;
		}
		return null;	
	}
}