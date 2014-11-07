package br.furb.seca.model;

public class Disciplina {

    private Professor professor;

    public Disciplina(Professor prof) {
	this.professor = prof;
    }

    public Professor getProfessor() {
	return professor;
    }
}
