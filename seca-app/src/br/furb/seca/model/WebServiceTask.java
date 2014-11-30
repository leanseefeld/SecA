package br.furb.seca.model;

import java.net.ConnectException;
import android.os.AsyncTask;
import android.util.Log;
import br.furb.seca.controller.Controller;

public class WebServiceTask extends AsyncTask<Aluno, Void, Aluno> {

    private WebServiceConnector wsConnector;
    private Controller controller;
    private Exception exception;

    public WebServiceTask(WebServiceConnector wsConnector, Controller controller) {
	if (wsConnector == null || controller == null) {
	    throw new NullPointerException();
	}
	this.wsConnector = wsConnector;
	this.controller = controller;
    }

    @Override
    protected Aluno doInBackground(Aluno... alunos) {
	try {
	    return wsConnector.carregarAluno(alunos[0]);
	} catch (Exception ex) {
	    this.exception = ex;
	    Log.e("SecA-sync", "failure", ex);
	    return null;
	}
    }

    @Override
    protected void onPostExecute(Aluno aluno) {
	if (aluno != null) {
	    controller.syncSucceeded(aluno);
	} else if (exception instanceof ConnectException) {
	    controller.syncFailed("A conexão falhou. Você pode tentar novamente mais tarde!");
	} else {
	    controller.syncFailed(exception.toString());
	}

    }

}
