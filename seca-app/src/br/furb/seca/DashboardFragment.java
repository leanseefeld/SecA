package br.furb.seca;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import br.furb.seca.controller.Controller;

public class DashboardFragment extends MyFragment {

    Controller controller;
    ListView horarios;
    
    public DashboardFragment() {
	super(R.layout.fragment_dashboard);
    }

    public DashboardFragment(int sectionNumber) {
	super(R.layout.fragment_dashboard, sectionNumber);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	View v = super.onCreateView(inflater, container, savedInstanceState);
	controller = new Controller(v.getContext());

	horarios = (ListView) v.findViewById(R.id.lista_horarios);
	horarios.setAdapter(new DashboardHorarioListAdapter(v.getContext(), 
		controller.proximosHorariosFormatados(2)));

	return v;
    }

}

