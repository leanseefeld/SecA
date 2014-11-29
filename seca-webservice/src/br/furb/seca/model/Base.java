/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.furb.seca.model;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author gabri_000
 */
public class Base {
    
    Connection con;
    
    public Base() {
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.println("Driver carregado com sucesso!");  
            String connectionUrl = "jdbc:mysql://localhost/seca?user=root&password=root";  
            con = DriverManager.getConnection(connectionUrl, "root", "root");  
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
                   
    }
    
    public Aluno MontaCapa(String nome, String senha)
    {
       try
       {
            Statement stm = con.createStatement();  
            ResultSet r = stm.executeQuery("SELECT * FROM ALUNO WHERE nome_usuario='"+nome+"' AND senha='"+senha+"'");   
            Aluno a = null;
            r.first();
            if (r.getRow()!=0)
            {
                a = new Aluno(r.getInt("codigo_aluno"), r.getString("nome_usuario"), r.getString("nome_completo"));
            
                Statement stm1 = con.createStatement();  
                ResultSet r1 = stm1.executeQuery(""
                    + " SELECT                       "
                    + "     d.*                      "
                    + " FROM                         "
                    + "     matricula m              "
                    + "     INNER JOIN disciplina d ON (m.codigo_disciplina = d.codigo_disciplina )  "
                    + " WHERE                        "
                    + "     codigo_aluno= "+String.valueOf(a.getCodigo())+" ");               
                r1.first();
                if (r1.getRow()!=0)
                {                    
                    r1.first();    
                    do {            
                        Disciplina d = new Disciplina(r1.getString("nome"), r1.getString("professor"));        
                        d.setCodigo(r1.getInt("codigo_disciplina"));
                        d = montaDiscplina((int) a.getCodigo(),r1.getInt("codigo_disciplina"),d);                
                        a.addDisciplina(d);
                        
                    }while (r1.next());
                }
                
                
                ResultSet r2 = stm1.executeQuery(""
                    + " SELECT                       "
                    + "     *                        "
                    + " FROM                         "
                    + "     compromisso c            "
                    + "     LEFT JOIN disciplina d ON (c.codigo_disciplina = d.codigo_disciplina )  "                    
                    + " WHERE                        "
                    + "     c.codigo_aluno= "+String.valueOf(a.getCodigo())+" ");               
                r2.first();
                
                Statement stm2 = con.createStatement();  
                do {            
                    if (r2.getRow()!=0)
                    {                    
                        
                        Compromisso c = new  Compromisso();
                        c.setCodigo(r2.getInt("codigo_comp"));
                        c.setDataFim(r2.getDate("data_fim"));
                        c.setDataInicio(r2.getDate("data_ini"));
                        c.setTitulo(r2.getString("titulo"));
                        if (!r2.getString("nome").equals(""))
                        {
                            Disciplina d = new Disciplina(r2.getString("nome"), r2.getString("professor"));        
                            d = montaDiscplina((int) a.getCodigo(),r2.getInt("codigo_disciplina"),d);                
                            c.setDisciplina(d);
                        }
                        ResultSet r3 = stm2.executeQuery("SELECT * FROM LEMBRETE WHERE CODIGO_COMPROMISSO = "+r2.getString("codigo_comp"));                        
                        r3.first();
                        if (r3.getRow() != 0)
                        {
                            do {            
                                c.addLembrete(r3.getInt("minutos_antes"));                            
                            }while (r3.next());
                        }
                        a.addCompromisso(c);
                    }
                }while (r2.next());
                
                
                
                
            }
            
            return a;
       }
       catch (Exception e)
       {
           e.printStackTrace();
       }
       return null;
    }
    
    public Disciplina montaDiscplina(int codAluno, int codDisciplina, Disciplina d) throws SQLException
    {
        Statement stm2 = con.createStatement();  
        ResultSet r2 = stm2.executeQuery(""
        + " SELECT                       "
        + "     *                        "
        + " FROM                         "
        + "     prova p                  "            
        + " WHERE                        "
        + "     codigo_aluno = "+codAluno+" AND " 
        + "     codigo_disciplina= "+String.valueOf(codDisciplina)+"");               
        r2.first();
        if (r2.getRow()!=0)
        {
            do
            {          
              Prova p = new Prova(r2.getFloat("nota"), r2.getString("nome"));                
              d.addProva(p);
            }while(r2.next());
        }
        
        ResultSet r3 = stm2.executeQuery(""
        + " SELECT                       "
        + "     *                        "
        + " FROM                         "
        + "     horario h                "            
        + " WHERE                        "
        + "     codigo_disciplina= "+String.valueOf(codDisciplina)+"");               
        r3.first();
        if (r3.getRow()!=0)
        {
            do
            {
               Horario h = new Horario();                          
               h.setDiaSemana(DiaSemana.fromCodigo(r3.getInt("dia_semana")));
               h.setPeriodo(r3.getInt("periodo"));
               h.setSala(r3.getString("sala"));
               d.addHorario(h);
            }while(r3.next());
        }                  
        
        return d;
    }
    
    
    
    
    
    
    public Compromisso salvaCompromisso(Compromisso c, int codigoALuno )
    {
        try
        {
            Statement stm1 = con.createStatement();  
            
            String s = "INSERT INTO COMPROMISSO (codigo_aluno, data_ini, data_fim, titulo, descricao, codigo_disciplina) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(s);
            ps.setInt(1, codigoALuno);
            ps.setDate(2, new Date(c.getDataInicio().getTime()));
            ps.setDate(3, new Date(c.getDataFim().getTime()));
            ps.setString(4, c.getTitulo());
            ps.setString(5, c.getDescricao());
            if (c.getDisciplina() != null)
            {
                ps.setInt(6, c.getDisciplina().getCodigo());
            } else {
		ps.setInt(6, 0);
	    }
	    ps.execute();

	    String sSelect = "SELECT MAX(CODIGO_COMP) as cod FROM COMPROMISSO ";
	    ResultSet r1 = stm1.executeQuery(s);
	    c.setCodigo(r1.getInt("cod"));
	    return c;

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }

}
