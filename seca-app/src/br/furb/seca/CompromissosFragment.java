package br.furb.seca;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import br.furb.seca.controller.Controller;
import br.furb.seca.model.Compromisso;
import br.furb.seca.model.Disciplina;

public class CompromissosFragment extends MyFragment {

    private Controller controller;
    private Context context;
    private String[] from = new String[] { "Valor" };
    private int[] to = new int[] { android.R.id.text1 };
    private String _idCompromisso;
    private EditText titulo_compromisso;
    private EditText descricao_compromisso;
    private TextView data_inicio;
    private TextView tempo_inicio;
    private TextView data_fim;
    private TextView tempo_fim;
    private Button btnGravar;
    private Button btnExcluir;
    private Spinner cbo_disciplinas;
    private CheckBox cbx_dia_todo;

    //private EditText txtTempoLemprete;
    //private Spinner cboUnidadeTempo;

    int anoInicio;
    int mesInicio;
    int diaInicio;
    int horaInicio;
    int minutoInicio;
    int anoFim;
    int mesFim;
    int diaFim;
    int horaFim;
    int minutoFim;

    public CompromissosFragment(int sectionNumber) {
	super(R.layout.fragment_compromissos, sectionNumber);
    }

    public CompromissosFragment() {
	super(R.layout.fragment_compromissos);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	View v = super.onCreateView(inflater, container, savedInstanceState);
	this.context = v.getContext();
	controller = new Controller(v.getContext());

	titulo_compromisso = (EditText) v.findViewById(R.id.titulo_compromisso);
	descricao_compromisso = (EditText) v.findViewById(R.id.descricao_compromisso);
	data_inicio = (TextView) v.findViewById(R.id.data_inicio);
	tempo_inicio = (TextView) v.findViewById(R.id.hora_inicio);
	data_fim = (TextView) v.findViewById(R.id.data_fim);
	tempo_fim = (TextView) v.findViewById(R.id.hora_fim);
	cbx_dia_todo = (CheckBox) v.findViewById(R.id.cbx_dia_inteiro);
	btnGravar = (Button) v.findViewById(R.id.btnGravar);
	btnExcluir = (Button) v.findViewById(R.id.btnExcluir);
	cbo_disciplinas = (Spinner) v.findViewById(R.id.cbo_disciplinas);

	//txtTempoLemprete = (EditText) v.findViewById(R.id.txtTempoLemprete);
	//cboUnidadeTempo = (Spinner) v.findViewById(R.id.cboUnidadeTempo);

	//txtTempoLemprete.setText("10");
	data_inicio.setOnClickListener(new OnClickListener() {

	    @Override
	    public void onClick(View v) {
		DialogFragment newFragment = new DatePickerFragment(new DateListener() {

		    @Override
		    public void setData(int dia, int mes, int ano) {
			CompromissosFragment.this.setDataInicio(dia, mes, ano);
		    }

		}, CompromissosFragment.this.context);
		newFragment.show(getFragmentManager(), "timePicker");
	    }
	});

	tempo_inicio.setOnClickListener(new OnClickListener() {

	    @Override
	    public void onClick(View v) {
		DialogFragment newFragment = new TimePickerFragment(new TimeListener() {

		    @Override
		    public void setTime(int hora, int minuto) {
			CompromissosFragment.this.setTempoInicio(hora, minuto);
		    }

		}, CompromissosFragment.this.context);
		newFragment.show(getFragmentManager(), "timePicker");
	    }
	});

	data_fim.setOnClickListener(new OnClickListener() {

	    @Override
	    public void onClick(View v) {
		DialogFragment newFragment = new DatePickerFragment(new DateListener() {

		    @Override
		    public void setData(int dia, int mes, int ano) {
			CompromissosFragment.this.setDataFim(dia, mes, ano);
		    }

		}, CompromissosFragment.this.context);
		newFragment.show(getFragmentManager(), "timePicker");
	    }
	});

	tempo_fim.setOnClickListener(new OnClickListener() {

	    @Override
	    public void onClick(View v) {
		DialogFragment newFragment = new TimePickerFragment(new TimeListener() {

		    @Override
		    public void setTime(int hora, int minuto) {
			CompromissosFragment.this.setTempoFim(hora, minuto);
		    }

		}, CompromissosFragment.this.context);
		newFragment.show(getFragmentManager(), "timePicker");
	    }
	});

	btnGravar.setOnClickListener(new OnClickListener() {

	    @Override
	    public void onClick(View v) {
		CompromissosFragment.this.gravar();
	    }
	});

	btnExcluir.setOnClickListener(new OnClickListener() {

	    @Override
	    public void onClick(View v) {
		CompromissosFragment.this.excluir();
	    }
	});

	cbx_dia_todo.setOnCheckedChangeListener(new OnCheckedChangeListener() {

	    @Override
	    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (isChecked) {
		    CompromissosFragment.this.tempo_inicio.setVisibility(View.INVISIBLE);
		    CompromissosFragment.this.tempo_fim.setVisibility(View.INVISIBLE);
		} else {
		    CompromissosFragment.this.tempo_inicio.setVisibility(View.VISIBLE);
		    CompromissosFragment.this.tempo_fim.setVisibility(View.VISIBLE);
		}
	    }
	});

