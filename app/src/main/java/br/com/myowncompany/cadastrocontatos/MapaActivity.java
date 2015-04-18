package br.com.myowncompany.cadastrocontatos;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by android5193 on 17/04/15.
 */
public class MapaActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapa_frame);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaFrame();
    }

    private void carregaFrame(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.mapa_frame, new Mapa());
        transaction.commit();
    }
}
