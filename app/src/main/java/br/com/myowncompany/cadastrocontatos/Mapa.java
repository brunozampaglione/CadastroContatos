package br.com.myowncompany.cadastrocontatos;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

/**
 * Created by android5193 on 17/04/15.
 */
public class Mapa  extends SupportMapFragment{
    @Override
    public void onResume() {
        super.onResume();
        Localizador loc = new Localizador(getActivity(), this);

        ContatoDao dao = new ContatoDao(getActivity());
        List<Contato> contatos = dao.buscarContatos();

        for (Contato contato : contatos){
            MarkerOptions mo = new MarkerOptions();
            mo.title(contato.getNome());
            mo.snippet(contato.getEndereco());
            mo.position(loc.getLatLng(contato.getEndereco()));
            getMap().addMarker(mo);
        }
    }

    public void centralizaMapa(LatLng local) {
        GoogleMap map = getMap();
        map.setMapType(1);
        CameraUpdate updateCam = CameraUpdateFactory.newLatLngZoom(local, 15);

        map.moveCamera(updateCam);
    }
}
