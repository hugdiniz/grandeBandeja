package entidades.value_objects;

public class DepartamentoVO implements VO {
	private String nome;
	private String sigla;
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public DepartamentoVO() { }

	public DepartamentoVO(String nome, String sigla) {
		this.nome = nome;
		this.sigla = sigla;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getSigla() {
		return sigla;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
}
