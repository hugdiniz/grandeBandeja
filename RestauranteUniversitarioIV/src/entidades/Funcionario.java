package entidades;

import java.util.Collection;

import entidades.value_objects.DepartamentoVO;

public class Funcionario extends Consumidor{
	private DepartamentoVO departamento;

	public Funcionario(String nome, int matricula, int anoIngresso, DepartamentoVO departamento) {
		super(nome, matricula, anoIngresso);
		this.departamento = departamento;
	}

	public Funcionario(String nome, int matricula, int anoIngresso, Sexo sexo, Titulo titulo, CPF cpf, DepartamentoVO departamento) {
		super(nome, matricula, anoIngresso, sexo, titulo, cpf);
		this.departamento = departamento;
	}
	
	public DepartamentoVO getDepartamento() {
		return departamento;
	}
}
