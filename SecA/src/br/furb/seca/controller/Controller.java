package br.furb.seca.controller;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.furb.seca.model.Compromisso;
import br.furb.seca.model.DatabaseHelper;
import br.furb.seca.model.Disciplina;
import br.furb.seca.model.Horario;
import br.furb.seca.model.Professor;

public class Controller {

    DatabaseHelper dbHelper;

    public Controller(Context c) {
	dbHelper = new DatabaseHelper(c);
    }

    public List<Horario> buscarHorarios() {
	List<Horario> dias = new ArrayList<Horario>();
	int index;
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
	    hor.setDiaSemana(cursor.getInt(3));
	    hor.setDisciplina(new Disciplina(cursor.getString(1), new Professor(cursor.getString(0))));
	    hor.setPeriodo(cursor.getInt(2));
	    hor.setSala(cursor.getString(4));

	    dias.add(hor);
	    cursor.moveToNext();
	}
	return dias;
    }

    public void sincronizar() {
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

	insertHorario(db, 5, 12, 5, "S-415");
	insertHorario(db, 5, 13, 5, "S-415");
	insertHorario(db, 5, 14, 5, "S-415");
	insertHorario(db, 5, 15, 5, "S-415");
    }

    private void insertHorario(SQLiteDatabase db, int dia, int hrInicio, int disciplina, String sala) {
	ContentValues values = new ContentValues();
	values.put("nrPeriodo", hrInicio);
	values.put("fk_disciplina", disciplina);
	values.put("nrDiaSemana", dia);
	values.put("dsSala", sala);
	db.insert("HORARIO", null, values);
    }

    public List<Compromisso> buscarCompromissos(Disciplina disciplina) {
	throw new UnsupportedOperationException("Não implementado ainda :)");
    }

    public void gravarCompromisso(Compromisso compromisso) {
	if (compromisso.get_id() == 0) {
	    // gravar
	} else {
	    // alterar
	}

	throw new UnsupportedOperationException("Não implementado ainda :)");
    }

}
