package br.furb.seca.model;

import java.io.IOException;
import java.util.Calendar;
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
	// TODO: remover
	if (true) {
	    return createMockAluno(aluno);
	}

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

    private Aluno createMockAluno(Aluno aluno) {
	Aluno mock = new Aluno(aluno.getCodigo(), aluno.getUsuario(), aluno.getNomeCompleto(), aluno.getSenha());

	Compromisso compAllDay = new Compromisso();
	compAllDay.setCodigo(983);
	compAllDay.setTitulo("Comp Mock 01");
	compAllDay.setDescricao("Descri��o do compromisso mock");
	compAllDay.setDiaTodo(true);
	Calendar now = Calendar.getInstance();
	compAllDay.setDataInicio(now.getTime());
	compAllDay.setDataFim(now.getTime());
	compAllDay.addLembrete(10);
	compAllDay.addLembrete(30);

	Compromisso compPartDay = new Compromisso();
	compPartDay.setCodigo(332);
	compPartDay.setTitulo("Comp Mock 02");
	compPartDay.setDescricao("Descri��o do segundo compromisso mock");
	compPartDay.setDiaTodo(false);
	Calendar end = (Calendar) now.clone();
	end.set(Calendar.HOUR_OF_DAY, now.get(Calendar.HOUR_OF_DAY) + 1);
	compPartDay.setDataInicio(now.getTime());
	compPartDay.setDataFim(end.getTime());
	compPartDay.addLembrete(60);

	mock.addCompromisso(compAllDay);
	mock.addCompromisso(compPartDay);

	Disciplina disciplina = new Disciplina("Soneca", new Professor(234, "Soniferus Bonzins"));
	Horario horario1D1 = new Horario();
	horario1D1.setDiaSemana(DiaSemana.QUARTA);
	horario1D1.setPeriodo(Periodo._13);
	horario1D1.setSala("S-309/1");
	horario1D1.setDisciplina(disciplina);
	disciplina.addHorario(horario1D1);

	mock.addDisciplina(disciplina);

	return mock;
    }

    private String getURL() {
	// TODO Auto-generated method stub
	return "";
    }

}