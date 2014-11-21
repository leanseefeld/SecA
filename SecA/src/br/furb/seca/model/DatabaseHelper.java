package br.furb.seca.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String NOME_BASE = "GUniver";
    private static final int VERSAO_ATUAL = 1;

    public DatabaseHelper(Context context) {
	super(context, NOME_BASE, null, VERSAO_ATUAL);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
	StringBuilder builder = new StringBuilder();

	builder.append(" CREATE TABLE PROFESSOR(");
	builder.append(" _id integer primary key not null, ");
	builder.append(" nrCodigo integer not null, ");
	builder.append(" dsNome text");
	builder.append(" )");
	db.execSQL(builder.toString());

	builder = new StringBuilder();
	builder.append(" CREATE TABLE DISCIPLINA(");
	builder.append(" _id integer primary key not null, ");
	builder.append(" nrCodigo integer not null, ");
	builder.append(" dsNome text, ");
	builder.append(" fk_professor, ");
	builder.append(" FOREIGN KEY(fk_professor) REFERENCES PROFESSOR(nrCodigo) ");
	builder.append(" )");
	db.execSQL(builder.toString());

	builder = new StringBuilder();
	builder.append(" CREATE TABLE HORARIO(");
	builder.append(" _id integer primary key not null, ");
	builder.append(" nrPeriodo integer not null, ");
	builder.append(" nrHorarioInicio integer null, ");
	builder.append(" nrHorarioFim integer null, ");
	builder.append(" nrDiaSemana integer not null, "); //1 = domingo, 2 = segunda....
	builder.append(" dsSala char(7) not null, "); // X-123/4
	builder.append(" fk_disciplina integer not null, ");
	builder.append(" FOREIGN KEY(fk_disciplina) REFERENCES DISCIPLINA(nrCodigo) ");
	builder.append(" )");
	db.execSQL(builder.toString());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
