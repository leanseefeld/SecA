package br.furb.seca.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import android.R.bool;

public class Compromisso {

    private long _id;
    private int codigo;
    private String descricao;
    private String titulo;
    private Date dataInicio;
    private Date dataFim;
    private boolean isDiaTodo;
    private final List<Integer> lembretes;
    private Disciplina disciplina;

    public long getId() {
        return _id;
    }
    
    public void setId(long id) {
        this._id = id;
    }
    
    public void setCodigo(int codigo) {
	this.codigo = codigo;
    }

    public void setDescricao(String descricao) {
	this.descricao = descricao;
    }

    public void setTitulo(String titulo) {
	this.titulo = titulo;
    }

    public void setDataInicio(Date dataInicio) {
	this.dataInicio = dataInicio;
    }

    public void setDataFim(Date dataFim) {
	this.dataFim = dataFim;
    }

    public void setDiaTodo(boolean isDiaTodo) {
	this.isDiaTodo = isDiaTodo;
    }

    public void setDisciplina(Disciplina disciplina) {
	this.disciplina = disciplina;
    }

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

    public List<Integer> getLembretes() {
	return lembretes;
    }

    /**
     * Verifica se o compromisso é de dia inteiro.<br>
     * Em caso afirmativo, o {@code Date} retornado nos métodos {@link #getDataInicio()} e
     * {@link #getDataFim()} levam em consideração a hora informada. Caso contrário, a hora do dia
     * deve ser desconsiderada.
     * 
     * @return {@code true} se o compromisso é de dia inteiro
     */
    public boolean isDiaTodo() {
	return isDiaTodo;
    }

    public void setIsDiaTodo(boolean isDiaTodo) {
	this.isDiaTodo = isDiaTodo;
    }

}
