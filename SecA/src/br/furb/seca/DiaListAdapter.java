package br.furb.seca;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import br.furb.seca.model.DiaSemana;

public class DiaListAdapter extends ArrayAdapter<DiaSemana> {

	private final Context context;
	private final List<DiaSemana> values;
	private final LayoutInflater inflater;
	private final int resource;

	public DiaListAdapter(Context context, List<DiaSemana> objects) {
		super(context, R.layout.fragment_dia_item, objects);

		this.context = context;
		this.values = objects;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.resource = R.layout.fragment_dia_item;
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
		View row = inflater.inflate(this.resource, parent, false);

		DiaSemana diaSemana = values.get(position);

		TextView txtSemana = (TextView) row.findViewById(R.id.dia_semana);
		txtSemana.setText(diaSemana.getDescricao());

		ListView listViewHorarios = (ListView) row
				.findViewById(R.id.dia_horarios);
		
		HorarioListAdapter adapter = new HorarioListAdapter(row.getContext(),
				diaSemana.getHorarios());

		listViewHorarios.setAdapter(adapter);

		return row;
	}
}
