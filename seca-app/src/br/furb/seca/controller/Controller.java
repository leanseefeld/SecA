package br.furb.seca.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;
import br.furb.seca.model.Aluno;
import br.furb.seca.model.Compromisso;
import br.furb.seca.model.DatabaseHelper;
import br.furb.seca.model.DiaSemana;
import br.furb.seca.model.Disciplina;
import br.furb.seca.model.Horario;
import br.furb.seca.model.WebServiceConnector;

public class Controller {

    private DatabaseHelper dbHelper;
    private Context context;

    public Controller(Context context) {
	this.context = context;
	this.dbHelper = new DatabaseHelper(context);
    }

    public List<Horario> buscarHorarios() {
	List<Horario> dias = new ArrayList<Horario>();
	SQLiteDatabase db = dbHelper.getReadableDatabase();

	StringBuilder builder = new StringBuilder();
	builder.append(" SELECT PROFESSOR.dsNome, DISCIPLINA.dsNome, ");
	builder.append(" HORARIO.nrPeriodo, HORARIO.nrDiaSemana, HORARIO.dsSala ");
	builder.append(" FROM HORARIO ");
	builder.append(" INNER JOIN DISCIPLINA ON DISCIPLINA.nrCodigo = HORARIO.fk_disciplina ");
	builder.append(" INNER JOIN PROFESSOR ON PROFESSOR.nrCodigo = DISCIPLINA.fk_professor ");
	builder.append(" ORDER BY HORARIO.nrDiaSemana");

	Cursor cursor = db.rawQuery(builder.toString(), null);
	cursor.moveToFirst();
	while (cursor.isAfterLast() == false) {
	    Horario hor = new Horario();
	    hor.setDiaSemana(DiaSemana.fromCodigo(cursor.getInt(3)));
	    hor.setDisciplina(new Disciplina(cursor.getString(1), cursor.getString(0)));
	    hor.setPeriodo(cursor.getInt(2));
	    hor.setSala(cursor.getString(4));

	    dias.add(hor);
	    cursor.moveToNext();
	}
	Log.d("MEU", "nrDias:" + dias.size());
	return dias;
    }

    public List<Horario> buscarProximosHorarios(int diaAtual) {
	List<Horario> horarios = this.buscarHorarios();
	List<Horario> horariosRemover = new ArrayList<Horario>();

	for (int i = 0; i < horarios.size(); i++) {
	    if (horarios.get(i).getDiaSemana().getCodigo() < diaAtual) {
		horariosRemover.add(horarios.get(i));
	    }
	}

	//Remove todos para depois adicionar no fim da lista
	horarios.removeAll(horariosRemover);
	horarios.addAll(horariosRemover);

	return horarios;
    }

    public List<Map<String, String>> horariosFormatados() {
	List<Map<String, String>> dados = new ArrayList<Map<String, String>>();
	List<Horario> horarios = this.buscarHorarios();

	DiaSemana diaAnterior = null;
	Disciplina disciplinaAnteior = null;

	Map<String, String> map = null;
	for (Horario horario : horarios) {

	    if (map != null && horario.getDiaSemana().equals(diaAnterior)
		    && horario.getDisciplina().getNome().equals(disciplinaAnteior.getNome())) {
		map.put("horarioFim", horario.getPeriodo().getFim());
	    } else {

		map = new HashMap<String, String>();
		map.put("dia", horario.getDescricaoDiaSemana());
		map.put("disciplina", horario.getDisciplina().getNome());
		map.put("professor", horario.getDisciplina().getNomeProfessor());
		map.put("horarioInicio", horario.getPeriodo().getInicio());
		map.put("horarioFim", horario.getPeriodo().getFim());
		map.put("sala", horario.getSala());
		dados.add(map);
	    }

	    diaAnterior = horario.getDiaSemana();
	    disciplinaAnteior = horario.getDisciplina();
	}

	return dados;
    }

