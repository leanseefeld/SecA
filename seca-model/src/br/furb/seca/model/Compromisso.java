package br.furb.seca.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Compromisso {

    private int codigo;
    private String descricao;
    private String titulo;
    private Date dataInicio;
    private Date dataFim;
    private boolean isDiaTodo;
    private final List<Integer> lembretes;
    private Disciplina disciplina;

    public Compromisso() {
	this.lembretes = new ArrayList<>();
    }

    public String getDescricao() {
	return descricao;
    }

    public String getTitulo() {
	return titulo;
    }

    public Date getDataInicio() {
	return dataInicio;
    }

    public Date getDataFim() {
	return dataFim;
    }

    public void addLembrete(Integer minutosAntecedencia) {
	this.lembretes.add(minutosAntecedencia);
    }

    public Disciplina getDisciplina() {
	return disciplina;
    }

    public int getCodigo() {
	return codigo;
    }

    /**
     * Verifica se o compromisso � de dia inteiro.<br>
     * Em caso afirmativo, o {@code Date} retornado nos m�todos {@link #getDataInicio()} e
     * {@link #getDataFim()} levam em considera��o a hora informada. Caso contr�rio, a hora do dia
     * deve ser desconsiderada.
     * 
     * @return {@code true} se o compromisso � de dia inteiro
     */
    public boolean isDiaTodo() {
	return isDiaTodo;
    }

}
