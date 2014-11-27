package br.furb.seca.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Compromisso {

    private long _id;
    private int codigo;
    private String descricao;
    private String titulo;
    private Date dataInicio;
    private Date dataFim;
    private boolean isDiaTodo;
    private List<Integer> lembretes;
    private Disciplina disciplina;

    public Compromisso() {
	this.lembretes = new ArrayList<>();
    }

    public void addLembrete(Integer minutosAntecedencia) {
	this.lembretes.add(minutosAntecedencia);
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

    public long getId() {
	return _id;
    }

    public void setId(long id) {
	this._id = id;
    }

    public void setDiaTodo(boolean isDiaTodo) {
	this.isDiaTodo = isDiaTodo;
    }

    public String getDescricao() {
	return descricao;
    }

    /**
     * @param descricao
     *            the descricao to set
     */
    public void setDescricao(String descricao) {
	this.descricao = descricao;
    }

    public String getTitulo() {
	return titulo;
    }

    /**
     * @param titulo
     *            the titulo to set
     */
    public void setTitulo(String titulo) {
	this.titulo = titulo;
    }

    public Date getDataInicio() {
	return dataInicio;
    }

    /**
     * @param dataInicio
     *            the dataInicio to set
     */
    public void setDataInicio(Date dataInicio) {
	this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
	return dataFim;
    }

    /**
     * @param dataFim
     *            the dataFim to set
     */
    public void setDataFim(Date dataFim) {
	this.dataFim = dataFim;
    }

    public Disciplina getDisciplina() {
	return disciplina;
    }

    public int getCodigo() {
	return codigo;
    }

    /**
     * @param lembretes
     *            the lembretes to set
     */
    public void setLembretes(List<Integer> lembretes) {
	this.lembretes = lembretes;
    }

    /**
     * @param codigo
     *            the codigo to set
     */
    public void setCodigo(int codigo) {
	this.codigo = codigo;
    }

    public List<Integer> getLembretes() {
	return lembretes;
    }

    /**
     * @param disciplina
     *            the disciplina to set
     */
    public void setDisciplina(Disciplina disciplina) {
	this.disciplina = disciplina;
    }

    public void setIsDiaTodo(boolean isDiaTodo) {
	this.isDiaTodo = isDiaTodo;
    }

    /**
     * @return the isDiaTodo
     */
    @Deprecated
    public boolean isIsDiaTodo() {
	return isDiaTodo;
    }

}
