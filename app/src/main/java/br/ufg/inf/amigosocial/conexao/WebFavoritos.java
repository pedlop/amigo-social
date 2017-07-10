package br.ufg.inf.amigosocial.conexao;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.List;

import br.ufg.inf.amigosocial.dominio.Favorito;
import br.ufg.inf.amigosocial.dominio.Favoritos;

import br.ufg.inf.amigosocial.util.AppConstantes;
import okhttp3.Response;

/**
 * @author gabriel
 * @see Conexao
 * @version 1.0.0
 *
 */
public class WebFavoritos extends Conexao {

    private static final String LOG_TAG = "FAVORITOS_SERVICE";

    public WebFavoritos() {
        super("favoritos");
    }

    @Override
    String getParametros() {
        return "";
    }

    @Override
    void parseResposta(Response resposta) {
        try {
            EventBus.getDefault().post(new Favoritos(
                 Conexao.<Favorito>parseRespostaList(resposta, Favorito[].class)
            ));
            EventBus.getDefault().post(AppConstantes.FAVORITOS_CARREGAMENTO_COMPLETO);
        } catch (IOException | NullPointerException | IllegalStateException e) {
            Log.d(LOG_TAG, e.getMessage() + "\n");
        }
    }
}