	final Calendar c = Calendar.getInstance();
	int year = c.get(Calendar.YEAR);
	int month = c.get(Calendar.MONTH);
	int day = c.get(Calendar.DAY_OF_MONTH);
	int hour = c.get(Calendar.HOUR_OF_DAY);
	int minute = c.get(Calendar.MINUTE);
	this.setDataInicio(day, month, year);
	this.setTempoInicio(hour, minute);
	this.setDataFim(day, month, year);
	this.setTempoFim(hour, minute);

	//cboUnidadeTempo.setAdapter(new SimpleAdapter(v.getContext(), getUnidadesTempo(),
	//	android.R.layout.simple_spinner_item, from, to));

	cbo_disciplinas.setAdapter(new SimpleAdapter(v.getContext(), getDisciplinas(),
		android.R.layout.simple_spinner_item, from, to));

	return v;
    }

    /*private List<Map<String, String>> getUnidadesTempo() {
    List<Map<String, String>> unidades = new ArrayList<Map<String, String>>();
    HashMap<String, String> unidade = new HashMap<String, String>();
    unidade.put("Valor", "Minutos");
    unidades.add(unidade);

    unidade = new HashMap<String, String>();
    unidade.put("Valor", "Horas");
    unidades.add(unidade);

    unidade = new HashMap<String, String>();
    unidade.put("Valor", "Dias");
    unidades.add(unidade);
    return unidades;
    }*/

    private List<Map<String, String>> getDisciplinas() {
	List<Disciplina> disciplinas = controller.buscarDisciplinas();
	List<Map<String, String>> disciplinasMap = new ArrayList<Map<String, String>>();

	Map<String, String> map = new HashMap<String, String>();
	map.put("_id", "-1");
	map.put("Valor", "");
	disciplinasMap.add(map);
	for (Disciplina disc : disciplinas) {
	    map = new HashMap<String, String>();
	    map.put("_id", String.valueOf(disc.getCodigo()));
	    map.put("Valor", disc.getNome());
	    disciplinasMap.add(map);
	}

	return disciplinasMap;
    }

    private void setDataInicio(int dia, int mes, int ano) {
	this.diaInicio = dia;
	this.mesInicio = mes;
	this.anoInicio = ano;
	this.data_inicio.setText(String.format("%02d/%02d/%04d", dia, mes, ano));
    }

    private void setTempoInicio(int hora, int minuto) {
	this.horaInicio = hora;
	this.minutoInicio = minuto;
	this.tempo_inicio.setText(String.format("%02d:%02d:00", hora, minuto));
    }

    private void setDataFim(int dia, int mes, int ano) {
	this.diaFim = dia;
	this.mesFim = mes;
	this.anoFim = ano;
	this.data_fim.setText(String.format("%02d/%02d/%04d", dia, mes, ano));
    }

    private void setTempoFim(int hora, int minuto) {
	this.horaFim = hora;
	this.minutoFim = minuto;
	this.tempo_fim.setText(String.format("%02d:%02d:00", hora, minuto));
    }

    private void gravar() {
	String titulo = titulo_compromisso.getText().toString();
	if (titulo.isEmpty()) {
	    titulo_compromisso.setError("Este campo tem conteúdo obrigatório");
	    return;
	}

	Compromisso compromisso = new Compromisso();

	compromisso.setTitulo(titulo);
	compromisso.setDescricao(descricao_compromisso.getText().toString());
	Calendar cl = Calendar.getInstance();
	if (cbx_dia_todo.isChecked()) {
	    compromisso.setDiaTodo(true);
	    cl.set(this.anoInicio, this.mesInicio, this.diaInicio, 0, 0);
	    compromisso.setDataInicio(cl.getTime());
	    cl.set(this.anoFim, this.mesFim, this.diaFim, 23, 59);
	    compromisso.setDataFim(cl.getTime());
	} else {
	    compromisso.setDiaTodo(false);
	    cl.set(this.anoInicio, this.mesInicio, this.diaInicio, this.horaInicio, this.minutoInicio);
	    compromisso.setDataInicio(cl.getTime());
	    cl.set(this.anoFim, this.mesFim, this.diaFim, this.horaFim, this.minutoFim);
	    compromisso.setDataFim(cl.getTime());
	}

	/*if (!txtTempoLemprete.getText().toString().isEmpty()) {
	    int tempo_lembrete = Integer.valueOf(txtTempoLemprete.getText().toString());
	    int posicao = cboUnidadeTempo.getSelectedItemPosition();
	    switch (posicao) {
		case 1:
		    tempo_lembrete *= 60;
		    break;
		case 2:
		    tempo_lembrete *= 60 * 24;
		    break;
	    }
	    compromisso.addLembrete(tempo_lembrete);
	}*/
	if (cbo_disciplinas.getSelectedItemPosition() > 0) {
	    HashMap<String, String> disciplinaMap = (HashMap<String, String>) cbo_disciplinas.getSelectedItem();
	    Disciplina disc = controller.buscarDisciplina(Integer.valueOf(disciplinaMap.get("_id")));
	    compromisso.setDisciplina(disc);
	}

	controller.gravarCompromisso(compromisso);

	Toast.makeText(context, "Compromisso cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
    }

    private void excluir() {
	Toast.makeText(context, "Excluindo o compromisso " + _idCompromisso, Toast.LENGTH_SHORT).show();
	// TPDP
    }

    interface DateListener {

	void setData(int dia, int mes, int ano);
    }

    interface TimeListener {

	void setTime(int hora, int minuto);
    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

	DateListener dateListner;
	Context context;

	public DatePickerFragment(DateListener dateListner, Context context) {
	    this.dateListner = dateListner;
	    this.context = context;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
	    final Calendar c = Calendar.getInstance();
	    int year = c.get(Calendar.YEAR);
	    int month = c.get(Calendar.MONTH);
	    int day = c.get(Calendar.DAY_OF_MONTH);

	    return new DatePickerDialog(context, this, year, month, day);
	}

	public void onDateSet(DatePicker view, int year, int month, int day) {
	    dateListner.setData(day, month, year);
	}
    }

    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

	TimeListener timeListner;
	Context context;

	public TimePickerFragment(TimeListener timeListner, Context context) {
	    this.timeListner = timeListner;
	    this.context = context;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
	    // Use the current time as the default values for the picker
	    final Calendar c = Calendar.getInstance();
	    int hour = c.get(Calendar.HOUR_OF_DAY);
	    int minute = c.get(Calendar.MINUTE);

	    // Create a new instance of TimePickerDialog and return it
	    return new TimePickerDialog(context, this, hour, minute, DateFormat.is24HourFormat(context));
	}

	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
	    timeListner.setTime(hourOfDay, minute);
	}
    }

    @Override
    public void atualizar() {
	//Do Nothing
    }

}
