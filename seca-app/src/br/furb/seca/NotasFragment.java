package br.furb.seca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import br.furb.seca.controller.Controller;
import br.furb.seca.model.Disciplina;
import br.furb.seca.model.Prova;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class NotasFragment extends MyFragment {

    private ListView listViewNotas;
    private TextView nome_disciplina;
    private TextView media_notas;
    private Disciplina disciplina;
    private Controller controller;

    private String[] from = new String[] { "Nota", "NomePeso" };
    private int[] to = new int[] { android.R.id.text1, android.R.id.text2 };

    public NotasFragment(Disciplina disciplina) {
	super(R.layout.fragment_notas);
	this.disciplina = disciplina;
    }

    @Override
    void Atualizar() {
	Toast.makeText(this.getActivity(), "Sincronizando notas", Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	View v = super.onCreateView(inflater, container, savedInstanceState);
	listViewNotas = (ListView) v.findViewById(R.id.notas_disciplina);
	controller = new Controller(v.getContext());
	listViewNotas.setAdapter(new SimpleAdapter(v.getContext(), listarNotas(), android.R.layout.simple_list_item_2,
		from, to));
	
	nome_disciplina = (TextView)v.findViewById(R.id.nome_disciplina);
	media_notas = (TextView)v.findViewById(R.id.media_notas);
	
	nome_disciplina.setText(disciplina.getNome());
	media_notas.setText(controller.calcularMedia(disciplina.getProvas()));

	return v;
    }

    public List<Map<String, String>> listarNotas() {
	List<Map<String, String>> notas = new ArrayList<Map<String,String>>();
	
	for (Prova prova : this.disciplina.getProvas()) {
	    Map<String, String> nota = new HashMap<String, String>();
	    nota.put("NomePeso", prova.getNomeAvaliacao() + " - Peso:" + prova.getPeso());
	    nota.put("Nota", String.valueOf(prova.getNota()));
	    notas.add(nota);
	}
	
	return notas;
    }
}
