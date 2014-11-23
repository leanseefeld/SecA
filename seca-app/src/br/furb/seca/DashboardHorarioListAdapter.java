package br.furb.seca;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import br.furb.seca.model.Horario;

public class DashboardHorarioListAdapter extends
		ArrayAdapter<Map<String, String>> {

	private final List<Map<String, String>> horarios;
	private final LayoutInflater inflater;

	public DashboardHorarioListAdapter(Context context,
			List<Map<String, String>> horarios) {
		super(context, R.layout.fragment_dashboard_horario_item, horarios);

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
		View row = inflater.inflate(R.layout.fragment_horario_item, parent,
				false);

		Map<String, String> horario = horarios.get(position);

		TextView txtDisciplina = (TextView) row
				.findViewById(R.id.horario_disciplina);
		txtDisciplina.setText(horario.get("disciplina"));

		TextView txtProfessor = (TextView) row
				.findViewById(R.id.horario_professor);
		txtProfessor.setText(horario.get("professor"));

		TextView txtHorario = (TextView) row.findViewById(R.id.horario_horario);
		txtHorario.setText(horario.get("horarioInicio") + " - "
				+ horario.get("horarioFim"));

		TextView txtSala = (TextView) row.findViewById(R.id.sala);
		txtSala.setText(horario.get("sala"));

		TextView txtDiaSemana = (TextView) row.findViewById(R.id.dia_semana);
		txtDiaSemana.setText(horario.get("dia"));

		return row;
	}
}
