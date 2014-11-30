package br.furb.seca.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Disciplina {

    private int codigo;
    private String nome;
    private Professor professor;
    private List<Prova> provas;
    private Collection<Horario> horarios;

    public Disciplina() {
	provas = new ArrayList<>(5);
	horarios = new ArrayList<>();
    }

    public Disciplina(String nome, String nomeProfessor) {
	this(nomeProfessor, new Professor(-1, nomeProfessor));
    }

    public Disciplina(String nome, Professor professor) {
	this();
	this.nome = nome;
	this.professor = professor;
    }

    public int getCodigo() {
	return codigo;
    }

    public void setCodigo(int codigo) {
	this.codigo = codigo;
    }

    public String getNome() {
	return this.nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public Professor getProfessor() {
	return professor;
    }

    public void setProfessor(Professor professor) {
	this.professor = professor;
    }

    public void addProva(Prova prova) {
	this.provas.add(prova);
    }

    public List<Prova> getProvas() {
	return provas;
    }

    public void addHorario(Horario horario) {
	this.horarios.add(horario);
    }

    public Collection<Horario> getHorarios() {
	return horarios;
    }

    public void setProvas(List<Prova> provas) {
	this.provas = provas;
    }

    @Override
    public String toString() {
	return nome;
    }

}
