package br.com.myowncompany.cadastrocontatos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by android5193 on 15/04/15.
 */
public class DetalhesProvaFragment extends android.support.v4.app.Fragment {
    private TextView tvMateria;
    private TextView tvData;
    private ListView lvTopicos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_detalhes, container, false);

        tvMateria = (TextView) layout.findViewById(R.id.detalhes_tvMateria);
        tvData = (TextView) layout.findViewById(R.id.detalhes_tvData);
        lvTopicos = (ListView) layout.findViewById(R.id.detalhes_lvTopics);

        populaFragment(null);

        return layout;
    }
    public void populaFragment(Bundle bundle) {
        Prova prova;
        if (getArguments() != null){
            prova = (Prova) this.getArguments().getSerializable("prova");
            tvMateria.setText(prova.getMateria());
            tvData.setText(prova.getData());

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, prova.getTopicos());
            lvTopicos.setAdapter(adapter);
        }
        else if (bundle != null){
            prova = (Prova) bundle.getSerializable("prova");
            tvMateria.setText(prova.getMateria());
            tvData.setText(prova.getData());

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, prova.getTopicos());
            lvTopicos.setAdapter(adapter);
        }
    }
}
