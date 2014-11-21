package br.furb.seca.model;

public class Disciplina {

    private int codigo;
    private String nomeProfessor;
    private String nome;

    public Disciplina(String nome, String nomeProfessor) {
	this.nome = nome;
	this.nomeProfessor = nomeProfessor;
    }

    public String getNome() {
	return nome;
    }

    public String getNomeProfessor() {
	return nomeProfessor;
    }

    public int getCodigo() {
	return codigo;
    }

    @Override
    public String toString() {
	return nome;
    }

}
