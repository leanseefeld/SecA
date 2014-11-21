package br.furb.seca;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import br.furb.seca.model.Horario;

public class HorarioListAdapter extends ArrayAdapter<Horario> {

    private static final int SECTIONED_STATE = 1;
    private static final int REGULAR_STATE = 2;
    private int[] mRowStates;

    private final List<Horario> horarios;
    private final LayoutInflater inflater;

    public HorarioListAdapter(Context context, List<Horario> horarios) {
	super(context, R.layout.fragment_horario_item, horarios);

	this.horarios = horarios;
	inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	mRowStates = new int[horarios.size()];
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

	boolean showSeparator = false;

	Horario horario = horarios.get(position);

	TextView txtDisciplina = (TextView) row.findViewById(R.id.horario_disciplina);
	txtDisciplina.setText(horario.getDisciplina().getNome());

	TextView txtProfessor = (TextView) row.findViewById(R.id.horario_professor);
	txtProfessor.setText(horario.getDisciplina().getNomeProfessor());

	TextView txtHorario = (TextView) row.findViewById(R.id.horario_horario);
	txtHorario.setText(horario.getPeriodo().getInicio() + " - " + horario.getPeriodo().getFim());

	switch (mRowStates[position]) {

	    case SECTIONED_STATE:
		showSeparator = true;
		break;

	    case REGULAR_STATE:
		showSeparator = false;
		break;

	    default:

		if (position == 0) {
		    showSeparator = true;
		} else {

		    if (horarios.get(position - 1).getDiaSemana() != horario.getDiaSemana()) {
			showSeparator = true;
		    } else {
			showSeparator = false;
		    }
		}

		// Cache it
		mRowStates[position] = showSeparator ? SECTIONED_STATE : REGULAR_STATE;

		break;
	}

	TextView txtSeparador = (TextView) row.findViewById(R.id.separator);
	if (showSeparator) {
	    txtSeparador.setText(horario.getDescricaoDiaSemana());
	    txtSeparador.setVisibility(View.VISIBLE);
	} else {
	    txtSeparador.setVisibility(View.GONE);
	}

	return row;
    }
}
