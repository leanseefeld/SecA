package br.furb.seca.model;

import java.util.Collection;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
	} finally {
	    db.close();
	}
    }

    public void insertDataFromAluno(Aluno aluno) {
	SQLiteDatabase db = getWritableDatabase();
	try {
	    insertCompromissos(db, aluno.getCompromissos());
	    insertDisciplinas(db, aluno.getDisciplinas());
	} finally {
	    db.close();
	}
    }

    private void insertDisciplinas(SQLiteDatabase db, Collection<Disciplina> disciplinas) {
	for (Disciplina disciplina : disciplinas) {
	    // TODO

	    ContentValues values = new ContentValues();
	    values.put("nrCodigo", disciplina.getCodigo());
	    values.put("dsNome", disciplina.getNome());
	    values.put("fk_professor", 3);
	    db.insert("DISCIPLINA", null, values);

	    insertHorarios(db, disciplina.getHorarios());
	}
    }

    private void insertHorarios(SQLiteDatabase db, Collection<Horario> horarios) {
	for (Horario horario : horarios) {
	    ContentValues values = new ContentValues();
	    values.put("nrPeriodo", horario.getPeriodo().getCodigo());
	    values.put("fk_disciplina", horario.getDisciplina().getCodigo());
	    values.put("nrDiaSemana", horario.getDiaSemana().getCodigo());
	    values.put("dsSala", horario.getSala());
	    db.insert("HORARIO", null, values);
	}
    }

    private void insertCompromissos(SQLiteDatabase db, Collection<Compromisso> compromissos) {
	// TODO Auto-generated method stub

    }

}
