package br.furb.seca.model;

import java.util.ArrayList;
import java.util.List;

public class DiaSemana {

	private List<Horario> horarios;
	private int diaSemana;

	public DiaSemana(int diaSemana) {
		this.horarios = new ArrayList<Horario>();
		this.diaSemana = diaSemana;
	}

	public String getDescricao() {
		String saida;
		switch (diaSemana) {
		case 1:
			saida = "Domingo";
			break;
		case 2:
			saida = "Segunda Feira";
			break;
		case 3:
			saida = "Terça Feira";
			break;
		case 4:
			saida = "Quarda Feira";
			break;
		case 5:
			saida = "Quinta Feira";
			break;
		case 6:
			saida = "Sexta Feira";
			break;
		case 7:
			saida = "Sabado Feira";
			break;
		default:
			saida = "Fudeu";
			break;
		}
		return saida;
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

	@Override
	public boolean equals(Object o) {
		if (o != null) {
			if (o instanceof DiaSemana) {
				if (((DiaSemana) o).diaSemana == this.diaSemana) {
					return true;
				}
			}
		}
		return false;
	}
}
