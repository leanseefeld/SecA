/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package seca;

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
    private List<Integer> lembretes;
    private Disciplina disciplina;

    public Compromisso() {
	this.lembretes = new ArrayList<>();
    }

    

    public void addLembrete(Integer minutosAntecedencia) {
        this.getLembretes().add(minutosAntecedencia);
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
	return isIsDiaTodo();
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the dataInicio
     */
    public Date getDataInicio() {
        return dataInicio;
    }

    /**
     * @param dataInicio the dataInicio to set
     */
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * @return the dataFim
     */
    public Date getDataFim() {
        return dataFim;
    }

    /**
     * @param dataFim the dataFim to set
     */
    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    /**
     * @return the isDiaTodo
     */
    public boolean isIsDiaTodo() {
        return isDiaTodo;
    }

    /**
     * @param isDiaTodo the isDiaTodo to set
     */
    public void setIsDiaTodo(boolean isDiaTodo) {
        this.isDiaTodo = isDiaTodo;
    }

    /**
     * @return the lembretes
     */
    public List<Integer> getLembretes() {
        return lembretes;
    }

    /**
     * @param lembretes the lembretes to set
     */
    public void setLembretes(List<Integer> lembretes) {
        this.lembretes = lembretes;
    }

    /**
     * @return the disciplina
     */
    public Disciplina getDisciplina() {
        return disciplina;
    }

    /**
     * @param disciplina the disciplina to set
     */
    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

}
