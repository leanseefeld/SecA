package br.furb.seca;

import android.widget.Toast;

public class NotasFragment extends MyFragment {

    public NotasFragment() {
	super(R.layout.fragment_notas);
    }

    public NotasFragment(int sectionNumber) {
	super(R.layout.fragment_notas, sectionNumber);
    }

    @Override
    void Atualizar() {
	Toast.makeText(this.getActivity(), "Sincronizando notas", Toast.LENGTH_SHORT).show();
    }

}
