package br.furb.seca;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import br.furb.seca.controller.Controller;

public class GradeHorariaFragment extends MyFragment {

    private ListView ListViewDiasSemana;
    private Controller controller;

    public GradeHorariaFragment() {
	super(R.layout.fragment_grade_horaria);
    }

    public GradeHorariaFragment(int sectionNumber) {
	super(R.layout.fragment_grade_horaria, sectionNumber);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	View v = super.onCreateView(inflater, container, savedInstanceState);
	controller = new Controller(v.getContext());

	ListViewDiasSemana = (ListView) v.findViewById(R.id.dias_semana);
	ListViewDiasSemana.setAdapter(new HorarioListAdapter(v.getContext(), controller.buscarHorariosFormatados()));

	return v;
    }

    @Override
    public void atualizar() {
	ListViewDiasSemana
		.setAdapter(new HorarioListAdapter(this.getActivity(), controller.buscarHorariosFormatados()));
    }

}
