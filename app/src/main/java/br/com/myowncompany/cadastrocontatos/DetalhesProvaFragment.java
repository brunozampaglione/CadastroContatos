package br.com.myowncompany.cadastrocontatos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by android5193 on 15/04/15.
 */
public class DetalhesProvaFragment extends android.support.v4.app.Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_detalhes, container, false);
        return layout;
    }
}
