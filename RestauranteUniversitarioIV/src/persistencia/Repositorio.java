package persistencia;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import javax.servlet.http.HttpSession;

public abstract class Repositorio<T> {
	
	private HttpSession session;
	private String attributeName;

	public Repositorio(HttpSession session, String attributeName){
		this.session = session;
		this.attributeName = attributeName;
	}
	
	public Collection<T> listar(){
		
		Collection<T> lista = (Collection<T>)this.session.getAttribute(this.attributeName);
		
		if (lista == null){
			lista = new HashSet<T>();
			session.setAttribute(this.attributeName, lista);
		}

		return lista;
	}
	
	public void adicionar(T novo){
		Collection<T> lista = listar();
		lista.add(novo);		
		session.setAttribute(this.attributeName, lista);
	}
	
	public abstract void atualizar(T antigo, T novo);
	public abstract T buscar(T alvo);
}