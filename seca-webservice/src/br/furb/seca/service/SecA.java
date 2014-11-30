package br.furb.seca.service;

import br.furb.seca.model.Base;
import br.furb.seca.model.Compromisso;
import com.google.gson.Gson;

/**
 * @author gabri_000
 */
public class SecA {

    /**
     * Operação de Web service
     */
    public String consultar(String Usuario, String Senha) {
	System.out.printf("Recebido: Usuario=%s; Senha=%s\n", Usuario, Senha);
	Gson g = new Gson();
	String result;
	try (Base b = new Base()) {
	    result = g.toJson(b.montaAluno(Usuario, Senha));
	} catch (Exception e) {
	    e.printStackTrace();
	    result = g.toJson(e.getMessage());
	}
	System.out.println("Retornando: " + result);
	return result;
    }

    /**
     * Operação de Web service
     */
    public String inserirCompromisso(String compromisso, int codigoAluno) {
	System.out.printf("Recebido: compromisso=%s; codigoAluno=%d\n", compromisso, codigoAluno);
	Gson g = new Gson();
	Compromisso c;
	try {
	    c = g.fromJson(compromisso, Compromisso.class);
	} catch (Exception e) {
	    return g.toJson("estrutura inválida para o compromisso");
	}
	String result;
	try (Base b = new Base()) {
	    result = g.toJson(b.salvaCompromisso(c, codigoAluno));
	} catch (Exception e) {
	    result = g.toJson(e.getMessage());
	}
	return result;
    }

}
