package br.furb.seca.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MontaAluno {

    Connection con;

    public MontaAluno() {
	try {
	    Class.forName("com.mysql.jdbc.Driver").newInstance();
	    System.out.println("Driver carregado com sucesso!");
	    String connectionUrl = "jdbc:mysql://localhost/seca?user=root&password=root";
	    con = DriverManager.getConnection(connectionUrl, "root", "root");
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    public Aluno MontaCapa(String nome, String senha) {
	try {
	    Statement stm = con.createStatement();
	    ResultSet r = stm.executeQuery("SELECT * FROM ALUNO WHERE nome_usuario='" + nome + "' AND senha='" + senha
		    + "'");
	    Aluno a = null;
	    r.first();
	    if (r.getRow() != 0) {
		a = new Aluno(r.getInt("codigo_aluno"), r.getString("nome_usuario"), r.getString("nome_completo"));

		Statement stm1 = con.createStatement();
		ResultSet r1 = stm1.executeQuery("" + " SELECT                       "
			+ "     d.*                      " + " FROM                         "
			+ "     matricula m              "
			+ "     INNER JOIN disciplina d ON (m.codigo_disciplina = d.codigo_disciplina )  "
			+ " WHERE                        " + "     codigo_aluno= " + String.valueOf(a.getCodigo())
			+ " ");
		r1.first();
		if (r1.getRow() != 0) {
		    r1.first();
		    do {
			Disciplina d = new Disciplina(r1.getString("nome"), r1.getString("professor"));
			d = montaDiscplina((int) a.getCodigo(), r1.getInt("codigo_disciplina"), d);
			a.addDisciplina(d);

		    } while (r1.next());
		}

		ResultSet r2 = stm1.executeQuery("" + " SELECT                       "
			+ "     *                        " + " FROM                         "
			+ "     compromisso c            "
			+ "     LEFT JOIN disciplina d ON (c.codigo_disciplina = d.codigo_disciplina )  "
			+ " WHERE                        " + "     c.codigo_aluno= " + String.valueOf(a.getCodigo())
			+ " ");
		r2.first();
		do {
		    if (r2.getRow() != 0) {
			Compromisso c = new Compromisso();
			c.setCodigo(r2.getInt("codigo_comp"));
			c.setDataFim(r2.getDate("data_fim"));
			c.setDataInicio(r2.getDate("data_ini"));
			c.setTitulo(r2.getString("titulo"));
			if (!r2.getString("nome").equals("")) {
			    Disciplina d = new Disciplina(r2.getString("nome"), r2.getString("professor"));
			    d = montaDiscplina((int) a.getCodigo(), r2.getInt("codigo_disciplina"), d);
			    c.setDisciplina(d);
			}
			ResultSet r3 = stm1.executeQuery("SELECT * FROM LEMBRETE WHERE CODIGO_COMPROMISSO = "
				+ r2.getString("codigo_comp"));
			r3.first();
			if (r3.getRow() != 0) {
			    do {
				c.addLembrete(r3.getInt("minutos_antes"));
			    } while (r3.next());
			}
			a.addCompromisso(c);
		    }
		} while (r2.next());
		a.addCompromisso(null);

	    }

	    return a;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }

    public Disciplina montaDiscplina(int codAluno, int codDisciplina, Disciplina d) throws SQLException {
	Statement stm2 = con.createStatement();
	ResultSet r2 = stm2.executeQuery("" + " SELECT                       " + "     *                        "
		+ " FROM                         " + "     prova p                  "
		+ " WHERE                        " + "     codigo_aluno = " + codAluno + " AND "
		+ "     codigo_disciplina= " + String.valueOf(codDisciplina) + "");
	r2.first();
	if (r2.getRow() != 0) {
	    do {
		Prova p = new Prova(r2.getFloat("nota"), r2.getString("nome"));
		d.addProva(p);
	    } while (r2.next());
	}

	ResultSet r3 = stm2.executeQuery("" + " SELECT                       " + "     *                        "
		+ " FROM                         " + "     horario h                "
		+ " WHERE                        " + "     codigo_disciplina= " + String.valueOf(codDisciplina) + "");
	r3.first();
	if (r3.getRow() != 0) {
	    do {
		Horario h = new Horario();
		h.setDiaSemana(DiaSemana.valueOf(r3.getString("dia_semana")));
		h.setPeriodo(r3.getInt("periodo"));
		h.setSala(r3.getString("sala"));
		d.addHorario(h);
	    } while (r3.next());
	}

	return d;
    }

}
