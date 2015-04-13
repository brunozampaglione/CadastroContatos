package br.com.myowncompany.cadastrocontatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android5193 on 08/04/15.
 */
public class ContatoDao extends SQLiteOpenHelper{
    private static final int Versao = 6;
    private static final String Database = "CadastroDeContatos";
    private static final String Tabela = "Contato";

    public ContatoDao(Context contexto){
        super(contexto,Database,null,Versao);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + Tabela +" (" +
                                                    " id INTEGER PRIMARY KEY," +
                                                    " nome TEXT NOT NULL," +
                                                    " endereco TEXT," +
                                                    " telefone TEXT," +
                                                    " site TEXT," +
                                                    " nota REAL," +
                                                    " foto TEXT" +
                                            ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //String sql = "DROP TABLE IF EXISTS " + Tabela;
        //db.execSQL(sql);
        //onCreate(db);
    }

    public Long inserirContato(Contato contato){
        ContentValues cv = new ContentValues();
        cv.put("nome", contato.getNome());
        cv.put("endereco", contato.getEndereco());
        cv.put("telefone", contato.getTelefone());
        cv.put("site", contato.getSite());
        cv.put("nota", contato.getNota());
        cv.put("foto", contato.getFoto());

        Long rowId = getWritableDatabase().insert(Tabela, null, cv);

        return  rowId;
    }

    public int atualizarContato(Contato contato){
        ContentValues cv = new ContentValues();
        cv.put("nome", contato.getNome());
        cv.put("endereco", contato.getEndereco());
        cv.put("telefone", contato.getTelefone());
        cv.put("site", contato.getSite());
        cv.put("nota", contato.getNota());
        cv.put("foto", contato.getFoto());

        String[] args = {contato.getId().toString()};
        int rowId = getWritableDatabase().update(Tabela, cv, "id=?", args );

        return  rowId;
    }

    public List<Contato> buscarContatos(){
        String sql = "SELECT * FROM " + Tabela + ";";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        List<Contato> contatos = new ArrayList<Contato>();
        while (cursor.moveToNext()){
            Contato contato = new Contato();
            contato.setId(Long.valueOf(cursor.getString(cursor.getColumnIndex("id"))));
            contato.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            contato.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
            contato.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
            contato.setSite(cursor.getString(cursor.getColumnIndex("site")));
            contato.setNota(Double.valueOf(cursor.getString(cursor.getColumnIndex("nota"))));
            contato.setFoto(cursor.getString(cursor.getColumnIndex("foto")));
            contatos.add(contato);
        }
        return contatos;
    }

    public void deletaContato(Contato contato) {
        String[] args = {String.valueOf(contato.getId())};
        getWritableDatabase().delete(Tabela, "id = ?", args);
    }
    public Contato buscaContatoPorId(Long id){
        String[] args = {id.toString()};
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + Tabela + " WHERE id =?", args);

        if(cursor.moveToFirst()){
            Contato contato = new Contato();
            contato.setId(Long.valueOf(cursor.getString(cursor.getColumnIndex("id"))));
            contato.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            contato.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
            contato.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
            contato.setSite(cursor.getString(cursor.getColumnIndex("site")));
            contato.setNota(Double.valueOf(cursor.getString(cursor.getColumnIndex("nota"))));
            contato.setFoto(cursor.getString(cursor.getColumnIndex("foto")));
            return contato;
        }
        cursor.close();
        return null;
    }
}
