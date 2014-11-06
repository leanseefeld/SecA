package br.furb.seca.model;

public class Horario {

	private Disciplina disciplina;
	private Periodo periodoInicio;
	private Periodo periodoFim;
	
	public Horario() {
		
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

}
