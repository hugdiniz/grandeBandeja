package entidades.value_objects;

import entidades.enumerados.SexoEnum;
import entidades.enumerados.TituloEnum;

/**
 * @author hugdiniz
 *
 */
public class ConsumidorVO implements VO
{
	private String nome;
	private String matricula;
	private String anoIngresso;
	private SexoEnum sexo;
	private TituloEnum titulo;
	private String cpf;
	private Long id;	
	private Long idDepartamento;
	private Long idCurso;
	private Boolean atualizar;
	
	public Boolean getAtualizar()
	{
		return atualizar;
	}
	public void setAtualizar(Boolean atualizar)
	{
		this.atualizar = atualizar;
	}
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
	public SexoEnum getSexo()
	{
		return sexo;
	}
	public void setSexo(SexoEnum sexo)
	{
		this.sexo = sexo;
	}
	public TituloEnum getTitulo()
	{
		return titulo;
	}
	public void setTitulo(TituloEnum titulo)
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
