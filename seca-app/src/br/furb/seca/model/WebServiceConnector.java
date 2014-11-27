package br.furb.seca.model;

import java.io.IOException;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;
import com.google.gson.Gson;

public class WebServiceConnector {

    private static String NAMESPACE = "";
    private static String METHOD_NAME = "";
    private static String SOAP_ACTION = "";

    public Aluno carregarAluno(Aluno aluno) throws IOException, XmlPullParserException {
	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
	request.addProperty("Usuario", aluno.getUsuario());
	request.addProperty("Senha", new String(aluno.getSenha()));

	SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	envelope.setOutputSoapObject(request);

	HttpTransportSE androidHttpTransport = new HttpTransportSE(getURL());
	androidHttpTransport.call(SOAP_ACTION, envelope);

	String responseJson = envelope.getResponse().toString();

	Gson gson = new Gson();
	return gson.fromJson(responseJson, Aluno.class);

    }

    private String getURL() {
	// TODO Auto-generated method stub
	return "";
    }

}
