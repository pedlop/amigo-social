package br.ufg.inf.amigosocial.conexao;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Responsável por realizar conexões a serviços externos utilizando protocolo HTTP(S)
 * @author Rony Nogueira
 * @version 1.0
 */

public abstract class Conexao {

    private static final String API_URL = "API_ENDPOINT_URL";
    protected static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private String servicoUrl;

    public Conexao(String servico){
        this.servicoUrl = API_URL + servico;
    }

    public void requisicao() {
        OkHttpClient cliente = new OkHttpClient();

        RequestBody parametros = RequestBody.create(JSON, getParametros());
        Request requisicao = new Request.Builder()
                .url(this.servicoUrl)
                .post(parametros)
                .build();

        cliente.newCall(requisicao).enqueue(new Callback() {
            @Override
            public void onFailure(Call chamada, IOException erro) {
                EventBus.getDefault().post(new Exception("Verifique sua conexÃ£o"));
            }

            @Override
            public void onResponse(Call chamada, Response resposta) throws IOException {
                parseResposta(resposta);
            }
        });
    }

    abstract String getParametros();
    abstract void parseResposta(Response resposta);

}
