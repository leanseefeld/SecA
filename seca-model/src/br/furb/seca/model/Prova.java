package br.furb.seca.model;

public class Prova {

    private float nota;
    private float peso;
    private String nomeAvaliacao;

    public Prova(float nota, String nomeAvaliacao) {
	this.nota = nota;
	this.nomeAvaliacao = nomeAvaliacao;
    }

    public Prova(float nota, float peso, String nomeAvaliacao) {
	this.nota = nota;
	this.nomeAvaliacao = nomeAvaliacao;
	this.peso = peso;
    }

    public float getNota() {
	return nota;
    }

    public String getNomeAvaliacao() {
	return nomeAvaliacao == null ? "" : nomeAvaliacao;
    }

    public float getPeso() {
	return peso;
    }

    public void setPeso(float peso) {
	this.peso = peso;
    }

    public void setNomeAvaliacao(String nomeAvaliacao) {
	this.nomeAvaliacao = nomeAvaliacao;
    }

    public void setNota(float nota) {
	this.nota = nota;
    }

}
