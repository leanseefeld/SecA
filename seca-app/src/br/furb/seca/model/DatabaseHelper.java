package br.furb.seca.model;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import br.furb.seca.controller.Controller;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String NOME_BASE = "GUniver";
    private static final int VERSAO_ATUAL = 2;

    public DatabaseHelper(Context context) {
	super(context, NOME_BASE, null, VERSAO_ATUAL);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
	StringBuilder builder = new StringBuilder();

	builder.append(" CREATE TABLE PROFESSOR(");
	builder.append("   nrCodigo integer primary key not null, ");
	builder.append("   dsNome text");
	builder.append(" )");
	db.execSQL(builder.toString());

	builder = new StringBuilder();
	builder.append(" CREATE TABLE DISCIPLINA(");
	builder.append("   nrCodigo integer primary key not null, ");
	builder.append("   dsNome text, ");
	builder.append("   fk_professor, ");
	builder.append("   FOREIGN KEY(fk_professor) REFERENCES PROFESSOR(nrCodigo) ");
	builder.append(" )");
	db.execSQL(builder.toString());

	builder = new StringBuilder();
	builder.append(" CREATE TABLE PROVA(");
	builder.append("   dsNome text, ");
	builder.append("   vlNota real, ");
	builder.append("   vlPeso real, ");
	builder.append("   fk_disciplina, ");
	builder.append("   FOREIGN KEY(fk_disciplina) REFERENCES DISCIPLINA(nrCodigo) ");
	builder.append(" )");
	db.execSQL(builder.toString());

	builder = new StringBuilder();
	builder.append(" CREATE TABLE HORARIO(");
	builder.append("   nrCodigo integer primary key not null, ");
	builder.append("   nrPeriodo integer not null, ");
	builder.append("   nrHorarioInicio integer null, ");
	builder.append("   nrHorarioFim integer null, ");
	builder.append("   nrDiaSemana integer not null, "); //1 = domingo, 2 = segunda....
	builder.append("   dsSala char(7) not null, "); // X-123/4
	builder.append("   fk_disciplina integer not null, ");
	builder.append("   FOREIGN KEY(fk_disciplina) REFERENCES DISCIPLINA(nrCodigo) ");
	builder.append(" )");
	db.execSQL(builder.toString());

	builder = new StringBuilder();
	builder.append(" CREATE TABLE COMPROMISSO(");
	builder.append("   nrCodigo integer primary key not null, ");
	builder.append("   dsTitulo text, ");
	builder.append("   dsDescricao text, ");
	builder.append("   dtDataInicio text, ");
	builder.append("   dtDataFim text, ");
	builder.append("   flDiaTodo smallint, ");
	builder.append("   fk_disciplina integer null, ");
	builder.append("   FOREIGN KEY(fk_disciplina) REFERENCES DISCIPLINA(nrCodigo) ");
	builder.append(" )");
	db.execSQL(builder.toString());

	builder = new StringBuilder();
	builder.append(" CREATE TABLE LEMBRETE(");
	builder.append("   nrCodigo integer primary key not null, ");
	builder.append("   nrMinutosAntes integer not null, ");
	builder.append("   fk_compromisso integer not null, ");
	builder.append("   FOREIGN KEY(fk_compromisso) REFERENCES COMPROMISSO(nrCodigo) ");
	builder.append(" )");
	db.execSQL(builder.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void truncateTables() {
	SQLiteDatabase db = getWritableDatabase();
	try {
	    db.delete("PROFESSOR", null, null);
	    db.delete("DISCIPLINA", null, null);
	    db.delete("HORARIO", null, null);
	    db.delete("COMPROMISSO", null, null);
	    db.delete("LEMBRETE", null, null);
	    db.delete("PROVA", null, null);
	} finally {
	    db.close();
	}
    }

    public void insertDataFromAluno(Aluno aluno) {
	SQLiteDatabase db = getWritableDatabase();
	try {
	    insertCompromissos(db, aluno.getCompromissos());
	    insertDisciplinas(db, aluno.getDisciplinas());

	    Map<Integer, Professor> professores = new HashMap<Integer, Professor>();
	    for (Disciplina disciplina : aluno.getDisciplinas()) {
		insertProvas(db, disciplina.getProvas(), disciplina.getCodigo());
		professores.put(disciplina.getProfessor().getCodigo(), disciplina.getProfessor());
	    }

	    for (Map.Entry<Integer, Professor> entry : professores.entrySet()) {
		insertProfessor(db, entry.getValue());
	    }

	} finally {
	    db.close();
	}
    }

    private void insertDisciplinas(SQLiteDatabase db, Collection<Disciplina> disciplinas) {
	for (Disciplina disciplina : disciplinas) {
	    ContentValues values = new ContentValues();
	    values.put("nrCodigo", disciplina.getCodigo());
	    values.put("dsNome", disciplina.getNome());
	    values.put("fk_professor", disciplina.getProfessor().getCodigo());
	    db.insert("DISCIPLINA", null, values);

	    insertHorarios(db, disciplina.getHorarios(), disciplina);
	}
    }

    private void insertProfessor(SQLiteDatabase db, Professor professor) {
	ContentValues values = new ContentValues();
	values.put("nrCodigo", professor.getCodigo());
	values.put("dsNome", professor.getNome());
	db.insert("PROFESSOR", null, values);
    }

    private void insertHorarios(SQLiteDatabase db, Collection<Horario> horarios, Disciplina disciplina) {
	for (Horario horario : horarios) {
	    horario.setDisciplina(disciplina);
	    ContentValues values = new ContentValues();
	    values.put("nrPeriodo", horario.getPeriodo().getCodigo());
	    values.put("fk_disciplina", disciplina.getCodigo());
	    values.put("nrDiaSemana", horario.getDiaSemana().getCodigo());
	    values.put("dsSala", horario.getSala());
	    db.insert("HORARIO", null, values);
	}
    }

    private void insertProvas(SQLiteDatabase db, Collection<Prova> provas, int codigoDisciplina) {
	for (Prova prova : provas) {
	    ContentValues values = new ContentValues();
	    values.put("dsNome", prova.getNomeAvaliacao());
	    values.put("vlNota", prova.getNota());
	    values.put("vlPeso", prova.getPeso());
	    values.put("fk_disciplina", codigoDisciplina);
	    db.insert("PROVA", null, values);
	}
    }

    public void insertCompromisso(SQLiteDatabase db, Compromisso compromisso) {
	ContentValues values = new ContentValues();
	values.put("nrCodigo", compromisso.getCodigo());
	values.put("dsTitulo", compromisso.getTitulo());
	values.put("dsDescricao", compromisso.getDescricao());

	Date dataInicial = compromisso.getDataInicio();
	Date dataFinal = compromisso.getDataFim();

	if (compromisso.isDiaTodo()) {

	    Calendar cl = Calendar.getInstance();

	    cl.setTime(dataInicial);
	    cl.set(Calendar.HOUR, 0);
	    cl.set(Calendar.MINUTE, 0);
	    cl.set(Calendar.AM_PM, Calendar.AM);
	    dataInicial = cl.getTime();

	    cl.setTime(dataFinal);
	    cl.set(Calendar.HOUR, 11);
	    cl.set(Calendar.MINUTE, 59);
	    cl.set(Calendar.AM_PM, Calendar.PM);
	    dataFinal = cl.getTime();
	    Log.d("MEU", "Data Final: " + cl.getTime().toString());
	}

	values.put("dtDataInicio", dateToSqLiteDate(dataInicial));
	values.put("dtDataFim", dateToSqLiteDate(dataFinal));
	values.put("flDiaTodo", compromisso.isDiaTodo());
	db.insert("COMPROMISSO", null, values);
    }

    private void insertCompromissos(SQLiteDatabase db, Collection<Compromisso> compromissos) {
	for (Compromisso compromisso : compromissos) {
	    insertCompromisso(db, compromisso);
	}
    }

    public static Date dateSqLiteToDate(String data) throws java.text.ParseException {
	return Controller.SIMPLE_DATE_FORMAT.parse(data);
    }

    public static String dateToSqLiteDate(Date data) {
	return Controller.SIMPLE_DATE_FORMAT.format(data);
    }

}
