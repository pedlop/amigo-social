package br.ufg.inf.amigosocial.conexao;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.ufg.inf.amigosocial.dominio.Noticia;
import okhttp3.Response;

/**
 * Created by ronyn on 29/06/2017.
 */

public class WebNoticias extends Conexao {

    private String LOG_TAG = "NOTICIAS_SERVICE";

    public WebNoticias(String servico) {
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
            Noticia[] noticias = gson.fromJson(json, Noticia[].class);
            ArrayList<Noticia> listaNoticias = new ArrayList<>(noticias.length);
            for(Noticia noticia : noticias) {
                listaNoticias.add(noticia);
            }
            EventBus.getDefault().post(listaNoticias);
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }
}
