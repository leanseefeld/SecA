package br.furb.seca.model;

import java.util.ArrayList;
import java.util.List;

public class Disciplina {

    private long _id;
    private int codigo;
    private String nomeProfessor;
    private String nome;
    private final List<Prova> provas = new ArrayList<>(5);

    public Disciplina() {

    }

    public Disciplina(String nome, String nomeProfessor) {
	this.nome = nome;
	this.nomeProfessor = nomeProfessor;
    }

    public long getId() {
	return _id;
    }

    public void setId(long _id) {
	this._id = _id;
    }

    public void setCodigo(int codigo) {
	this.codigo = codigo;
    }

    public void setNomeProfessor(String nomeProfessor) {
	this.nomeProfessor = nomeProfessor;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public String getNome() {
	return this.nome;
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
