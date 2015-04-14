package br.com.myowncompany.cadastrocontatos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by android5193 on 13/04/15.
 */
public class ContatoAdapter extends BaseAdapter {
    private activity_lista_contatos activity;
    private List<Contato> contatos;
    public ContatoAdapter(List<Contato> contatos, activity_lista_contatos act){
        this.contatos = contatos;
        this.activity = act;
    }
    @Override
    public int getCount() {
        return contatos.size();
    }

    @Override
    public Object getItem(int position) {
        return contatos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return contatos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View layout = activity.getLayoutInflater().inflate(R.layout.item, parent, false);
        ImageView foto = (ImageView) layout.findViewById(R.id.ivFoto);
        TextView nome = (TextView) layout.findViewById(R.id.lblNomeCont);

        Contato contato = contatos.get(position);
        nome.setText(contato.getNome());

        if(contato.getFoto()!=null) {
            foto.setImageURI(Uri.parse(contato.getFoto()));
        }
        else{
            Bitmap bm;
            bm = BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_no_image);
            bm = Bitmap.createScaledBitmap(bm, 50, 50, true);
            foto.setImageBitmap(bm);

        }


        if(position%2==0){
            layout.setBackgroundColor(activity.getResources().getColor(R.color.linha_par));
        }
        else{
            layout.setBackgroundColor(activity.getResources().getColor(R.color.linha_impar));
        }

        return layout;
    }
}
