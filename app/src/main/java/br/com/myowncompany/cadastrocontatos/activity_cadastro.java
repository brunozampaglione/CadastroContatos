package br.com.myowncompany.cadastrocontatos;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class activity_cadastro extends ActionBarActivity {
    private CadastroHelper helper;
    private Contato contatoSelecionado;
    private String localArquivo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_cadastro);
        Log.i("Ciclo de vida (Cadastro)", "OnCreate");

        this.helper = new CadastroHelper(this);

        Intent intent = getIntent();
        this.contatoSelecionado = (Contato) intent.getSerializableExtra("ContatoSelecionado");

        if (this.contatoSelecionado != null) {
            helper.setaContato(this.contatoSelecionado);
        }

        Button btTiraFoto = (Button) this.findViewById(R.id.Foto);
        btTiraFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
                File arquivo = new File(path);
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                camera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(arquivo));
                localArquivo = path;
                startActivityForResult(camera, 111);
            }
        });

        Button btVoltar = (Button) this.findViewById(R.id.Voltar);
        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button btLimpar = (Button) this.findViewById(R.id.Limpar);
        btLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               helper.limpaContato();
            }
        });

        Button btCadastrar = (Button) this.findViewById(R.id.Cadastrar);
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contato contato = helper.pegaContato();
                if(helper.temNome()){
                    ContatoDao ContatoDao = new ContatoDao(activity_cadastro.this);
                    if(contatoSelecionado==null) {
                        Long rowId = ContatoDao.inserirContato(contato);
                        helper.mostraMsg(activity_cadastro.this, "Contato adicionado na linha: " + rowId);
                        finish();
                    }
                    else{
                        contato.setId(contatoSelecionado.getId());
                        int rowId = ContatoDao.atualizarContato(contato);

                        helper.mostraMsg(activity_cadastro.this, "Contato alterado na linha: " + rowId);
                        finish();
                    }
                }
                else{
                    helper.mostraMsg(activity_cadastro.this, "Contato precisa ter nome");
                    helper.setError("name must be filled");
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int request, int result, Intent intent){
        if(request==111){
            if(result== activity_cadastro.RESULT_OK) {
                Toast.makeText(this, "Result OK", Toast.LENGTH_SHORT).show();
                ImageView foto = (ImageView) findViewById(R.id.ViewFoto);
                foto.setImageURI(Uri.parse(localArquivo));
                foto.setTag(localArquivo);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_cadastro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.Salvar) {
           Contato contato = helper.pegaContato();
            if(helper.temNome()){
                ContatoDao ContatoDao = new ContatoDao(this);
                if(contatoSelecionado==null) {
                    Long rowId = ContatoDao.inserirContato(contato);
                    helper.mostraMsg(activity_cadastro.this, "Contato adicionado na linha: " + rowId);
                    finish();
                }
                else{
                    contato.setId(contatoSelecionado.getId());
                    int rowId = ContatoDao.atualizarContato(contato);
                    helper.mostraMsg(activity_cadastro.this, "Contato alterado na linha: " + rowId);
                    finish();
                }
            }
            else{
                helper.mostraMsg(this, "Contato precisa ter nome");
                helper.setError("name must be filled");
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected  void onStart(){
        super.onStart();
        Log.i("Ciclo de vida (Cadastro)", "onStart");
    }

    @Override
    protected  void onResume(){
        super.onResume();
        Log.i("Ciclo de vida (Cadastro)", "onResume");
    }

    @Override
    protected  void onPause(){
        super.onPause();
        Log.i("Ciclo de vida (Cadastro)", "onPause");
    }

    @Override
    protected  void onStop(){
        super.onStop();
        Log.i("Ciclo de vida (Cadastro)", "onStop");
    }

    @Override
    protected  void onDestroy(){
        super.onDestroy();
        Log.i("Ciclo de vida (Cadastro)", "onDestroy");
    }
}
