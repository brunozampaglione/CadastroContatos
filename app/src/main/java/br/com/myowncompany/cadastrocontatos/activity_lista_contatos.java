package br.com.myowncompany.cadastrocontatos;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.List;

public class activity_lista_contatos extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_lista_contatos);
        Log.i("Ciclo de vida (Lista)", "OnCreate");

        ListView lvContatos = (ListView) this.findViewById(R.id.lvContatos);
        registerForContextMenu(lvContatos);

        lvContatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(activity_lista_contatos.this, "(short click) Clicou na posição: " + position, Toast.LENGTH_SHORT).show();
                Contato contato = (Contato) parent.getItemAtPosition(position);

                Intent intent = new Intent(activity_lista_contatos.this, activity_cadastro.class);
                intent.putExtra("ContatoSelecionado", contato);
                startActivity(intent);
            }
        });

        Button btCadastrar = (Button) this.findViewById(R.id.Cadastrar);
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_lista_contatos.this, activity_cadastro.class);
                startActivity(intent);
            }
        });

        btCadastrar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ListView lvContatos = (ListView) activity_lista_contatos.this.findViewById(R.id.lvContatos);
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    Log.i("v-X", String.valueOf(v.getX()));
                    Log.i("v-Y", String.valueOf(v.getY()));

                    if(v.getX()<=lvContatos.getHeight() && v.getY()<=lvContatos.getWidth()) {
                        v.setX(event.getRawX() - 36);
                        v.setY(event.getRawY() - 130);
                    }

                    Log.i("e-X", String.valueOf(event.getRawX()));
                    Log.i("e-Y", String.valueOf(event.getRawY()));
                }
                return false;
            }
        });

        registerForContextMenu(lvContatos);

        registerReceiver(bateria, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    private BroadcastReceiver bateria = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int valor = intent.getIntExtra("level", 0);
            Toast.makeText(context, valor + "%", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_lista_contatos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.menu_enviar_notas:
                new SendInfo(this).execute();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected  void onStart(){
        super.onStart();
        Log.i("Ciclo de vida (Lista)", "onStart");
    }

    @Override
    protected  void onResume(){
        super.onResume();
        Log.i("Ciclo de vida (Lista)", "onResume");
        carregaLista();
    }

    @Override
    protected  void onPause(){
        super.onPause();
        Log.i("Ciclo de vida (Lista)", "onPause");
    }

    @Override
    protected  void onStop(){
        super.onStop();
        Log.i("Ciclo de vida (Lista)", "onStop");
    }

    @Override
    protected  void onDestroy(){
        super.onDestroy();
        Log.i("Ciclo de vida (Lista)", "onDestroy");
        unregisterReceiver(bateria);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo info){
        Contato contato = new Contato();
        AdapterView.AdapterContextMenuInfo contextMenuInfo = (AdapterView.AdapterContextMenuInfo) info;
        ListView lvContatos = (ListView) activity_lista_contatos.this.findViewById(R.id.lvContatos);
        Adapter adapterContatos = lvContatos.getAdapter();
        contato = (Contato) adapterContatos.getItem(contextMenuInfo.position);
        final Contato finalContato = contato;

        MenuItem Ligar = menu.add("Ligar");
        Intent intentLigar = new Intent(Intent.ACTION_CALL);
        intentLigar.setData(Uri.parse("tel:" + contato.getTelefone()));
        Ligar.setIntent(intentLigar);

        MenuItem EnviarSms = menu.add("Enviar SMS");
        Intent intentSms = new Intent(Intent.ACTION_VIEW);
        intentSms.setData(Uri.parse("sms:" + contato.getTelefone()));
        intentSms.putExtra("sms_body", "Mensagem para o aluno");
        EnviarSms.setIntent(intentSms);

        MenuItem AcharMapa = menu.add("Achar no Mapa");
        Intent intentMapa = new Intent(Intent.ACTION_VIEW);
        intentMapa.setData(Uri.parse("geo:0,0?z=14&q=" + contato.getEndereco()));
        AcharMapa.setIntent(intentMapa);

        MenuItem NavegarSite = menu.add("Navegar para Site");
        Intent intentSite = new Intent(Intent.ACTION_VIEW);
        intentSite.setData(Uri.parse("http:" + contato.getSite()));
        NavegarSite.setIntent(intentSite);

        MenuItem deletarContato = menu.add("Deletar");
        deletarContato.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                new AlertDialog.Builder(activity_lista_contatos.this).setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Deletar").setMessage("Deseja mesmo deletar?")
                        .setPositiveButton("Quero", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ContatoDao contatoDao = new ContatoDao(activity_lista_contatos.this);
                                contatoDao.deletaContato(finalContato);
                                carregaLista();

                            }
                        }).setNegativeButton("Não", null).show();
                return true;
            }
        });
    }

    public void carregaLista(){
        ContatoDao ContatoDao = new ContatoDao(this);
        List<Contato> contatos = ContatoDao.buscarContatos();
        ContatoDao.close();

        ListView lvContatos = (ListView) this.findViewById(R.id.lvContatos);
        ContatoAdapter adapterContatos = new ContatoAdapter(contatos, this);
        lvContatos.setAdapter(adapterContatos);
    }
}
