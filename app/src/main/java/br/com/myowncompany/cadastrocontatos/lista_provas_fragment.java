package br.com.myowncompany.cadastrocontatos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

/**
 * Created by android5193 on 15/04/15.
 */
public class lista_provas_fragment extends android.support.v4.app.Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_lista_provas, container, false);

        Prova prova1 = new Prova();
        prova1.setData("10/04/2015");
        prova1.setMateria("Portugues");
        prova1.setTopicos(Arrays.asList("Complemento Nominal", "Oraçoes subordinadas", "Analise sintatica"));

        Prova prova2 = new Prova();
        prova2.setData("15/04/2015");
        prova2.setMateria("Matematica");
        prova2.setTopicos(Arrays.asList("Algebra Linear", "Calculo", "Estatisticas"));

        List<Prova> provas = Arrays.asList(prova1, prova2);

        ListView listView = (ListView) layout.findViewById(R.id.listaProvas_lvProvas);
        final ArrayAdapter<Prova> adapter = new ArrayAdapter<Prova>(getActivity(), android.R.layout.simple_list_item_1, provas);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Prova selecionada = (Prova) parent.getItemAtPosition(position);
                ProvasActivity act = (ProvasActivity) getActivity();
                act.selecionaProva(selecionada);
                Toast.makeText(getActivity(), "Prova selecioanda: " + selecionada.getMateria(), Toast.LENGTH_SHORT).show();
            }
        });


        return layout;
    }
}
