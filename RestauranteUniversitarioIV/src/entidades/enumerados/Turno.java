package entidades.enumerados;

import java.util.ArrayList;
import java.util.Collection;

public enum Turno {
	MANHA(3,0.5), TARDE(6,1), NOITE(6,1);
	
	private double valorFuncionario, valorAluno;

	private Turno(double valorFuncionario, double valorAluno) {
		this.valorAluno = valorAluno;
		this.valorFuncionario = valorFuncionario;
	}
	
	public double getValorAluno() {
		return valorAluno;
	}
	
	public double getValorFuncionario() {
		return valorFuncionario;
	}
	
	public static Collection names()
	{
		Collection names = new ArrayList();
		for (Turno turno : Turno.values())
		{
			names.add(turno.name());
		}
		return names;
	}
}
