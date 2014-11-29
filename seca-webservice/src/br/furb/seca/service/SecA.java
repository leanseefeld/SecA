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
	Gson g = new Gson();
	try (Base b = new Base()) {
	    return g.toJson(b.montaAluno(Usuario, Senha));
	} catch (Exception e) {
	    e.printStackTrace();
	    return g.toJson(e.getMessage());
	}
    }

    /**
     * Operação de Web service
     */
    public String inserirCompromisso(String compromisso, int codigoAluno) {
	Gson g = new Gson();
	Compromisso c;
	try {
	    c = g.fromJson(compromisso, Compromisso.class);
	} catch (Exception e) {
	    return g.toJson("estrutura inválida para o compromisso");
	}
	try (Base b = new Base()) {
	    return g.toJson(b.salvaCompromisso(c, codigoAluno));
	} catch (Exception e) {
	    return g.toJson(e.getMessage());
	}
    }

}
