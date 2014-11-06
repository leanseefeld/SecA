package br.furb.seca;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class DisciplinaListAdapter extends ArrayAdapter<String[]> {

    private final LayoutInflater inflater;
    private final List<String[]> disciplinas;

    public DisciplinaListAdapter(Context context, int resource, List<String[]> disciplinas) {
	super(context, resource);
	this.disciplinas = disciplinas;
	inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
	return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
	return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View converView, ViewGroup parent) {
	View row = inflater.inflate(R.layout.fragment_disciplina_item, parent, false);
	String[] disciplina = disciplinas.get(position);
	TextView txtDisciplina = (TextView) row.findViewById(R.id.txtDisciplina);
	txtDisciplina.setText(disciplina[0]);
	TextView txtProfessor = (TextView) row.findViewById(R.id.txtProfessor);
	txtProfessor.setText(disciplina[1]);
	TextView txtNota = (TextView) row.findViewById(R.id.txtNota);
	txtNota.setText(disciplina[3]);
	return row;
    }

}
