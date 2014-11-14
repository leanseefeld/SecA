package br.furb.seca.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import android.R.bool;

public class Compromisso {
	private int _id;
	private String descricao;
	private bool diaTodo;
	private Date dataInicio;
	private Date dataFinal;
	private List<Date> lembretes;
	private Disciplina disciplina;

	public Compromisso() {
		this.lembretes = new ArrayList<Date>();
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public void addLembrete(Date lembrete) {
		this.lembretes.add(lembrete);
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

}
