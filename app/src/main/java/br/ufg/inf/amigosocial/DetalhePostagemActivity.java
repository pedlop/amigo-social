package br.ufg.inf.amigosocial;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import br.ufg.inf.amigosocial.dominio.Postagem;

public class DetalhePostagemActivity extends AppCompatActivity {

    private Postagem postagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_postagem);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setNavigationIcon(R.mipmap.ic_voltar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();

        if (intent != null) {
            this.postagem = (Postagem) intent.getSerializableExtra("POSTAGEM");
            Toast.makeText(this, postagem.getTitulo() + "", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.erro_carregar_detalhe_postagem), Toast.LENGTH_LONG).show();
            finish();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr=" + postagem.getInstitutoLatitude() + "," + postagem.getInstitutoLongitude()));
                        //Uri.parse("http://maps.google.com/maps?daddr=40.7484405,-73.9856644"));
                        //Uri.parse("http://maps.google.com/maps?saddr=20.344,34.34&daddr=20.5666,45.345"));
                startActivity(intent);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
    }

}
