package br.furb.seca;

import java.util.List;

import br.furb.seca.model.DiaSemana;
import br.furb.seca.model.Horario;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class HorarioListAdapter extends ArrayAdapter<Horario> {

	private final Context context;
	private final List<Horario> horarios;
	private final LayoutInflater inflater;

	public HorarioListAdapter(Context context, List<Horario> horarios) {
		super(context, R.layout.fragment_horario_item, horarios);

		this.context = context;
		this.horarios = horarios;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
		View row = inflater.inflate(R.layout.fragment_horario_item, parent, false);

		Log.d("TESTE2", String.valueOf(position));
		Log.d("TESTE2 - Size", String.valueOf(horarios.size()));
		
		Horario horario = horarios.get(position);

		TextView txtDisciplina = (TextView) row
				.findViewById(R.id.horario_disciplina);
		txtDisciplina.setText(horario.getDisciplina().getDescricao());

		TextView txtProfessor = (TextView) row
				.findViewById(R.id.horario_professor);
		txtProfessor.setText(horario.getDisciplina().getProfessor().getNome());

		TextView txtHorario = (TextView) row.findViewById(R.id.horario_horario);
		txtHorario.setText(horario.Periodo());

		return row;
	}

}
