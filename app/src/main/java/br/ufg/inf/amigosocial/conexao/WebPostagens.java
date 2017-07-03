package br.ufg.inf.amigosocial.conexao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;

import br.ufg.inf.amigosocial.dominio.Postagem;
import okhttp3.Response;

/**
 * @author Pedro Victor - github.com/pedlop
 * @version 1.0
 * Created on 03/07/2017.
 */

public class WebPostagens extends Conexao {

    private String LOG_TAG = "POSTAGENS_SERVICE";

    public WebPostagens(String servico) {
        super(servico);
    }

    @Override
    String getParametros() {
        return "";
    }

    @Override
    void parseResposta(Response resposta) {
        try {
            String json = resposta.body().string();
            Gson gson = new GsonBuilder().create();
            Postagem[] postagens = gson.fromJson(json, Postagem[].class);
            ArrayList<Postagem> listaPostagens = new ArrayList<>(postagens.length);
            for (Postagem postagem : postagens) {
                listaPostagens.add(postagem);
            }
            EventBus.getDefault().post(listaPostagens);
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }
}
