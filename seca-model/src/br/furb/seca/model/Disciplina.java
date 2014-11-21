package br.furb.seca.model;

public class Disciplina {

	private Professor professor;
	private String nome;

	public Disciplina(String nome, Professor prof) {
		this.nome = nome;
		this.professor = prof;
	}
	
	public String getNome() {
		return nome;
	}

	public Professor getProfessor() {
		return professor;
	}
	
	@Override
	public String toString() {
		return nome;
	}
}
