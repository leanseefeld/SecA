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

public class DisciplinasFragment extends MyFragment {

	private String[] from = {"disciplina", "professor"};
	private int[] to = { android.R.id.text1, android.R.id.text2 };
	private ListView listDisciplinas;
	
	public DisciplinasFragment() {
		super(R.layout.fragment_disciplinas);
	}

	public DisciplinasFragment(int sectionNumber) {
		super(R.layout.fragment_disciplinas, sectionNumber);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = super.onCreateView(inflater, container, savedInstanceState);
		
		listDisciplinas = (ListView)v.findViewById(R.id.disciplinas);
		atualizaDisciplinas(v.getContext());
		
		return v;
	}
	
	public void atualizaDisciplinas(Context c)
	{
		SimpleAdapter adapter = new SimpleAdapter(
				c, 
				listarDisciplinas(), 
				android.R.layout.simple_list_item_2, 
				from, to);
		
		listDisciplinas.setAdapter(adapter);
	}
	
	public List<Map<String, String>> listarDisciplinas()
	{
		List<Map<String, String>> disciplinas = new ArrayList<Map<String,String>>();
		
		Map<String, String> m = new HashMap<String,String>();
		m.put("disciplina", "Sistemas Distribuidos");
		m.put("professor", "Paulo");
		disciplinas.add(m);
		
		m = new HashMap<String,String>();
		m.put("disciplina", "Processo de Software I");
		m.put("professor", "Everaldo");
		disciplinas.add(m);
		
		return disciplinas;
	}
}
