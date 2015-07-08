package entidades;

import entidades.value_objects.CursoVO;

public class Aluno extends Consumidor{
	private CursoVO curso;

	public Aluno(String nome, int matricula, int anoIngresso, CursoVO curso) {
		super(nome, matricula, anoIngresso);
		this.curso = curso;
	}
	
	public Aluno(String nome, int matricula, int anoIngresso, Sexo sexo, Titulo titulo, CPF cpf, CursoVO curso) {
		super(nome, matricula, anoIngresso, sexo, titulo, cpf);
		this.curso = curso;
	}
	
	public CursoVO getCurso() {
		return curso;
	}
}