    public List<Map<String, String>> buscarProximosHorariosFormatados(int quantidade) {
	List<Map<String, String>> dados = new ArrayList<Map<String, String>>();
	int diaDaSemana = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
	List<Horario> horarios = this.buscarProximosHorarios(diaDaSemana);

	DiaSemana diaAnterior = null;
	Disciplina disciplinaAnteior = null;

	Map<String, String> map = null;
	for (Horario horario : horarios) {

	    if (map != null && horario.getDiaSemana().equals(diaAnterior)
		    && horario.getDisciplina().getNome().equals(disciplinaAnteior.getNome())) {
		map.put("horarioFim", horario.getPeriodo().getFim());
	    } else {

		if (quantidade != 0 && quantidade == dados.size()) {
		    break;
		}

		map = new HashMap<String, String>();
		map.put("dia", horario.getDescricaoDiaSemana());
		map.put("disciplina", horario.getDisciplina().getNome());
		map.put("professor", horario.getDisciplina().getNomeProfessor());
		map.put("horarioInicio", horario.getPeriodo().getInicio());
		map.put("horarioFim", horario.getPeriodo().getFim());
		map.put("sala", horario.getSala());
		dados.add(map);
	    }

	    diaAnterior = horario.getDiaSemana();
	    disciplinaAnteior = horario.getDisciplina();
	}

	return dados;
    }

    public void sincronizar() {
	/* talvez passar a URL do webservice aqui. De qualquer forma, 
	 * pegar do Android.manifest ou outro lugar que não seja hard coded */
	WebServiceConnector wsConnector = new WebServiceConnector();
	try {
	    Aluno aluno = null; // TODO: obter aluno tentando logar ou logado
	    aluno = wsConnector.carregarAluno(aluno);

	    // TODO: persistir os dados no banco
	} catch (Exception e) {
	    Toast.makeText(context, "Não foi possível sincronizar os dados: " + e.getMessage(), Toast.LENGTH_LONG)
		    .show();
	}
    }

    @Deprecated
    public void _sincronizar() {
	SQLiteDatabase db = dbHelper.getWritableDatabase();

	db.delete("PROFESSOR", "1=1", null);
	db.delete("DISCIPLINA", "1=1", null);
	db.delete("HORARIO", "1=1", null);

	// CADASTRO DE PROFESSORES
	ContentValues values = new ContentValues();
	values.put("nrCodigo", 1);
	values.put("dsNome", "Everaldo Artur Grahl");
	db.insert("PROFESSOR", null, values);

	values = new ContentValues();
	values.put("nrCodigo", 2);
	values.put("dsNome", "Jaison Hinkel");
	db.insert("PROFESSOR", null, values);

	values = new ContentValues();
	values.put("nrCodigo", 3);
	values.put("dsNome", "Paulo Fernando da Silva");
	db.insert("PROFESSOR", null, values);

	values = new ContentValues();
	values.put("nrCodigo", 4);
	values.put("dsNome", "Jhony Alceu Pereira");
	db.insert("PROFESSOR", null, values);

	values = new ContentValues();
	values.put("nrCodigo", 5);
	values.put("dsNome", "Cláudio Ratke");
	db.insert("PROFESSOR", null, values);

	// CADASTRO DE DISCIPLINAS
	values = new ContentValues();
	values.put("nrCodigo", 1);
	values.put("dsNome", "Comportamento Organizacional");
	values.put("fk_professor", 2);
	db.insert("DISCIPLINA", null, values);

	values = new ContentValues();
	values.put("nrCodigo", 2);
	values.put("dsNome", "Processo de Software I");
	values.put("fk_professor", 1);
	db.insert("DISCIPLINA", null, values);

	values = new ContentValues();
	values.put("nrCodigo", 3);
	values.put("dsNome", "Sistemas Distribuídos");
	values.put("fk_professor", 3);
	db.insert("DISCIPLINA", null, values);

	values = new ContentValues();
	values.put("nrCodigo", 4);
	values.put("dsNome", "Android");
	values.put("fk_professor", 4);
	db.insert("DISCIPLINA", null, values);

	values = new ContentValues();
	values.put("nrCodigo", 5);
	values.put("dsNome", "Banco de Dados II");
	values.put("fk_professor", 5);
	db.insert("DISCIPLINA", null, values);

	// CADASTRO DOS HORARIOS
	insertHorario(db, 2, 12, 2, "S-415");
	insertHorario(db, 2, 13, 2, "S-415");
	insertHorario(db, 2, 14, 1, "S-415");
	insertHorario(db, 2, 15, 1, "S-415");

	insertHorario(db, 3, 12, 3, "S-415");
	insertHorario(db, 3, 13, 3, "S-415");

	insertHorario(db, 3, 14, 2, "S-415");
	insertHorario(db, 3, 15, 2, "S-415");

	insertHorario(db, 4, 12, 1, "S-415");
	insertHorario(db, 4, 13, 1, "S-415");

	insertHorario(db, 4, 14, 3, "S-415");
	insertHorario(db, 4, 15, 3, "S-415");

	insertHorario(db, 5, 12, 4, "S-415");
	insertHorario(db, 5, 13, 4, "S-415");
	insertHorario(db, 5, 14, 4, "S-415");
	insertHorario(db, 5, 15, 4, "S-415");

	insertHorario(db, 6, 12, 5, "S-415");
	insertHorario(db, 6, 13, 5, "S-415");
	insertHorario(db, 6, 14, 5, "S-415");
	insertHorario(db, 6, 15, 5, "S-415");
    }

