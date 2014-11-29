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

    public Aluno(long codigo, String usuario, String nomeCompleto) {
	this.codigo = codigo;
	this.usuario = usuario;
	this.nomeCompleto = nomeCompleto;
    }

    public Aluno(long codigo, String usuario, String nomeCompleto, byte[] senha) {
	this.codigo = codigo;
	this.usuario = usuario;
	this.nomeCompleto = nomeCompleto;
	this.senha = senha;
    }

    public long getCodigo() {
	return codigo;
    }

    public String getUsuario() {
	return usuario;
    }

    public byte[] getSenha() {
	return senha;
    }

    public void setSenha(byte[] senha) {
	this.senha = senha;
    }

    public String getNomeCompleto() {
	return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
	this.nomeCompleto = nomeCompleto;
    }

    public void addCompromisso(Compromisso compromisso) {
	this.compromissos.add(compromisso);
    }

    public Collection<Compromisso> getCompromissos() {
	return compromissos;
    }

    public void addDisciplina(Disciplina disciplina) {
	this.disciplinas.add(disciplina);
    }

    public Collection<Disciplina> getDisciplinas() {
	return disciplinas;
    }

}
