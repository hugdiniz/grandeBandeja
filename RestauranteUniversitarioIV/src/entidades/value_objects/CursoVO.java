package entidades.value_objects;

public class CursoVO implements VO {
	private String nome;
	private String sigla;
	private DepartamentoVO departamentoVO;
	private Long id;

	public CursoVO()
	{		
	}
	
	public CursoVO(String nome, String sigla,DepartamentoVO departamento) {
		this.nome = nome;
		this.sigla = sigla;
		this.departamentoVO = departamento;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getSigla() {
		return sigla;
	}
	
	public DepartamentoVO getDepartamentoVO() {
		return departamentoVO;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setDepartamentoVO(DepartamentoVO departamento){
		this.departamentoVO = departamento;
	}
	
	public Long getId()
	{
		return id;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
	
	public void setSigla(String sigla)
	{
		this.sigla = sigla;
	}
}
