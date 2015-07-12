package controladores.ccu;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import controladores.ccu.exceptions.DepartamentoNotFound;
import controladores.ccu.exceptions.NomeNotFoundException;
import controladores.ccu.exceptions.SiglaAlreadyExistsException;
import controladores.ccu.exceptions.SiglaNotFoundException;
import entidades.Departamento;
import entidades.value_objects.DepartamentoVO;

public class GerirDepartamento {
	private static GerirDepartamento gerirDepartamento;
	public static GerirDepartamento getInstance()
	{
		if (gerirDepartamento == null) {
			gerirDepartamento = new GerirDepartamento();
		}
		return gerirDepartamento;
	}
	public Collection<DepartamentoVO> listarDepartamentos(DepartamentoVO departamentoVO) {
		return Departamento.getInstance().listarDepartamentosDisponiveis(departamentoVO);
	}
	
	public static DepartamentoVO buscarDepartamento(HttpSession session, String sigla) throws DepartamentoNotFound{
		DepartamentoVO departamentoAntigo = new DepartamentoVO("", sigla);
		try {
			departamentoAntigo = Departamento.getInstance().buscarDepartamento(session, departamentoAntigo.getSigla());
		} catch (NullPointerException e) {
			throw new DepartamentoNotFound();
		}
		if (departamentoAntigo == null){
			throw new DepartamentoNotFound();
		}
		
		return departamentoAntigo;
	}	
	
	public static void criarDepartamento(HttpSession session, String nome, String sigla) throws SiglaNotFoundException, NomeNotFoundException, SiglaAlreadyExistsException {
		
		DepartamentoVO dpto = new DepartamentoVO(nome,sigla);
		
		if (Departamento.getInstance().buscarDepartamento(session,sigla) == null){
			if (sigla==""){
				throw new SiglaNotFoundException();
			}else{
				if (nome==""){
					throw new NomeNotFoundException();
				}else{
					Departamento.getInstance().adicionarDepartamento(dpto);
				}
			}
		}else{
			throw new SiglaAlreadyExistsException(sigla);
		}
	}
	
	public static void atualizarDepartamento(HttpSession session, String nome, String sigla) throws DepartamentoNotFound{
		DepartamentoVO dpto = new DepartamentoVO(nome,sigla);
		
		DepartamentoVO departamentoAntigo = buscarDepartamento(session,sigla);
		if (departamentoAntigo == null){
			throw new DepartamentoNotFound();	
		}else{
			Departamento.getInstance().atualizarDepartamento(session, dpto);
		}
	}
}
