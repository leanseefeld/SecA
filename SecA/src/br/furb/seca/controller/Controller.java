package br.furb.seca.controller;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.furb.seca.model.DatabaseHelper;
import br.furb.seca.model.DiaSemana;
import br.furb.seca.model.Disciplina;
import br.furb.seca.model.Horario;
import br.furb.seca.model.Periodo;
import br.furb.seca.model.Professor;

public class Controller {

	DatabaseHelper dbHelper;

	public Controller(Context c) {
		dbHelper = new DatabaseHelper(c);
	}

	public List<DiaSemana> buscarHorarios() {
		List<DiaSemana> dias = new ArrayList<DiaSemana>();
		int index;
		SQLiteDatabase db = dbHelper.getReadableDatabase();

		StringBuilder builder = new StringBuilder();
		builder.append(" SELECT PROFESSOR.dsNome, DISCIPLINA.dsNome, ");
		builder.append(" HORARIO.nrHorarioInicio, HORARIO.nrHorarioFim, HORARIO.nrDiaSemana ");
		builder.append(" FROM HORARIO ");
		builder.append(" INNER JOIN DISCIPLINA ON DISCIPLINA.nrCodigo = HORARIO.fk_disciplina ");
		builder.append(" INNER JOIN PROFESSOR ON PROFESSOR.nrCodigo = DISCIPLINA.fk_professor ");
		builder.append(" ORDER BY HORARIO.nrDiaSemana");

		Cursor cursor = db.rawQuery(builder.toString(), null);
		cursor.moveToFirst();
		while (cursor.isAfterLast() == false) {
			Professor prof = new Professor(cursor.getString(0));
			Disciplina dis = new Disciplina(cursor.getString(1), prof);
			Horario hor = new Horario(dis, cursor.getInt(2), cursor.getInt(3));

			DiaSemana dia = new DiaSemana(cursor.getInt(4));
			index = dias.indexOf(dia);
			if (index != -1) {
				dia = dias.get(index);
			} else {
				dias.add(dia);
			}
			dia.addHorario(hor);

			cursor.moveToNext();
		}
		return dias;
	}

	public void Sincronizar() {
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
		InsertHorario(db, 2, 12, 13, 2);
		InsertHorario(db, 2, 14, 15, 1);
		InsertHorario(db, 3, 12, 13, 3);
		InsertHorario(db, 3, 14, 15, 2);
		InsertHorario(db, 4, 12, 13, 1);
		InsertHorario(db, 4, 14, 15, 3);
		InsertHorario(db, 5, 12, 15, 4);
		InsertHorario(db, 5, 12, 15, 5);
	}

	private void InsertHorario(SQLiteDatabase db, int dia, int hrInicio,
			int hrFim, int disciplina) {
		ContentValues values = new ContentValues();
		values.put("nrHorarioInicio", hrInicio);
		values.put("nrHorarioFim", hrFim);
		values.put("fk_disciplina", disciplina);
		values.put("nrDiaSemana", dia);
		db.insert("HORARIO", null, values);
	}
}
