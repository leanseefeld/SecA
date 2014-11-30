package br.furb.seca.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Compromisso {

    private long codigo;
    private String titulo;
    private String descricao;
    private long dataInicioMili;
    private long dataFimMili;
    private transient Date dataInicio;
    private transient Date dataFim;
    private boolean isDiaTodo;
    private List<Integer> lembretes;
    private Disciplina disciplina;

    public Compromisso() {
	this.lembretes = new ArrayList<>();
    }

    public long getCodigo() {
	return codigo;
    }

    public void setCodigo(long codigo) {
	this.codigo = codigo;
    }

    public String getTitulo() {
	return titulo;
    }

    public void setTitulo(String titulo) {
	this.titulo = titulo;
    }

    public String getDescricao() {
	return descricao;
    }

    public void setDescricao(String descricao) {
	this.descricao = descricao;
    }

    public Date getDataInicio() {
	if (dataInicio == null && dataInicioMili != 0) {
	    return dataInicio = new Date(dataInicioMili);
	}
	return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
	this.dataInicioMili = dataInicio == null ? 0 : dataInicio.getTime();
	this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
	if (dataFim == null && dataFimMili != 0) {
	    return dataFim = new Date(dataFimMili);
	}
	return dataFim;
    }

    public void setDataFim(Date dataFim) {
	this.dataFimMili = dataFim == null ? 0 : dataFim.getTime();
	this.dataFim = dataFim;
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

    public void setDiaTodo(boolean isDiaTodo) {
	this.isDiaTodo = isDiaTodo;
    }

    public void addLembrete(Integer minutosAntecedencia) {
	this.lembretes.add(minutosAntecedencia);
    }

    public List<Integer> getLembretes() {
	return lembretes;
    }

    public void setLembretes(List<Integer> lembretes) {
	this.lembretes = lembretes;
    }

    public Disciplina getDisciplina() {
	return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
	this.disciplina = disciplina;
    }

}
