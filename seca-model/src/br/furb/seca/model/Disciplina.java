package br.furb.seca.model;

import java.util.ArrayList;
import java.util.List;

public class Disciplina {

    private int codigo;
    private String nomeProfessor;
    private String nome;
    private final List<Float> notas = new ArrayList<>(4); // provas

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

    public List<Float> getNotas() {
	return notas;
    }

    public void addNota(Float nota) {
	this.notas.add(nota);
    }

    @Override
    public String toString() {
	return nome;
    }

}
