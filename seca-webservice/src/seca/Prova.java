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
