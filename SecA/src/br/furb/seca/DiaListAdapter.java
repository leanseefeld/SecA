package br.furb.seca;

import java.util.List;

import android.content.Context;
import android.widget.ArrayAdapter;
import br.furb.seca.model.DiaSemana;

public class DiaListAdapter extends ArrayAdapter {

	private final Context context;
	private final List<DiaSemana> values;

	public DiaListAdapter(Context context, int resource, List<DiaSemana> objects) {
		super(context, resource, objects);

		this.context = context;
		this.values = objects;
	}

}
