package br.furb.seca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class DisciplinasFragment extends MyFragment {

    private ListView listDisciplinas;

    public DisciplinasFragment() {
	super(R.layout.fragment_disciplinas);
    }

    public DisciplinasFragment(int sectionNumber) {
	super(R.layout.fragment_disciplinas, sectionNumber);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	View v = super.onCreateView(inflater, container, savedInstanceState);

	listDisciplinas = (ListView) v.findViewById(R.id.frDisciplinas_lista);

	atualizaDisciplinas(v.getContext());

	return v;
    }

    public void atualizaDisciplinas(Context c) {
	DisciplinaListAdapter adapter = new DisciplinaListAdapter(c, R.layout.fragment_disciplina_item, Arrays.asList(
		new String[] { "Sistemas Distribuídos", "Paulo Fernando da Silva", "8.33" }, // 
		new String[] { "Processo de Software I", "Everaldo Artur Grahl", "9.5" }, //
		new String[] { "Desafios Sociais e Contemporâneos", "Cíntia Aparecida da Luz Silva Pereira Goes",
			"10.0" }, //
		new String[] { "Álgebra Linear para Computação", "Evandro Felin Londero", "2.45" }, //
		new String[] { "Linguagens de Programação", "José Roque Voltolini da Silva", "7.0" }, //
		new String[] { "Teoria dos Grafos", "Aurélio Hoppe", "8.0" }, //
		new String[] { "Arquitetura de Computadores", "Miguel Alexandre Wisintainer", "7.77" }, //
		new String[] { "Linguagens Formais", "Joyce Martins", "8.9" } //
		));
	listDisciplinas.setAdapter(adapter);
    }

    private List<Map<String, String>> listarDisciplinas() {
	List<Map<String, String>> disciplinas = new ArrayList<Map<String, String>>();

	Map<String, String> m = new HashMap<String, String>();
	m.put("disciplina", "Sistemas Distribuidos");
	m.put("professor", "Paulo");
	disciplinas.add(m);

	m = new HashMap<String, String>();
	m.put("disciplina", "Processo de Software I");
	m.put("professor", "Everaldo");
	disciplinas.add(m);

	return disciplinas;
    }
    
    @Override
    void Atualizar() {
	atualizaDisciplinas(this.getActivity());
    }
}
