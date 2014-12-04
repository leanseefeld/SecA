package br.furb.seca.controller;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;
import br.furb.seca.MyFragment;
import br.furb.seca.R;
import br.furb.seca.model.Aluno;
import br.furb.seca.model.Compromisso;
import br.furb.seca.model.DatabaseHelper;
import br.furb.seca.model.DiaSemana;
import br.furb.seca.model.Disciplina;
import br.furb.seca.model.Horario;
import br.furb.seca.model.Professor;
import br.furb.seca.model.Prova;
import br.furb.seca.model.WebServiceConnector;
import br.furb.seca.model.WebServiceTask;

public class Controller {

    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
	    Locale.getDefault());

    private DatabaseHelper dbHelper;
    private Context context;
    private static Aluno currentAluno = new Aluno("teste", "123"); // TODO: implementar tela de login e remover isso
    public List<MyFragment> fragmentos = new ArrayList<MyFragment>();

    public Controller(Context context) {
	this.context = context;
	this.dbHelper = new DatabaseHelper(context);
    }

    public void addFragment(MyFragment f) {
	fragmentos.add(f);
    }

    public void removeFragment(MyFragment f) {
	fragmentos.add(f);
    }

    public static Aluno getCurrentAluno() {
	return currentAluno;
    }

    public static void setCurrentAluno(Aluno currentAluno) {
	Controller.currentAluno = currentAluno;
    }

    public void syncSucceeded(Aluno aluno) {
	dbHelper.truncateTables();
	dbHelper.insertDataFromAluno(aluno);
	Toast.makeText(context, R.string.sincronizacao_sucedida, Toast.LENGTH_LONG).show();

	setCurrentAluno(aluno);

	for (MyFragment fragment : fragmentos) {
	    fragment.atualizar();
	}
    }

    public void syncFailed(String message) {
	Toast toast = Toast.makeText(context, "Não foi possível sincronizar os dados: " + message, Toast.LENGTH_LONG);
	//	toast.setView(callbackView);
	toast.show();
    }

    public void sincronizarWebService(ConnectivityManager connMgr) {
	NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
	if (networkInfo == null || !networkInfo.isConnected()) {
	    Toast.makeText(context, R.string.error_sem_conexao, Toast.LENGTH_SHORT).show();
	    return;
	}

	WebServiceConnector wsConnector = new WebServiceConnector(context.getResources().getString(
		R.string.endereco_webservice));

	WebServiceTask wsTask = new WebServiceTask(wsConnector, this);
	wsTask.execute(getCurrentAluno());
    }

    @Deprecated
    public void sincronizarLocal() {
	SQLiteDatabase db = dbHelper.getWritableDatabase();

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

    public List<Horario> buscarHorarios() {
	List<Horario> dias = new ArrayList<Horario>();
	SQLiteDatabase db = dbHelper.getReadableDatabase();

	StringBuilder builder = new StringBuilder();
	builder.append(" SELECT PROFESSOR.nrCodigo, PROFESSOR.dsNome, DISCIPLINA.dsNome, ");
	builder.append(" HORARIO.nrPeriodo, HORARIO.nrDiaSemana, HORARIO.dsSala ");
	builder.append(" FROM HORARIO ");
	builder.append(" INNER JOIN DISCIPLINA ON DISCIPLINA.nrCodigo = HORARIO.fk_disciplina ");
	builder.append(" INNER JOIN PROFESSOR ON PROFESSOR.nrCodigo = DISCIPLINA.fk_professor ");
	builder.append(" ORDER BY HORARIO.nrDiaSemana");

	Cursor cursor = db.rawQuery(builder.toString(), null);
	cursor.moveToFirst();
	while (!cursor.isAfterLast()) {
	    int idx = 0;
	    Professor professor = new Professor(cursor.getInt(idx++), cursor.getString(idx++)); // PROFESSOR.nrCodigo, PROFESSOR.dsNome
	    Disciplina disciplina = new Disciplina(cursor.getString(idx++), professor); // DISCIPLINA.dsNome
	    Horario hor = new Horario();
	    hor.setDisciplina(disciplina);
	    hor.setPeriodo(cursor.getInt(idx++)); // HORARIO.nrPeriodo
	    hor.setDiaSemana(DiaSemana.fromCodigo(cursor.getInt(idx++))); // HORARIO.nrDiaSemana
	    hor.setSala(cursor.getString(idx++)); // HORARIO.dsSala

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

    public List<Map<String, String>> buscarHorariosFormatados() {
	List<Map<String, String>> dados = new ArrayList<Map<String, String>>();
	List<Horario> horarios = this.buscarHorarios();

	DiaSemana diaAnterior = null;
	Disciplina disciplinaAnterior = null;

	Map<String, String> map = null;
	for (Horario horario : horarios) {
	    // se são períodos seguidos da mesma disciplina
	    if (map != null && horario.getDiaSemana().equals(diaAnterior)
		    && horario.getDisciplina().getNome().equals(disciplinaAnterior.getNome())) {
		map.put("horarioFim", horario.getPeriodo().getFim());
	    } else {
		map = new HashMap<String, String>();
		map.put("dia", horario.getDescricaoDiaSemana());
		map.put("disciplina", horario.getDisciplina().getNome());
		map.put("professor", horario.getDisciplina().getProfessor().getNome());
		map.put("horarioInicio", horario.getPeriodo().getInicio());
		map.put("horarioFim", horario.getPeriodo().getFim());
		map.put("sala", horario.getSala());
		dados.add(map);
	    }

	    diaAnterior = horario.getDiaSemana();
	    disciplinaAnterior = horario.getDisciplina();
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
	    // se são períodos seguidos da mesma disciplina
	    if (map != null && horario.getDiaSemana().equals(diaAnterior)
		    && horario.getDisciplina().getNome().equals(disciplinaAnteior.getNome())) {
		map.put("horarioFim", horario.getPeriodo().getFim());
	    } else {
		if (quantidade > 0 && quantidade == dados.size()) {
		    break;
		}

		map = new HashMap<String, String>();
		map.put("dia", horario.getDescricaoDiaSemana());
		map.put("disciplina", horario.getDisciplina().getNome());
		map.put("professor", horario.getDisciplina().getProfessor().getNome());
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

    @Deprecated
    private void insertHorario(SQLiteDatabase db, int dia, int hrInicio, int disciplina, String sala) {
	ContentValues values = new ContentValues();
	values.put("nrPeriodo", hrInicio);
	values.put("fk_disciplina", disciplina);
	values.put("nrDiaSemana", dia);
	values.put("dsSala", sala);
	db.insert("HORARIO", null, values);
    }

    public void gravarCompromisso(Compromisso compromisso) {
	// TODO: ENVIAR PARA O WEBSERVICE, em uma Task para não travar a UI

	SQLiteDatabase db = dbHelper.getWritableDatabase();

	dbHelper.insertCompromisso(db, compromisso);
	/*
	ContentValues values = new ContentValues();

	//	    final Random numRandomico = new Random();
	//	    values.put("nrCodigo", numRandomico.nextInt()); // TODO: o código virá do web-service
	values.put("dsTitulo", compromisso.getTitulo());
	values.put("dsDescricao", compromisso.getDescricao());
	values.put("dtDataInicio", DatabaseHelper.dateToSqLiteDate(compromisso.getDataInicio()));

	Date dataFinal = compromisso.getDataFim();

	if (compromisso.isDiaTodo()) {
	    Calendar cl = Calendar.getInstance();
	    cl.setTime(dataFinal);
	    cl.set(Calendar.HOUR, 23);
	    cl.set(Calendar.MINUTE, 59);
	    dataFinal = cl.getTime();
	}

	values.put("dtDataFim", DatabaseHelper.dateToSqLiteDate(dataFinal));
	values.put("flDiaTodo", compromisso.isDiaTodo());

	if (compromisso.getDisciplina() != null) {
	    values.put("fk_disciplina", compromisso.getDisciplina().getCodigo());
	}
	if (compromisso.getCodigo() == 0) {
	    compromisso.setCodigo(db.insert("COMPROMISSO", null, values));
	} else {
	    db.update("COMPROMISSO", values, "nrCodigo=?", new String[] { String.valueOf(compromisso.getCodigo()) });
	}

	this.gravarLembretes(compromisso);*/
    }

    public void gravarLembretes(Compromisso compromisso) {
	if (compromisso.getLembretes().size() == 0)
	    return;
	// TODO
    }

    public List<Disciplina> buscarDisciplinas() {
	List<Disciplina> disciplinas = new ArrayList<Disciplina>();
	SQLiteDatabase db = dbHelper.getReadableDatabase();

	StringBuilder builder = new StringBuilder();
	builder.append(" SELECT PROFESSOR.nrCodigo, PROFESSOR.dsNome, DISCIPLINA.nrCodigo, DISCIPLINA.dsNome ");
	builder.append(" FROM DISCIPLINA ");
	builder.append(" INNER JOIN PROFESSOR ");
	builder.append(" ON PROFESSOR.nrCodigo = DISCIPLINA.fk_professor ");

	Cursor cursor = db.rawQuery(builder.toString(), null);
	cursor.moveToFirst();
	while (!cursor.isAfterLast()) {
	    int idx = 0;
	    Professor professor = new Professor(cursor.getInt(idx++), cursor.getString(idx++));
	    Disciplina disc = new Disciplina();
	    disc.setCodigo(cursor.getInt(idx++));
	    disc.setNome(cursor.getString(idx++));
	    disc.setProfessor(professor);
	    disc.setProvas(this.buscarProvas(disc.getCodigo()));

	    disciplinas.add(disc);

	    cursor.moveToNext();
	}

	return disciplinas;
    }

    public List<Prova> buscarProvas(int codigoDisciplina) {
	List<Prova> provas = new ArrayList<Prova>();
	SQLiteDatabase db = dbHelper.getReadableDatabase();

	StringBuilder builder = new StringBuilder();
	builder.append(" SELECT vlNota, vlPeso, dsNome ");
	builder.append(" FROM PROVA ");
	builder.append(" WHERE fk_disciplina = ").append(codigoDisciplina);

	Cursor cursor = db.rawQuery(builder.toString(), null);
	cursor.moveToFirst();
	while (!cursor.isAfterLast()) {
	    int idx = 0;
	    Prova prova = new Prova(cursor.getFloat(idx++), // nota 
		    cursor.getFloat(idx++), // peso
		    cursor.getString(idx++)); // nome
	    provas.add(prova);
	    cursor.moveToNext();
	}

	return provas;
    }

    public Disciplina buscarDisciplina(long codigo) {
	Disciplina disciplina = null;
	SQLiteDatabase db = dbHelper.getReadableDatabase();

	StringBuilder builder = new StringBuilder();
	builder.append(" SELECT ");
	builder.append(" PROFESSOR.nrCodigo, PROFESSOR.dsNome, ");
	builder.append(" DISCIPLINA.nrCodigo, DISCIPLINA.dsNome ");
	builder.append(" FROM DISCIPLINA ");
	builder.append(" LEFT JOIN PROFESSOR ");
	builder.append(" ON PROFESSOR.nrCodigo = DISCIPLINA.fk_professor ");
	builder.append(" WHERE DISCIPLINA.nrCodigo = ").append(codigo);

	Cursor cursor = db.rawQuery(builder.toString(), null);
	cursor.moveToFirst();
	if (cursor.isAfterLast() == false) {
	    int idx = 0;
	    Professor professor = new Professor(cursor.getInt(idx++), cursor.getString(idx++));
	    disciplina = new Disciplina();
	    disciplina.setCodigo(cursor.getInt(idx++));
	    disciplina.setNome(cursor.getString(idx++));
	    disciplina.setProfessor(professor);

	    disciplina.setProvas(this.buscarProvas(disciplina.getCodigo()));
	}

	return disciplina;
    }

    public List<Compromisso> buscarProximosCompromissos(int quantidade) throws java.text.ParseException {
	List<Compromisso> compromissos = new ArrayList<Compromisso>();
	SQLiteDatabase db = dbHelper.getReadableDatabase();

	StringBuilder builder = new StringBuilder();
	builder.append(" SELECT nrCodigo, flDiaTodo, dsTitulo, dsDescricao, dtDataInicio, dtDataFim, fk_disciplina ");
	builder.append(" FROM COMPROMISSO ");
	builder.append(" WHERE dtDataFim >= '")
		.append(DatabaseHelper.dateToSqLiteDate(Calendar.getInstance().getTime())).append("' ");
	builder.append(" ORDER BY dtDataInicio ");
	builder.append(" LIMIT ").append(quantidade);

	Cursor cursor = db.rawQuery(builder.toString(), null);
	cursor.moveToFirst();
	while (!cursor.isAfterLast()) {
	    int idx = 0;
	    Compromisso compromisso = new Compromisso();
	    compromisso.setCodigo(cursor.getInt(idx++));
	    compromisso.setDiaTodo(cursor.getInt(idx++) != 0);
	    compromisso.setTitulo(cursor.getString(idx++));
	    compromisso.setDescricao(cursor.getString(idx++));
	    compromisso.setDataInicio(DatabaseHelper.dateSqLiteToDate(cursor.getString(idx++)));
	    compromisso.setDataFim(DatabaseHelper.dateSqLiteToDate(cursor.getString(idx++)));

	    compromisso.setDisciplina(this.buscarDisciplina(cursor.getInt(idx++)));

	    compromissos.add(compromisso);
	    cursor.moveToNext();
	}
	return compromissos;
    }

    public String calcularMedia(List<Prova> provas) {
	if (provas.isEmpty())
	    return "-";
	float notaTotal = 0;
	float pesoTotal = 0;
	for (Prova prova : provas) {
	    notaTotal += (prova.getNota() * prova.getPeso());
	    pesoTotal += prova.getPeso();
	}

	float media = notaTotal / pesoTotal;

	DecimalFormat df = new DecimalFormat("0.00");
	df.setRoundingMode(RoundingMode.DOWN);
	return df.format(media);
    }

}
