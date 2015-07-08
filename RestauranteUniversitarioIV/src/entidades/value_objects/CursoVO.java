package entidades.value_objects;

import java.io.Serializable;

public class CursoVO implements Serializable {
	private String nome;
	private String sigla;
	private DepartamentoVO departamento;

	public CursoVO(String nome, String sigla,DepartamentoVO departamento) {
		this.nome = nome;
		this.sigla = sigla;
		this.departamento = departamento;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getSigla() {
		return sigla;
	}
	
	public DepartamentoVO getDepartamento() {
		return departamento;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setDepartamento(DepartamentoVO departamento){
		this.departamento = departamento;
	}
}
