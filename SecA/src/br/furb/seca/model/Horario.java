package br.furb.seca.model;

public class Horario {

	public Horario(Disciplina disciplina, Periodo inicio, Periodo fim) {
		this.disciplina = disciplina;
		this.inicio = inicio;
		this.fim = fim;
	}

	private Disciplina disciplina;
	private Periodo inicio;
	private Periodo fim;

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public Periodo getInicio() {
		return inicio;
	}

	public Periodo getFim() {
		return fim;
	}
}
