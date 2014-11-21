package br.furb.seca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

public class CompromissosFragment extends MyFragment {

    private Spinner cboUnidadeMedidaTempo;

    private String[] from = new String[] { "Valor" };
    private int[] to = new int[] { android.R.id.text1 };

    public CompromissosFragment(int sectionNumber) {
	super(R.layout.fragment_compromissos, sectionNumber);
    }

    public CompromissosFragment() {
	super(R.layout.fragment_compromissos);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	View v = super.onCreateView(inflater, container, savedInstanceState);

	cboUnidadeMedidaTempo = (Spinner) v.findViewById(R.id.cboUnidadeTempo);

	List<Map<String, String>> unidades = new ArrayList<Map<String, String>>();
	HashMap<String, String> unidade = new HashMap<String, String>();
	unidade.put("Valor", "Minutos");
	unidades.add(unidade);

	unidade = new HashMap<String, String>();
	unidade.put("Valor", "Horas");
	unidades.add(unidade);

	unidade = new HashMap<String, String>();
	unidade.put("Valor", "Dias");
	unidades.add(unidade);

	cboUnidadeMedidaTempo.setAdapter(new SimpleAdapter(v.getContext(), unidades,
		android.R.layout.simple_spinner_item, from, to));

	return v;
    }

}
