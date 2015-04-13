package br.com.myowncompany.cadastrocontatos;

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
    public ContatoAdapter(List<Contato> contatos){
        this.contatos = contatos;
    }
    @Override
    public int getCount() {
        return contatos.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View layout = activity.getLayoutInflater().inflate(R.layout.item, parent, false);
        ImageView foto = (ImageView) layout.findViewById(R.id.ivFoto);
        TextView nome = (TextView) layout.findViewById(R.id.lblNome);

        Contato contato = contatos.get(position);
        nome.setText(contato.getNome());

        return null;
    }
}
