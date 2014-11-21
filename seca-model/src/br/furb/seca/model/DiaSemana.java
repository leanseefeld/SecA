package br.furb.seca.model;

public enum DiaSemana {

    DOMINGO("domingo"), //
    SEGUNDA("segunda-feira"), //
    TERCA("terça-feira"), //
    QUARTA("quarta-feira"), //
    QUINTA("quinta-feira"), //
    SEXTA("sexta-feira"), //
    SABADO("sábado");

    private String descricao;

    private DiaSemana(String descricao) {
	this.descricao = descricao;
    }

    @Override
    public String toString() {
	return descricao;
    }

    public static DiaSemana fromCodigo(int codigo) {
	DiaSemana[] values = DiaSemana.values();
	if (codigo > 0 && codigo <= values.length) {
	    return values[codigo - 1];
	}
	return values[0];
    }

}