    private void insertHorario(SQLiteDatabase db, int dia, int hrInicio, int disciplina, String sala) {
	ContentValues values = new ContentValues();
	values.put("nrPeriodo", hrInicio);
	values.put("fk_disciplina", disciplina);
	values.put("nrDiaSemana", dia);
	values.put("dsSala", sala);
	db.insert("HORARIO", null, values);
    }

    public void gravarCompromisso(Compromisso compromisso) {

	//TODO: ENVIR PARA O WEBSERVICE

	if (compromisso.getId() == 0) {
	    final Random numRandomico = new Random();

	    SQLiteDatabase db = dbHelper.getWritableDatabase();
	    ContentValues values = new ContentValues();
	    values.put("nrCodigo", numRandomico.nextInt()); //TODO: o código virá do web-service
	    values.put("dsTitulo", compromisso.getTitulo());
	    values.put("dsDescricao", compromisso.getDescricao());
	    values.put("dtDataInicio", dateToSqLiteDate(compromisso.getDataInicio()));
	    values.put("dtDataFim", dateToSqLiteDate(compromisso.getDataFim()));
	    values.put("flDiaTodo", compromisso.isDiaTodo());
	    if (compromisso.getDisciplina() != null) {
		values.put("fk_disciplina", compromisso.getDisciplina().getCodigo());
	    }

	    compromisso.setId(db.insert("COMPROMISSO", null, values));

	    Log.d("MEU", "ID do compromisso gravado: " + compromisso.getId());

	} else {
	    SQLiteDatabase db = dbHelper.getWritableDatabase();
	    ContentValues values = new ContentValues();
	    values.put("dsTitulo", compromisso.getTitulo());
	    values.put("dsDescricao", compromisso.getDescricao());
	    values.put("dtDataInicio", dateToSqLiteDate(compromisso.getDataInicio()));
	    values.put("dtDataFim", dateToSqLiteDate(compromisso.getDataFim()));
	    values.put("flDiaTodo", compromisso.isDiaTodo());
	    values.put("fk_disciplina", compromisso.getDisciplina().getCodigo());

	    db.update("COMPROMISSO", values, "_id=" + compromisso.getId(), null);
	    Log.d("MEU", "compromisso " + compromisso.getId() + " - " + compromisso.getTitulo() + " alterado");
	}

	this.gravarLembretes(compromisso);
    }

    public void gravarLembretes(Compromisso compromisso) {
	if (compromisso.getLembretes().size() == 0)
	    return;
    }

    public List<Disciplina> buscarDisciplinas() {

	List<Disciplina> disciplinas = new ArrayList<Disciplina>();
	SQLiteDatabase db = dbHelper.getReadableDatabase();

	StringBuilder builder = new StringBuilder();
	builder.append(" SELECT DISCIPLINA._id, DISCIPLINA.nrCodigo, DISCIPLINA.dsNome, PROFESSOR.dsNome ");
	builder.append(" FROM DISCIPLINA ");
	builder.append(" INNER JOIN PROFESSOR ");
	builder.append(" ON PROFESSOR.nrCodigo = DISCIPLINA.fk_professor ");

	Cursor cursor = db.rawQuery(builder.toString(), null);
	cursor.moveToFirst();
	while (cursor.isAfterLast() == false) {
	    Disciplina disc = new Disciplina();
	    disc.setId(cursor.getInt(0));
	    disc.setCodigo(cursor.getInt(1));
	    disc.setNome(cursor.getString(2));
	    disc.setNomeProfessor(cursor.getString(3));
	    disciplinas.add(disc);

	    cursor.moveToNext();
	}

	return disciplinas;
    }

