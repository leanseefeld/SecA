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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Disciplina {

    private int codigo;
    private String nomeProfessor;
    private String nome;
    private final List<Prova> provas = new ArrayList<>(5);
    private final Collection<Horario> horarios = new ArrayList<>();

    public Disciplina(String nome, String nomeProfessor) {
	this.nome = nome;
	this.nomeProfessor = nomeProfessor;
    }

    public String getNome() {
	return nome;
    }

    public String getNomeProfessor() {
	return nomeProfessor;
    }

    public void setCodigo(int value) {
        codigo = value;
    }
    
    public int getCodigo() {
	return codigo;
    }

    public List<Prova> getProvas() {
	return provas;
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