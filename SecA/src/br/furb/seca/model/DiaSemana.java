package br.furb.seca.model;

import java.util.ArrayList;
import java.util.List;

public class DiaSemana {

    private List<Horario> horarios;

    public DiaSemana() {
	horarios = new ArrayList<Horario>();
    }

    public List<Horario> getHorarios() {
	return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
	this.horarios = horarios;
    }
}
