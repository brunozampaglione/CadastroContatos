package br.com.myowncompany.cadastrocontatos;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.List;

/**
 * Created by android5193 on 14/04/15.
 */
public class ContatoConverter {
    public String toJSON(List<Contato> contatos)  {
        JSONStringer js = new JSONStringer();
        try {
            js.object().key("list").array();

            js.object().key("aluno").array();

            for (Contato contato : contatos) {
                js.object();
                js.key("nome").value(contato.getNome());
                js.key("nota").value(contato.getNota());
                js.endObject();
            }

            js.endArray().endObject();
            js.endArray().endObject();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  js.toString();
    }
}
