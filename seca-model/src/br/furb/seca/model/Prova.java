package br.furb.seca.model;

public class Prova {

    private float nota;
    private String nomeAvaliacao;

    public Prova(float nota, String nomeAvaliacao) {
	this.nota = nota;
	this.nomeAvaliacao = nomeAvaliacao;
    }

    public final float getNota() {
	return nota;
    }

    public final String getNomeAvaliacao() {
	return nomeAvaliacao;
    }

}
