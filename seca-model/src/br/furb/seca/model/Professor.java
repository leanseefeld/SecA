package br.furb.seca.model;

public class Professor implements LazyLoadable {

    private int codigo;
    private String nome;
    private transient boolean loaded;

    public Professor(int codigo) {
	this(codigo, null);
    }

    public Professor(int codigo, String nome) {
	setCodigo(codigo);
	setNome(nome);
	setLoaded(nome != null);
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

    @Override
    public boolean isLoaded() {
	return loaded;
    }

    @Override
    public void setLoaded(boolean loaded) {
	this.loaded = loaded;
    }

}
