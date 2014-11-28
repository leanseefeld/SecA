package br.furb.seca.model;

import java.util.ArrayList;
import java.util.Collection;

public class Aluno {

    private long codigo;
    private String usuario;
    private byte[] senha;
    private String nomeCompleto;
    private final Collection<Compromisso> compromissos = new ArrayList<>();
    private final Collection<Disciplina> disciplinas = new ArrayList<>();

    public Aluno(long codigo, String usuario, String nomeCompleto, byte[] senha) {
	this.codigo = codigo;
	this.usuario = usuario;
	this.nomeCompleto = nomeCompleto;
	this.senha = senha;
    }

    public final long getCodigo() {
	return codigo;
    }

    public final String getUsuario() {
	return usuario;
    }

    public final byte[] getSenha() {
	return senha;
    }

    public final String getNomeCompleto() {
	return nomeCompleto;
    }

    public Collection<Compromisso> getCompromissos() {
	return compromissos;
    }

    public Collection<Disciplina> getDisciplinas() {
	return disciplinas;
    }

    public void addCompromisso(Compromisso compromisso) {
	this.compromissos.add(compromisso);
    }

    public void addDisciplina(Disciplina disciplina) {
	this.disciplinas.add(disciplina);
    }

}