    public Disciplina buscarDisciplina(long _id) {

	Disciplina disciplina = null;
	SQLiteDatabase db = dbHelper.getReadableDatabase();

	StringBuilder builder = new StringBuilder();
	builder.append(" SELECT DISCIPLINA._id, DISCIPLINA.nrCodigo, DISCIPLINA.dsNome, PROFESSOR.dsNome ");
	builder.append(" FROM DISCIPLINA ");
	builder.append(" INNER JOIN PROFESSOR ");
	builder.append(" ON PROFESSOR.nrCodigo = DISCIPLINA.fk_professor ");
	builder.append(" WHERE DISCIPLINA._id = " + _id);

	Cursor cursor = db.rawQuery(builder.toString(), null);
	cursor.moveToFirst();
	if (cursor.isAfterLast() == false) {
	    disciplina = new Disciplina();
	    disciplina.setId(cursor.getInt(0));
	    disciplina.setCodigo(cursor.getInt(1));
	    disciplina.setNome(cursor.getString(2));
	    disciplina.setNomeProfessor(cursor.getString(3));
	}

	return disciplina;
    }

    public Disciplina buscarDisciplinaPorCodigo(int codigo) {

	Disciplina disciplina = null;
	SQLiteDatabase db = dbHelper.getReadableDatabase();

	StringBuilder builder = new StringBuilder();
	builder.append(" SELECT DISCIPLINA._id, DISCIPLINA.nrCodigo, DISCIPLINA.dsNome, PROFESSOR.dsNome ");
	builder.append(" FROM DISCIPLINA ");
	builder.append(" INNER JOIN PROFESSOR ");
	builder.append(" ON PROFESSOR.nrCodigo = DISCIPLINA.fk_professor ");
	builder.append(" WHERE DISCIPLINA.nrCodigo = " + codigo);

	Cursor cursor = db.rawQuery(builder.toString(), null);
	cursor.moveToFirst();
	if (cursor.isAfterLast() == false) {
	    disciplina = new Disciplina();
	    disciplina.setId(cursor.getInt(0));
	    disciplina.setCodigo(cursor.getInt(1));
	    disciplina.setNome(cursor.getString(2));
	    disciplina.setNomeProfessor(cursor.getString(3));
	}

	return disciplina;
    }

    private String dateToSqLiteDate(Date data) {
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
	return dateFormat.format(data);
    }

    private Date dateSqLiteToDate(String data) throws java.text.ParseException {
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
	return dateFormat.parse(data);
    }

    public List<Compromisso> buscarProximosCompromissos(int quantidade) throws java.text.ParseException {
	List<Compromisso> compromissos = new ArrayList<Compromisso>();
	SQLiteDatabase db = dbHelper.getReadableDatabase();

	StringBuilder builder = new StringBuilder();
	builder.append(" SELECT _id, nrCodigo, flDiaTodo, dsTitulo, dsDescricao, dtDataInicio, dtDataFim, fk_disciplina ");
	builder.append(" FROM COMPROMISSO ");
	builder.append(" WHERE dtDataInicio >= '" + dateToSqLiteDate(Calendar.getInstance().getTime()) + "'");
	builder.append(" ORDER BY dtDataInicio ");
	builder.append(" LIMIT " + quantidade);

	Cursor cursor = db.rawQuery(builder.toString(), null);
	cursor.moveToFirst();
	while (cursor.isAfterLast() == false) {
	    Compromisso compromisso = new Compromisso();
	    compromisso.setId(cursor.getInt(0));
	    compromisso.setCodigo(cursor.getInt(1));
	    compromisso.setIsDiaTodo(cursor.getInt(2) == 1 ? true : false);
	    compromisso.setTitulo(cursor.getString(3));
	    compromisso.setDescricao(cursor.getString(4));
	    compromisso.setDataInicio(dateSqLiteToDate(cursor.getString(5)));
	    compromisso.setDataFim(dateSqLiteToDate(cursor.getString(6)));

	    compromisso.setDisciplina(this.buscarDisciplinaPorCodigo(cursor.getInt(7)));

	    compromissos.add(compromisso);
	    cursor.moveToNext();
	}
	return compromissos;
    }

}
