package br.furb.seca.model;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author gabri_000
 */
public class Base implements Closeable {

    private static final String CONNECTION_URL = "jdbc:mysql://localhost/seca?user=root&password=123456";
    private Connection con;

    public Base() throws SQLException {
	try {
	    Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
	    throw new RuntimeException("Driver de conexão MySql não localizado", e);
	}
	con = DriverManager.getConnection(CONNECTION_URL);
    }

    public Aluno montaAluno(String usuario, String senha) {
	try {
	    PreparedStatement psAluno = con
		    .prepareStatement("SELECT * FROM aluno WHERE nome_usuario = ? AND senha = ?");
	    psAluno.setString(1, usuario);
	    psAluno.setString(2, senha);

	    ResultSet fetchedAluno = psAluno.executeQuery();

	    Aluno aluno = null;
	    if (fetchedAluno.first()) {
		aluno = new Aluno(fetchedAluno.getInt("codigo_aluno"), // 
			fetchedAluno.getString("nome_usuario"), //
			fetchedAluno.getString("nome_completo"), //
			senha.getBytes());

		PreparedStatement psMatricula = con
			.prepareStatement("SELECT d.* FROM matricula m INNER JOIN disciplina d ON (m.codigo_disciplina = d.codigo_disciplina) WHERE codigo_aluno = ?");
		psMatricula.setLong(1, aluno.getCodigo());

		ResultSet fetchedMatricula = psMatricula.executeQuery();
		fetchedMatricula.beforeFirst();
		while (fetchedMatricula.next()) {
		    Disciplina disc = new Disciplina(fetchedMatricula.getString("nome"),
			    fetchedMatricula.getString("professor"));
		    disc.setCodigo(fetchedMatricula.getInt("codigo_disciplina"));
		    disc = montaDiscplina((int) aluno.getCodigo(), fetchedMatricula.getInt("codigo_disciplina"), disc);
		    aluno.addDisciplina(disc);
		}

		PreparedStatement psCompromissos = con
			.prepareStatement("SELECT * FROM compromisso c LEFT JOIN disciplina d ON (c.codigo_disciplina = d.codigo_disciplina) WHERE c.codigo_aluno = ?");
		psCompromissos.setLong(1, aluno.getCodigo());

		PreparedStatement psLembrete = con
			.prepareStatement("SELECT * FROM LEMBRETE WHERE CODIGO_COMPROMISSO = ?");

		ResultSet fetchedCompromissos = psCompromissos.executeQuery();
		fetchedCompromissos.beforeFirst();
		while (fetchedCompromissos.next()) {
		    Compromisso comp = new Compromisso();
		    comp.setCodigo(fetchedCompromissos.getInt("codigo_comp"));
		    comp.setDataFim(fetchedCompromissos.getDate("data_fim"));
		    comp.setDataInicio(fetchedCompromissos.getDate("data_ini"));
		    comp.setTitulo(fetchedCompromissos.getString("titulo"));
		    if (fetchedCompromissos.getString("nome") != null) {
			Disciplina disc = new Disciplina(fetchedCompromissos.getString("nome"),
				fetchedCompromissos.getString("professor"));
			disc = montaDiscplina((int) aluno.getCodigo(), fetchedCompromissos.getInt("codigo_disciplina"),
				disc);
			comp.setDisciplina(disc);
		    }

		    // carrega os lembretes
		    psLembrete.setLong(1, comp.getCodigo());
		    ResultSet fetchedLembretes = psLembrete.executeQuery();
		    fetchedLembretes.beforeFirst();
		    while (fetchedLembretes.next()) {
			comp.addLembrete(fetchedLembretes.getInt("minutos_antes"));
		    }
		    aluno.addCompromisso(comp);
		}
	    }

	    return aluno;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }

    public Disciplina montaDiscplina(int codAluno, int codDisciplina, Disciplina disc) throws SQLException {
	PreparedStatement psProva = con
		.prepareStatement("SELECT nota, peso, nome FROM prova p WHERE codigo_aluno = ? AND codigo_disciplina = ?");
	psProva.setInt(1, codAluno);
	psProva.setInt(2, codDisciplina);

	ResultSet fetchedProvas = psProva.executeQuery();
	fetchedProvas.beforeFirst();
	while (fetchedProvas.next()) {
	    Prova prova = new Prova(fetchedProvas.getFloat("nota"), //
		    fetchedProvas.getFloat("peso"),//
		    fetchedProvas.getString("nome"));
	    disc.addProva(prova);
	}

	PreparedStatement psHorario = con.prepareStatement("SELECT * FROM horario h WHERE codigo_disciplina = ?");
	psHorario.setInt(1, codDisciplina);

	ResultSet fetchedHorarios = psHorario.executeQuery();
	fetchedHorarios.beforeFirst();
	while (fetchedHorarios.next()) {
	    Horario horario = new Horario();
	    horario.setDiaSemana(DiaSemana.fromCodigo(fetchedHorarios.getInt("dia_semana")));
	    horario.setPeriodo(fetchedHorarios.getInt("periodo"));
	    horario.setSala(fetchedHorarios.getString("sala"));
	    disc.addHorario(horario);
	}

	return disc;
    }

    public Compromisso salvaCompromisso(Compromisso comp, int codigoAluno) {
	try {
	    Statement stm1 = con.createStatement();

	    String s = "INSERT INTO COMPROMISSO (codigo_aluno, data_ini, data_fim, titulo, descricao, codigo_disciplina) VALUES (?, ?, ?, ?, ?, ?)";
	    PreparedStatement ps = con.prepareStatement(s);
	    ps.setInt(1, codigoAluno);
	    ps.setDate(2, new Date(comp.getDataInicio().getTime()));
	    ps.setDate(3, new Date(comp.getDataFim().getTime()));
	    ps.setString(4, comp.getTitulo());
	    ps.setString(5, comp.getDescricao());
	    ps.setInt(6, comp.getDisciplina() != null ? comp.getDisciplina().getCodigo() : 0);
	    ps.execute();

	    ResultSet fetchedCod = stm1.executeQuery("SELECT MAX(CODIGO_COMP) as cod FROM COMPROMISSO");
	    comp.setCodigo(fetchedCod.getInt("cod"));
	    return comp;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }

    @Override
    public void close() throws IOException {
	try {
	    con.close();
	} catch (SQLException e) {
	    throw new IOException(e);
	}
    }

}
