package br.furb.seca.model;

public class Horario {

	private Disciplina disciplina;
	private Periodo inicio;
	private Periodo fim;

	public Horario(Disciplina disciplina, Periodo inicio, Periodo fim) {
		this.disciplina = disciplina;
		this.inicio = inicio;
		this.fim = fim;
	}
	
	public Horario(Disciplina disciplina, int periodoInicio, int periodoFim) {
		this.disciplina = disciplina;
		this.inicio = intToPeriodo(periodoInicio);
		this.fim = intToPeriodo(periodoFim);
	}
	
	public Disciplina getDisciplina() {
		return disciplina;
	}

	public Periodo getInicio() {
		return inicio;
	}

	public Periodo getFim() {
		return fim;
	}

	public String Periodo() {
		return inicio.getInicio() + "-" + fim.getFim();
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
}
