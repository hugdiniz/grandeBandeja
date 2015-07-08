package entidades;

public enum Turno {
	MANHA(0.5,3), TARDE(6,1), NOITE(6,1);
	
	private double valorAluno, valorFuncionario;

	private Turno(double valorAluno, double valorFuncionario) {
		this.valorAluno = valorAluno;
		this.valorFuncionario = valorFuncionario;
	}
	
	public double getValorAluno() {
		return valorAluno;
	}
	
	public double getValorFuncionario() {
		return valorFuncionario;
	}
}
