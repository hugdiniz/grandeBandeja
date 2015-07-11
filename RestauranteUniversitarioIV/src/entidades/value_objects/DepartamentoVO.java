package entidades.value_objects;

public class DepartamentoVO implements VO {
	private String nome;
	private String sigla;
	
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
