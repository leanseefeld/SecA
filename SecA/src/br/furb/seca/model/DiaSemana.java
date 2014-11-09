package br.furb.seca.model;

import java.util.ArrayList;
import java.util.List;

public class DiaSemana {

	private List<Horario> horarios;
	private String descricao;

	public DiaSemana(String descricao) {
		this.horarios = new ArrayList<Horario>();
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public List<Horario> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
	}

	public void addHorario(Horario hor) {
		this.horarios.add(hor);
	}

	@Override
	public String toString() {
		return this.getDescricao();
	}
}
