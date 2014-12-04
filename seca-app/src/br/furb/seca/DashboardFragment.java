package br.furb.seca;

import java.util.List;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import br.furb.seca.controller.Controller;
import br.furb.seca.model.Compromisso;

public class DashboardFragment extends MyFragment {

    Controller controller;
    ListView listViewHorarios;
    ListView ListViewCompromissos;

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

	listViewHorarios = (ListView) v.findViewById(R.id.lista_horarios);
	ListViewCompromissos = (ListView) v.findViewById(R.id.compromissos_dashboard);

	atualizar(v.getContext());

	return v;
    }

    private void atualizar(Context c) {
	listViewHorarios.setAdapter(new DashboardHorarioListAdapter(c, controller.buscarProximosHorariosFormatados(2)));

	try {

	    List<Compromisso> compromissos = controller.buscarProximosCompromissos(2);
	    Log.d("MEU", "Número de compromissos: " + compromissos.size());
	    ListViewCompromissos.setAdapter(new DashboardCompromissoListAdapter(c, compromissos));
	} catch (Exception ex) {
	    Log.d("MEU", ex.getMessage());
	    Toast.makeText(c, "Ocorreu um erro ao bucar os compromissos \r\n" + ex.getMessage(), Toast.LENGTH_LONG)
		    .show();
	    Log.e("SecA-compromissos", "Ocorreu um erro ao bucar os compromissos", ex);
	}
    }

    @Override
    public void atualizar() {
	this.atualizar(this.getActivity());
    }
}
