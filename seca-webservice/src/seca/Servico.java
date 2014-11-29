/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package seca;

import com.google.gson.Gson;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/** 
 *
 * @author gabri_000
 */
@WebService(serviceName = "seca")
public class Servico {

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "Consultar")
    public String Consultar(@WebParam(name = "Usuario") String Usuario, @WebParam(name = "Senha") String Senha) {
        //TODO write your implementation code here:
        Gson g = new Gson();
        Base b = new Base();
        return  g.toJson(b.MontaCapa(Usuario, Senha));
    }

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "InserirCompromisso")
    public String InserirCompromisso(@WebParam(name = "compromisso") String compromisso, @WebParam(name = "codigoAluno") int codigoAluno) {
        //TODO write your implementation code here:
        Gson g = new Gson();        
        Compromisso c = g.fromJson(compromisso, Compromisso.class);
        Base b =  new Base();
        return g.toJson(b.salvaCompromisso(c, codigoAluno));
    }

}
