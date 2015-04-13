package br.com.myowncompany.cadastrocontatos;

import android.app.Activity;
import android.net.Uri;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

/**
 * Created by android5193 on 08/04/15.
 */
public class CadastroHelper {
    private Contato contato;
    private EditText contatoNome;
    private EditText contatoEndereco;
    private EditText contatoTelefone;
    private EditText contatoSite;
    private RatingBar contatoNota;
    private ImageView contatoFoto;
    private activity_cadastro Act;

    public CadastroHelper(activity_cadastro activity){
        this.contato = new Contato();

        this.contatoNome = (EditText) activity.findViewById(R.id.contatoNome);
        this.contatoEndereco = (EditText) activity.findViewById(R.id.contatoEndereco);
        this.contatoTelefone = (EditText) activity.findViewById(R.id.contatoTelefone);
        this.contatoSite = (EditText) activity.findViewById(R.id.contatoSite);
        this.contatoNota = (RatingBar) activity.findViewById(R.id.contatoNota);
        this.contatoFoto = (ImageView) activity.findViewById(R.id.ViewFoto);

        Act = activity;
    }

    public Contato pegaContato(){
        contato.setNome(contatoNome.getText().toString());
        contato.setEndereco(contatoEndereco.getText().toString());
        contato.setTelefone(contatoTelefone.getText().toString());
        contato.setSite(contatoSite.getText().toString());
        contato.setNota(Double.valueOf(contatoNota.getProgress()));
        contato.setFoto((String)contatoFoto.getTag());

        return contato;
    }

    public void setaContato(Contato contato){
        this.contatoNome.setText(contato.getNome());
        this.contatoEndereco.setText(contato.getEndereco());
        this.contatoTelefone.setText(contato.getTelefone());
        this.contatoSite.setText(contato.getSite());
        this.contatoNota.setProgress(contato.getNota().intValue());

        if(contato.getFoto()!=null) {
            this.contatoFoto.setImageURI(Uri.parse(contato.getFoto()));
            this.contatoFoto.setTag(contato.getFoto());
        }
    }

    public void limpaContato(){
        contatoNome.setText("");
        contatoEndereco.setText("");
        contatoTelefone.setText("");
        contatoSite.setText("");
        contatoNota.setProgress(0);
        contatoFoto.setImageURI(null);

    }

    public boolean temNome(){
        return !contato.getNome().isEmpty();
    }

    public void mostraMsg(activity_cadastro activity, String errMsg){
        Toast.makeText(Act, errMsg, Toast.LENGTH_SHORT).show();
    }

    public void setError(String errMsg){
        contatoNome.setError(errMsg);
    }
}
