package br.furb.seca.model;

public enum Periodo {
	_1("", ""), _2("", "");
	public String inicio;
	public String fim;

	private Periodo(String inicio, String fim) {
		this.inicio = inicio;
		this.fim = fim;
	}
}
