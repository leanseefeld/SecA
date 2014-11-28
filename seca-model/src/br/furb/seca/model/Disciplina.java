package br.furb.seca.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Disciplina {

    private int codigo;
    private Professor professor;
    private String nome;
    private final List<Prova> provas = new ArrayList<>(5);
    private final Collection<Horario> horarios = new ArrayList<>();

    public Disciplina() {

    }

    public Disciplina(String nome, Professor professor) {
	this.nome = nome;
	this.professor = professor;
    }

    public void setCodigo(int codigo) {
	this.codigo = codigo;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public String getNome() {
	return this.nome;
    }

    public int getCodigo() {
	return codigo;
    }

    public List<Prova> getProvas() {
	return provas;
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

    public Collection<Horario> getHorarios() {
	return horarios;
    }

    public void addHorario(Horario horario) {
	this.horarios.add(horario);
    }

    @Override
    public String toString() {
	return nome;
    }

}
