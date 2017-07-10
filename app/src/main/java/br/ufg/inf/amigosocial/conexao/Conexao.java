package br.ufg.inf.amigosocial.conexao;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import br.ufg.inf.amigosocial.exception.SemConexaoException;
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
 * @author gabriel
 * @version 1.0
 */

public abstract class Conexao {

    private static final String API_URL = "https://private-38cccd-amigosocial.apiary-mock.com/";
    protected static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private String servicoUrl;

    public Conexao(String servico){
        this.servicoUrl = API_URL + servico;
    }

    public void postRequisicao() {
        OkHttpClient cliente = new OkHttpClient();
        RequestBody parametros = RequestBody.create(JSON, getParametros());
        Request requisicao = new Request.Builder()
                .url(this.servicoUrl)
                .post(parametros)
                .build();

        cliente.newCall(requisicao).enqueue(new Callback() {
            @Override
            public void onFailure(Call chamada, IOException erro) {
                EventBus.getDefault().post(new SemConexaoException("Verifique sua conexão"));
            }

            @Override
            public void onResponse(Call chamada, Response resposta) throws IOException {
                parseResposta(resposta);
            }
        });
    }
    public void getRequisicao() {
        OkHttpClient cliente = new OkHttpClient();
        Request requisicao = new Request.Builder()
                .url(this.servicoUrl)
                .get()
                .build();

        cliente.newCall(requisicao).enqueue(new Callback() {
            @Override
            public void onFailure(Call chamada, IOException erro) {
                EventBus.getDefault().post(new SemConexaoException("Verifique sua conexão"));
            }

            @Override
            public void onResponse(Call chamada, Response resposta) throws IOException {
                parseResposta(resposta);
            }
        });
    }
    abstract String getParametros();
    abstract void parseResposta(Response resposta);


    /**
     * @param resposta - Response para retirar o body e realizar o parse.
     */
    static <T> T parseRespostaObject(Response resposta, Class<T> clazz) throws IOException {
        String json = resposta.body().string();
        Gson gson = new GsonBuilder().create();

        return gson.fromJson(json, clazz);
    }

    /**
     * @param resposta - Response para retirar o body e realizar o parse.
     */
    static <T> List<T> parseRespostaList(Response resposta, Class<T[]> clazz) throws IOException {
        String json = resposta.body().string();
        Gson gson = new GsonBuilder().create();

        T[] array =  gson.fromJson(json, clazz);
        return Arrays.asList(array);
    }

}
