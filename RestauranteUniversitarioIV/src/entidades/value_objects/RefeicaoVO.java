package entidades.value_objects;


public class RefeicaoVO implements VO {
	
	private String turno;
	private String descricao;
	private String op_vegetariana;
	private Long id;
	
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

	public RefeicaoVO() { }

	public RefeicaoVO(String turno, String descricao, String op_vegetariana ) {
		this.turno = turno;
		this.descricao = descricao;
		this.op_vegetariana = op_vegetariana;
	}
	
	public String getTurno() {
		return turno;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setTurno(String turno) {
		this.turno = turno;
	}
}
 
