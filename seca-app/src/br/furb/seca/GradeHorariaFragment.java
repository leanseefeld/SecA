package br.furb.seca;

import java.util.List;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import br.furb.seca.controller.Controller;
import br.furb.seca.model.Horario;

public class GradeHorariaFragment extends MyFragment {

    private ListView ListViewDiasSemana;
    private Controller controller;

    public GradeHorariaFragment() {
	super(R.layout.fragment_grade_horaria);
    }

    public GradeHorariaFragment(int sectionNumber) {
	super(R.layout.fragment_grade_horaria, sectionNumber);
    }

    public List<Horario> listarHorarios() {
	return controller.buscarHorarios();
	/*
	List<DiaSemana> diaSemana = new ArrayList<DiaSemana>();
	
	Professor proEveraldo = new Professor("Everaldo Artur Grahl");
	Professor proJaison = new Professor("Jaison Hinkel");
	Professor proPaulo = new Professor("Paulo Fernando da Silva");
	Professor proJhony = new Professor("Jhony Alceu Pereira");
	Professor proCaludio = new Professor("Cláudio Ratke");
	
	Disciplina disCompOrga = new Disciplina("Comportamento Organizacional", proJaison);
	Disciplina disProcSoft = new Disciplina("Processo de Software I", proEveraldo);
	Disciplina disSistDist = new Disciplina("Sistemas Distribuídos", proPaulo);
	Disciplina disAndroid = new Disciplina("Android", proJhony);
	Disciplina disBancoDad = new Disciplina("Banco de Dados II", proCaludio);
	
	DiaSemana dia = new DiaSemana(2);
	dia.addHorario(new Horario(disProcSoft, Periodo._12, Periodo._13));
	dia.addHorario(new Horario(disCompOrga, Periodo._14, Periodo._15));
	diaSemana.add(dia);
	
	dia = new DiaSemana(3);
	dia.addHorario(new Horario(disSistDist, Periodo._12, Periodo._13));
	dia.addHorario(new Horario(disProcSoft, Periodo._14, Periodo._15));
	diaSemana.add(dia);
	
	dia = new DiaSemana(4);
	dia.addHorario(new Horario(disCompOrga, Periodo._12, Periodo._13));
	dia.addHorario(new Horario(disSistDist, Periodo._14, Periodo._15));
	diaSemana.add(dia);
	
	dia = new DiaSemana(5);
	dia.addHorario(new Horario(disAndroid, Periodo._12, Periodo._15));
	diaSemana.add(dia);
	
	dia = new DiaSemana(6);
	dia.addHorario(new Horario(disBancoDad, Periodo._12, Periodo._15));
	diaSemana.add(dia);
	
	return diaSemana;*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	View v = super.onCreateView(inflater, container, savedInstanceState);
	controller = new Controller(v.getContext());

	ListViewDiasSemana = (ListView) v.findViewById(R.id.dias_semana);
	ListViewDiasSemana.setAdapter(new HorarioListAdapter(v.getContext(), listarHorarios()));

	return v;
    }
}
