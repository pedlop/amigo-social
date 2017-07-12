package br.ufg.inf.amigosocial;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import br.ufg.inf.amigosocial.conexao.Conexao;
import br.ufg.inf.amigosocial.dominio.Postagem;
import br.ufg.inf.amigosocial.util.AppConstantes;
import br.ufg.inf.amigosocial.util.PreferenciasApp;
import okhttp3.Response;

/**
 * @author Pedro Victor
 * @author Rony Nogueira
 * @version 1.0
 */
public class DetalhePostagemActivity extends BaseActivity {

    private Postagem postagem;
    private Toolbar toolbar;
    private Menu menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.favorito_option, menu);
        this.menu = menu;
        if (postagem.getFavorito() > 0) {
            this.menu.getItem(0).setIcon(R.mipmap.ic_favoritos);
        } else {
            this.menu.getItem(0).setIcon(R.mipmap.ic_favoritos_vazio);
        }
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_favoritos) {
            if (postagem.getFavorito() > 0) {
                removeFavorito();
            } else {
                adicionaFavorito();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Adiciona a postagem como favorito do usuário, caso não possua email cadastrado é solicitado o cadastro do email
     */
    private void adicionaFavorito() {
        String email = PreferenciasApp.getPreferencia(this, AppConstantes.FAVORITOS_EMAIL.getChave(),"");
        if (!email.equals("")) {
            String url = "favorito";
            Map<String, String> params = new HashMap<>();
            params.put("postagem_id", postagem.getId() + "");
            params.put("email", email);
            Gson gson = new GsonBuilder().create();
            String parametros = gson.toJson(params);
            Conexao.post(url, parametros , new Conexao.ParserResponse() {
                @Override
                public void parse(Response r) {
                    AppConstantes resposta = r.isSuccessful() ? AppConstantes.FAVORITO_ADICIONADO_SUCESSO : AppConstantes.FAVORITO_ADICIONADO_FALHA;
                    EventBus.getDefault().post(resposta);
                    EventBus.getDefault().post(AppConstantes.FAVORITOS_ATUALIZADOS);
                }
            });
        } else {

            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

            AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
            dialogo.setTitle(getString(R.string.texto_informe_email));
            dialogo.setView(input);
            dialogo.setPositiveButton(R.string.texto_salvar, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String emailInput = input.getText().toString().trim();
                    if (emailInput.length() > 0) {
                        PreferenciasApp.setPreferencia(DetalhePostagemActivity.this, AppConstantes.FAVORITOS_EMAIL.getChave(), emailInput );
                        adicionaFavorito();
                    } else {
                        Toast.makeText(DetalhePostagemActivity.this, getString(R.string.texto_informe_email_erro), Toast.LENGTH_SHORT).show();
                    }
                }
            });
            dialogo.setNegativeButton(R.string.texto_cancelar, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            AlertDialog alertDialog = dialogo.create();
            alertDialog.show();
        }
    }

    /**
     * Remove a postagem da lista de favoritos do usuário
     */
    private void removeFavorito() {
        String url = "favorito/" + postagem.getFavorito();
        Conexao.delete(url, new Conexao.ParserResponse() {
            @Override
            public void parse(Response r) {
                AppConstantes resposta = r.isSuccessful() ? AppConstantes.FAVORITO_REMOVIDO_SUCESSO : AppConstantes.FAVORITO_REMOVIDO_FALHA;
                EventBus.getDefault().post(resposta);
            }
        });
    }

    /**
     * Exibe um Toast com o resultado da ação do usuário
     * @see Toast
     * @param resposta AppConstantes
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(AppConstantes resposta) {
        int mensagem = 0;
        if (resposta.getChave().equals(AppConstantes.FAVORITO_ADICIONADO_SUCESSO.getChave())) {
            mensagem = R.string.texto_favorito_sucesso;
            this.menu.getItem(0).setIcon(R.mipmap.ic_favoritos);
            postagem.setFavorito(1);
        } else if(resposta.getChave().equals(AppConstantes.FAVORITO_ADICIONADO_FALHA.getChave())) {
            mensagem = R.string.texto_favorito_falha;
        } else if (resposta.getChave().equals(AppConstantes.FAVORITO_REMOVIDO_SUCESSO.getChave())) {
            this.menu.getItem(0).setIcon(R.mipmap.ic_favoritos_vazio);
            mensagem = R.string.texto_favorito_removido_sucesso;
            postagem.setFavorito(0);
        } else if (resposta.getChave().equals(AppConstantes.FAVORITO_REMOVIDO_FALHA.getChave())) {
            mensagem = R.string.texto_favorito_removido_falha;
        }
        if (mensagem != 0)
            Toast.makeText(this, getString(mensagem), Toast.LENGTH_SHORT).show();
    }

}