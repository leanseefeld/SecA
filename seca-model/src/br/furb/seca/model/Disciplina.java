package br.furb.seca.model;

import java.util.ArrayList;
import java.util.List;

public class Disciplina {

    private int codigo;
    private String nomeProfessor;
    private String nome;
    private final List<Prova> provas = new ArrayList<>(5);

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

    public List<Prova> getProvas() {
	return provas;
    }

    public void addProva(Prova prova) {
	this.provas.add(prova);
    }

    @Override
    public String toString() {
	return nome;
    }

}
