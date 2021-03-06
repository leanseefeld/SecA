package br.furb.seca;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import br.furb.seca.model.Compromisso;
import br.furb.seca.model.DiaSemana;

public class DashboardCompromissoListAdapter extends ArrayAdapter<Compromisso> {

    private final List<Compromisso> compromissos;
    private final LayoutInflater inflater;

    public DashboardCompromissoListAdapter(Context context, List<Compromisso> compromissos) {
	super(context, R.layout.fragment_dashboard_compromisso_item, compromissos);

	this.compromissos = compromissos;
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
	View row = inflater.inflate(R.layout.fragment_dashboard_compromisso_item, parent, false);

	Compromisso compromisso = compromissos.get(position);

	TextView txtTitulo = (TextView) row.findViewById(R.id.titulo_compromisso);
	TextView txtDisciplina = (TextView) row.findViewById(R.id.disciplina_compromisso);
	TextView txtDataInicial = (TextView) row.findViewById(R.id.data_inicial_compromisso);
	TextView txtDataFinal = (TextView) row.findViewById(R.id.data_final_compromisso);

	txtTitulo.setText(compromisso.getTitulo());

	if (compromisso.getDisciplina() != null) {
	    txtDisciplina.setText(compromisso.getDisciplina().getNome());
	} else {
	    txtDisciplina.setVisibility(View.GONE);
	}

	txtDataInicial.setText("Inicio: " + this.MontaData(compromisso.getDataInicio(), compromisso.isDiaTodo()));
	txtDataFinal.setText("Fim: " + this.MontaData(compromisso.getDataFim(), compromisso.isDiaTodo()));

	return row;
    }

    @SuppressLint("DefaultLocale")
    private String MontaData(Date data, Boolean ehDiaTodo) {
	String dataSaida = "";
	Calendar cal = Calendar.getInstance();
	cal.setTime(data);

	String diaSemana = DiaSemana.fromCodigo(cal.get(Calendar.DAY_OF_WEEK)).toString();
	int dia = cal.get(Calendar.DAY_OF_MONTH);
	int mes = cal.get(Calendar.MONTH);
	int hora = cal.get(Calendar.HOUR);
	int minuto = cal.get(Calendar.MINUTE);

	if (cal.get(Calendar.AM_PM) == Calendar.PM) {
	    hora += 12;
	}

	if (ehDiaTodo) {
	    dataSaida = String.format("%s, %02d/%02d", diaSemana, dia, mes);
	} else {
	    dataSaida = String.format("%s, %02d/%02d - %02d:%02d", diaSemana, dia, mes, hora, minuto);
	}
	return dataSaida;
    }
}
