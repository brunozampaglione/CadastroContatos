package br.com.myowncompany.cadastrocontatos;

import android.os.Bundle;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;

/**
 * Created by android5193 on 17/04/15.
 */
public class Configurador implements GoogleApiClient.ConnectionCallbacks{
    private Localizador loc;
    public Configurador(Localizador loc){
        this.loc = loc;

    }

    @Override
    public void onConnected(Bundle bundle) {
        LocationRequest lc = LocationRequest.create(); // cria um classe com configuracoes
        lc.setInterval(10000); // Seta intervalo de atualizacoes, cuidado pois quanto menor esse tempo
                              // mas rapido a bateria é cosumida >> bestpratice sempre maior q 1 seg
        lc.setSmallestDisplacement(50); // seta a distancia minima para atualizaçao
                                        // neste caso ele so vai usar o tempo de cima se a distancia fr alcancada
        lc.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY); // Quanto mais preciso maior é o consumo de bateria
        loc.inicia(lc); // volta para a classe de localizacao passando as configuracoes
    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}
