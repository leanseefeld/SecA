package br.furb.seca.model;

public class Disciplina {

	private Professor professor;
	private String descricao;

	public Disciplina(String descricao, Professor prof) {
		this.descricao = descricao;
		this.professor = prof;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public Professor getProfessor() {
		return professor;
	}
	
	@Override
	public String toString() {
		return descricao;
	}
}
