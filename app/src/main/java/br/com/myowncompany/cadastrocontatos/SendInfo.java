package br.com.myowncompany.cadastrocontatos;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;

/**
 * Created by android5193 on 14/04/15.
 */
public class SendInfo extends AsyncTask<Object, Object, String> {
    private activity_lista_contatos act;
    private ProgressDialog progress;
    public SendInfo(activity_lista_contatos act){
        this.act = act;
    }
    @Override
    protected String doInBackground(Object[] params) {
        // este metodo esta na thread que criamos
        // o parametro de retorno deste metodo é do mesmo tipo de retorno do parametro passado na classe

        ContatoDao dao = new ContatoDao(this.act);
        List<Contato> contatos = dao.buscarContatos();
        dao.close();

        String js = new ContatoConverter().toJSON(contatos);
        WebClient client = new WebClient();
        String response = client.post(js);

        return response;
    }

    protected void onPreExecute(){
        // Este metodo já esta na thread principal
        progress = ProgressDialog.show(this.act, "Aguarde...", "Enviado dados!!", true);
    }

    protected void onPostExecute(String result){
        // este result automaticamente ja é o return do doInBackground
        // Este metodo já esta na thread principal
        // por isso podemos fazer o toas
        progress.dismiss();
        Toast.makeText(this.act, result, Toast.LENGTH_LONG).show();

    }
}
