package entidades.value_objects;

public class ConsumidorVO implements VO
{
	private String nome;
	private String matricula;
	private String anoIngresso;
	private String sexo;
	private String titulo;
	private String cpf;
	private Long id;
	private Long idAluno;
	private Long idFuncionario;
	private Long idDepartamento;
	private Long idCurso;
	
	public Long getIdCurso()
	{
		return idCurso;
	}
	public void setIdCurso(Long idCurso)
	{
		this.idCurso = idCurso;
	}
	public Long getIdDepartamento()
	{
		return idDepartamento;
	}
	public void setIdDepartamento(Long idDepartamento)
	{
		this.idDepartamento = idDepartamento;
	}
	
	
	public Long getIdAluno()
	{
		return idAluno;
	}
	public void setIdAluno(Long idAluno)
	{
		this.idAluno = idAluno;
	}
	public Long getIdFuncionario()
	{
		return idFuncionario;
	}
	public void setIdFuncionario(Long idFuncionario)
	{
		this.idFuncionario = idFuncionario;
	}
	public String getNome()
	{
		return nome;
	}
	public void setNome(String name)
	{
		this.nome = name;
	}
	public String getMatricula()
	{
		return matricula;
	}
	public void setMatricula(String matricula)
	{
		this.matricula = matricula;
	}
	public String getAnoIngresso()
	{
		return anoIngresso;
	}
	public void setAnoIngresso(String anoIngresso)
	{
		this.anoIngresso = anoIngresso;
	}
	public String getSexo()
	{
		return sexo;
	}
	public void setSexo(String sexo)
	{
		this.sexo = sexo;
	}
	public String getTitulo()
	{
		return titulo;
	}
	public void setTitulo(String titulo)
	{
		this.titulo = titulo;
	}
	public String getCpf()
	{
		return cpf;
	}
	public void setCpf(String cpf)
	{
		this.cpf = cpf;
	}
	public Long getId()
	{
		return id;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
}
