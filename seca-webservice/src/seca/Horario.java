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
public class Horario {

    private int codigo;
    private Disciplina disciplina;
    private Periodo periodo;
    private DiaSemana diaSemana;
    private String sala;

    public int getCodigo() {
	return codigo;
    }

    public Periodo getPeriodo() {
	return periodo;
    }

    public void setPeriodo(Periodo periodo) {
	this.periodo = periodo;
    }

    public void setPeriodo(int periodo) {
	this.periodo = Periodo.fromCodigo(periodo);
    }

    public DiaSemana getDiaSemana() {
	return diaSemana;
    }

    public void setDisciplina(Disciplina disciplina) {
	this.disciplina = disciplina;
    }

    public void setSala(String sala) {
	this.sala = sala;
    }

    public void setDiaSemana(DiaSemana diaSemana) {
	this.diaSemana = diaSemana;
    }

    public Disciplina getDisciplina() {
	return disciplina;
    }

    public String getSala() {
	return sala;
    }

    public String getDescricaoDiaSemana() {
	return diaSemana.toString();
    }

}