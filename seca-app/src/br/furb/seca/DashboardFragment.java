package br.furb.seca;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import br.furb.seca.controller.Controller;

public class DashboardFragment extends MyFragment {

    Controller controller;
    ListView horarios;
    ListView compromissos;

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
	compromissos = (ListView) v.findViewById(R.id.compromissos_dashboard);

	horarios.setAdapter(new DashboardHorarioListAdapter(v.getContext(), controller
		.buscarProximosHorariosFormatados(2)));

	try {
	    compromissos.setAdapter(new DashboardCompromissoListAdapter(v.getContext(), controller
		    .buscarProximosCompromissos(2)));
	} catch (Exception ex) {
	    Log.d("MEU", ex.getMessage());
	    Toast.makeText(v.getContext(), "Ocorreu um erro ao bucar os compromissos \r\n" + ex.getMessage(),
		    Toast.LENGTH_LONG).show();
	    Log.e("SecA-compromissos", "Ocorreu um erro ao bucar os compromissos", ex);
	}

	return v;

    }
}
