package br.com.myowncompany.cadastrocontatos;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

/**
 * Created by android5193 on 17/04/15.
 */
public class Localizador implements LocationListener {
    private GoogleApiClient gac;
    private Context ctx;
    private Mapa mapa;
    public Localizador(Context ctx, Mapa mapa){
        this.ctx = ctx;
        this.mapa = mapa;
    }

    public LatLng getLatLng( String address){
        Geocoder gc = new Geocoder(ctx);
        List<Address> locations = null;
        try {
            locations = gc.getFromLocationName(address, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new LatLng (locations.get(0).getLatitude(), locations.get(0).getLongitude());
    }
    public void atualizadorDeLocalizacao(){
        // metodo utilizado para pegar localizaca atual do device
        // Olhar o GoogleApiClient pois tem muitas funcionalidades legais
        Configurador configs = new Configurador(this);
        gac = new GoogleApiClient.Builder(ctx)
                .addApi(LocationServices.API) // adiciona o servico de localizacao
                .addConnectionCallbacks(configs) // passa as configuracoes do location services,
                                                 // funciona como conceito de listener,
                                                 // poderiamos usar uma classe anonimo
                .build(); // solicito a contrucao do client

        gac.connect(); // ativa o serviço
    }
    public void inicia(LocationRequest lr){
        LocationServices.FusedLocationApi.requestLocationUpdates(gac,lr, (com.google.android.gms.location.LocationListener) this);
    }

    @Override
    public void onLocationChanged(Location location) {
        mapa.centralizaMapa(new LatLng (location.getLatitude(), location.getLongitude()));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
