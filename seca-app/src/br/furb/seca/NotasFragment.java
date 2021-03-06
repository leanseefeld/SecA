package br.furb.seca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import br.furb.seca.controller.Controller;
import br.furb.seca.model.Disciplina;
import br.furb.seca.model.Prova;

public class NotasFragment extends MyFragment {

    private static final String CODIGO_DISCIPLINA = "codigo_disciplina";
    private ListView listViewNotas;
    private TextView nome_disciplina;
    private TextView media_notas;
    private Disciplina disciplina;
    private Controller controller;

    private String[] from = new String[] { "Nota", "NomePeso" };
    private int[] to = new int[] { android.R.id.text1, android.R.id.text2 };

    public NotasFragment() {
	this(new Disciplina());
    }

    public NotasFragment(Disciplina disciplina) {
	super(R.layout.fragment_notas);
	this.disciplina = disciplina;
    }

    @Override
    public void atualizar() {
	Disciplina disc = controller.buscarDisciplina(disciplina.getCodigo());
	if (disc != null) {
	    this.disciplina = disc;
	    atualizaNotas(this.getActivity());
	}
	Toast.makeText(this.getActivity(), "Notas sincronizadas", Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	if (savedInstanceState != null) {
	    int savedCodigo = savedInstanceState.getInt(CODIGO_DISCIPLINA);
	    for (Disciplina discAluno : Controller.getCurrentAluno().getDisciplinas()) {
		if (discAluno.getCodigo() == savedCodigo) {
		    disciplina = discAluno;
		    break;
		}
	    }
	}

	View v = super.onCreateView(inflater, container, savedInstanceState);
	controller = new Controller(v.getContext());
	listViewNotas = (ListView) v.findViewById(R.id.notas_disciplina);
	nome_disciplina = (TextView) v.findViewById(R.id.nome_disciplina);
	media_notas = (TextView) v.findViewById(R.id.media_notas);

	nome_disciplina.setText(this.disciplina.getNome());
	media_notas.setText(controller.calcularMedia(disciplina.getProvas()));

	atualizaNotas(v.getContext());

	return v;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
	if (disciplina != null) {
	    outState.putInt(CODIGO_DISCIPLINA, disciplina.getCodigo());
	}
    }

    private void atualizaNotas(Context c) {
	listViewNotas.setAdapter(new SimpleAdapter(c, listarNotas(), android.R.layout.simple_list_item_2, from, to));
    }

    private List<Map<String, String>> listarNotas() {
	List<Map<String, String>> notas = new ArrayList<Map<String, String>>();
	for (Prova prova : this.disciplina.getProvas()) {
	    Map<String, String> nota = new HashMap<String, String>();
	    nota.put("NomePeso", prova.getNomeAvaliacao() + " - Peso:" + prova.getPeso());
	    nota.put("Nota", String.valueOf(prova.getNota()));
	    notas.add(nota);
	}
	return notas;
    }

}
