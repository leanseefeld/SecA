package br.furb.seca;

import java.util.ArrayList;
import java.util.List;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import br.furb.seca.controller.Controller;
import br.furb.seca.model.Disciplina;

public class DisciplinasFragment extends MyFragment implements OnItemClickListener {

    private ListView listDisciplinas;
    private Controller controller;
    private MyFragment fragmentNotas;

    public DisciplinasFragment() {
	super(R.layout.fragment_disciplinas);
    }

    public DisciplinasFragment(int sectionNumber) {
	super(R.layout.fragment_disciplinas, sectionNumber);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	View v = super.onCreateView(inflater, container, savedInstanceState);

	controller = new Controller(v.getContext());

	listDisciplinas = (ListView) v.findViewById(R.id.frDisciplinas_lista);
	listDisciplinas.setOnItemClickListener(this);

	atualizaDisciplinas(v.getContext());

//	fragmentNotas = null;

	return v;
    }

    public void atualizaDisciplinas(Context c) {
	List<String[]> disciplinas = new ArrayList<String[]>();

	for (Disciplina disc : controller.buscarDisciplinas()) {
	    disciplinas.add(new String[] { disc.getNome(), disc.getProfessor().getNome(),
		    controller.calcularMedia(disc.getProvas()), String.valueOf(disc.getCodigo()), });
	}

	DisciplinaListAdapter adapter = new DisciplinaListAdapter(c, R.layout.fragment_disciplina_item, disciplinas);

	listDisciplinas.setAdapter(adapter);
    }

    @Override
    void Atualizar() {
	if (fragmentNotas != null) {
	    fragmentNotas.Atualizar();
	}
	atualizaDisciplinas(this.getActivity());
    }

    private void exibirNotas(Disciplina disciplina) {
	FragmentManager fragmentManager = this.getFragmentManager();

	this.fragmentNotas = new NotasFragment(disciplina);

	fragmentManager.beginTransaction().setCustomAnimations(R.animator.slide_in_left, R.animator.pop_out)
		.replace(R.id.container, this.fragmentNotas).addToBackStack(null) //Não funciona....
		.commit();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	String[] disciplina = (String[]) this.listDisciplinas.getAdapter().getItem(position);
	int codigoDisciplina = Integer.valueOf(disciplina[3]);

	Disciplina disc = controller.buscarDisciplina(codigoDisciplina);
	exibirNotas(disc);
    }
}
