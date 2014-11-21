package br.furb.seca.model;

public class Horario {

	private Disciplina disciplina;
	private Periodo periodo;
	private String sala;
	private int diaSemana;

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}
	
	public void setPeriodo(int periodo) {
		this.periodo = intToPeriodo(periodo);
	}

	public int getDiaSemana() {
		return diaSemana;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public void setDiaSemana(int diaSemana) {
		this.diaSemana = diaSemana;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public String getSala() {
		return sala;
	}

	private Periodo intToPeriodo(int periodo) {
		Periodo enumPerdiodo;
		switch (periodo) {
		case 1:
			enumPerdiodo = Periodo._1;
			break;
		case 2:
			enumPerdiodo = Periodo._2;
			break;
		case 3:
			enumPerdiodo = Periodo._3;
			break;
		case 4:
			enumPerdiodo = Periodo._4;
			break;
		case 5:
			enumPerdiodo = Periodo._5;
			break;
		case 7:
			enumPerdiodo = Periodo._7;
			break;
		case 8:
			enumPerdiodo = Periodo._8;
			break;
		case 9:
			enumPerdiodo = Periodo._9;
			break;
		case 10:
			enumPerdiodo = Periodo._10;
			break;
		case 11:
			enumPerdiodo = Periodo._11;
			break;
		case 12:
			enumPerdiodo = Periodo._12;
			break;
		case 13:
			enumPerdiodo = Periodo._13;
			break;
		case 14:
			enumPerdiodo = Periodo._14;
			break;
		case 15:
			enumPerdiodo = Periodo._15;
			break;
		default:
			enumPerdiodo = Periodo._1;
			break;
		}

		return enumPerdiodo;
	}
	
	public String getDescricaoDiaSemana() {
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
}
