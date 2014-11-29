package br.furb.seca.model;

public class Professor {

    private int codigo;
    private String nome;
    private transient boolean loaded;

    public Professor(int codigo, String nome) {
	setCodigo(codigo);
	setNome(nome);
    }

    public int getCodigo() {
	return codigo;
    }

    public void setCodigo(int codigo) {
	this.codigo = codigo;
    }

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

}