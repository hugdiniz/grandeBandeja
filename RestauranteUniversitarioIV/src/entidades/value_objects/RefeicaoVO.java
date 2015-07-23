package entidades.value_objects;
import entidades.enumerados.Turno;

public class RefeicaoVO implements VO {
	
	private Turno turno;
	private String descricao;
	private String op_vegetariana;
	private Long id;
	private Double valorAluno;
	private Double valorFuncionario;
	
	public void setValorAluno(Double valorAluno)
	{
		this.valorAluno = valorAluno;
	}

	public void setValorFuncionario(Double valorFuncionario)
	{
		this.valorFuncionario = valorFuncionario;
	}

	public RefeicaoVO() 
	{
		
	}

	public RefeicaoVO(Turno turno, String descricao, String op_vegetariana ) {
		this.turno = turno;
		this.descricao = descricao;
		this.op_vegetariana = op_vegetariana;
	}

	
	public String getOp_vegetariana() {
		return op_vegetariana;
	}

	public void setOp_vegetariana(String op_vegetariana) {
		this.op_vegetariana = op_vegetariana;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Turno getTurno() {
		return turno;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setTurno(Turno turno) {
		this.turno = turno;
	}
	
	public double getValorFuncionario()
	{
		return valorAluno;
	}
	
	public double getValorAluno()
	{
		return valorFuncionario;
	}
}
 
