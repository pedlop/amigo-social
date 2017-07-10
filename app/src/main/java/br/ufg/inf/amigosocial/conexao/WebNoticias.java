package br.ufg.inf.amigosocial.conexao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import br.ufg.inf.amigosocial.dominio.Noticia;
import br.ufg.inf.amigosocial.dominio.Noticias;
import br.ufg.inf.amigosocial.util.AppConstantes;
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
            EventBus.getDefault().post(new Noticias(
                Conexao.<Noticia>parseRespostaList(resposta, Noticia[].class)
            ));
            EventBus.getDefault().post(AppConstantes.NOTICIAS_CARREGAMENTO_COMPLETO);

        } catch (IOException | NullPointerException | IllegalStateException e) {
            e.printStackTrace();
        }
    }
}
