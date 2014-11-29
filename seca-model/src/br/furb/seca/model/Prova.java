package br.furb.seca.model;

public class Prova {

    private float nota;
    private float peso; 
    private String nomeAvaliacao;

    public Prova(float nota, String nomeAvaliacao) {
	this.nota = nota;
	this.nomeAvaliacao = nomeAvaliacao;
    }

    public float getNota() {
	return nota;
    }

    public String getNomeAvaliacao() {
	return nomeAvaliacao;
    }

    public float getPeso() {
	return peso;
    }

    public void setPeso(float peso) {
	this.peso = peso;
    }

}
