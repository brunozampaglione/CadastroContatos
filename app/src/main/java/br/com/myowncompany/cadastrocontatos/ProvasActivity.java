package br.com.myowncompany.cadastrocontatos;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by android5193 on 15/04/15.
 */
public class ProvasActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_provas);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if(istablet()){
            transaction.replace(R.id.frame_provas, new lista_provas_fragment());
            transaction.replace(R.id.frame_detalhes, new DetalhesProvaFragment());
        }
        else {
            transaction.replace(R.id.frame_provas, new lista_provas_fragment());
        }
        transaction.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if(istablet()){
            transaction.replace(R.id.frame_provas, new lista_provas_fragment());
            transaction.replace(R.id.frame_detalhes, new DetalhesProvaFragment());
        }
        else {
            transaction.replace(R.id.frame_provas, new lista_provas_fragment());
        }
        transaction.commit();
    }

    private boolean istablet(){
        return getResources().getBoolean(R.bool.isTablet);
    }
}
