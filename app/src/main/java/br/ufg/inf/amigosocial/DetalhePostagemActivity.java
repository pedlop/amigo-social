package br.ufg.inf.amigosocial;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import br.ufg.inf.amigosocial.dominio.Postagem;

public class DetalhePostagemActivity extends BaseActivity {

    private Postagem postagem;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_postagem);
        toolbar = (Toolbar) findViewById(R.id.toolbar_detalhe);

        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.mipmap.ic_voltar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();

        if (intent != null) {
            this.postagem = (Postagem) intent.getSerializableExtra("POSTAGEM");
            this.setDadosPostagem(postagem);
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.erro_carregar_detalhe_postagem), Toast.LENGTH_LONG).show();
            finish();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (postagem.getInstitutoLatitude() != 0.0 && postagem.getInstitutoLongitude() != 0.0) {
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse("http://maps.google.com/maps?daddr=" + postagem.getInstitutoLatitude() + "," + postagem.getInstitutoLongitude()));
                    startActivity(intent);
                } else {
                    Snackbar.make(view, getString(R.string.texto_localizacao_nao_encontrada), Snackbar.LENGTH_LONG)
                            .setAction("Falha", null).show();
                }
            }
        });
    }

    /**
     * Responsável por colocar os dados da postagem nas respectivas views
     * @param postagem Postagem
     */
    private void setDadosPostagem(Postagem postagem) {
        ImageView imagem = getImageViewById(R.id.postagem_detalhe_imagem);

        TextView titulo = getTextViewById(R.id.postagem_detalhe_titulo);
        TextView descricao = getTextViewById(R.id.postagem_detalhe_descricao);
        TextView comoAjudar = getTextViewById(R.id.postagem_detalhe_como_ajudar);
        TextView instituicaoNome = getTextViewById(R.id.postagem_detalhe_instituicao_nome);
        TextView instituicaoCnpj = getTextViewById(R.id.postagem_detalhe_instituicao_cnpj);
        TextView instituicaoDescricao = getTextViewById(R.id.postagem_detalhe_instituicao_descricao);
        TextView instituicaoEndereco = getTextViewById(R.id.postagem_detalhe_instituicao_endereco);

        Picasso.with(this).load(postagem.getMidia().getArquivo()).into(imagem);

        titulo.setText(postagem.getTitulo());
        descricao.setText(postagem.getDescricao());
        comoAjudar.setText(postagem.getComoAjudar());
        instituicaoNome.setText(postagem.getInstituicao().getNome());
        instituicaoCnpj.setText(postagem.getInstituicao().getCnpj());
        instituicaoDescricao.setText(postagem.getInstituicao().getDescricao());
        instituicaoEndereco.setText(postagem.getInstituicao().getEndereco());
    }

}