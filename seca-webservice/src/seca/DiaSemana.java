/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package seca;

/**
 *
 * @author gabri_000
 */
public enum DiaSemana {

    DOMINGO(1, "domingo"), //
    SEGUNDA(2, "segunda-feira"), //
    TERCA(3, "terça-feira"), //
    QUARTA(4, "quarta-feira"), //
    QUINTA(5, "quinta-feira"), //
    SEXTA(6, "sexta-feira"), //
    SABADO(7, "sábado");

    private String descricao;
    private int codigo;

    private DiaSemana(int codigo, String descricao) {
	this.codigo = codigo;
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

    public int getCodigo()
    {
	return this.codigo;
    }
    
}