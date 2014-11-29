package br.furb.seca.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import br.furb.seca.model.Base;
import br.furb.seca.model.Compromisso;
import com.google.gson.Gson;

/**
 * @author gabri_000
 */
@WebService(serviceName = "seca")
public class Seca {

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "Consultar")
    public String Consultar(@WebParam(name = "Usuario") String Usuario, @WebParam(name = "Senha") String Senha) {
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
    @WebMethod(operationName = "InserirCompromisso")
    public String InserirCompromisso(@WebParam(name = "compromisso") String compromisso,
	    @WebParam(name = "codigoAluno") int codigoAluno) {
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
