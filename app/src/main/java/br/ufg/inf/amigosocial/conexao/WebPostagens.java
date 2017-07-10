package br.ufg.inf.amigosocial.conexao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.ufg.inf.amigosocial.dominio.Postagem;
import br.ufg.inf.amigosocial.dominio.Postagens;
import br.ufg.inf.amigosocial.util.AppConstantes;
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
            EventBus.getDefault().post(new Postagens(
                    Conexao.<Postagem>parseRespostaList(resposta, Postagem[].class)
            ));
            EventBus.getDefault().post(AppConstantes.POSTAGENS_CARREGAMENTO_COMPLETO);

        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }
}
