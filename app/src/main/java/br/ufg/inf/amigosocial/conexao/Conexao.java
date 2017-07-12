package br.ufg.inf.amigosocial.conexao;

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
.* @version 1.0
*/
public abstract class Conexao {

    /**
     * URL base.
     */
    private static final String API_URL = "http://private-38cccd-amigosocial.apiary-mock.com/";

    /**
     * Media Type do conteúdo dos requests.
     */
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    /**
     * Realiza uma requisição do tipo POST
     * @param urlService URL do serviço
     * @param content Dados do body
     * @param parserResponse Responsável por interpretar a resposta
     */
    public static void post(String urlService, String content, ParserResponse parserResponse) {
        OkHttpClient cliente = new OkHttpClient();
        RequestBody parametros = RequestBody.create(JSON, content);
        Request requisicao = new Request.Builder()
                .url(Conexao.concatUrl(urlService))
                .method("POST", RequestBody.create(null, new byte[0]))
                .post(parametros)
                .build();
        Conexao.requestTratament(cliente, requisicao, parserResponse);
    }

    /**
     * Realiza uma requisição do tipo PUT
     * @param urlService URL do serviço
     * @param content Dados do body
     * @param parserResponse Responsável por interpretar a resposta
     */
    public static void put(String urlService, String content, ParserResponse parserResponse) {
        OkHttpClient cliente = new OkHttpClient();
        RequestBody parametros = RequestBody.create(JSON, content);
        Request requisicao = new Request.Builder()
                .url(Conexao.concatUrl(urlService))
                .put(parametros)
                .build();
        Conexao.requestTratament(cliente, requisicao, parserResponse);
    }
    /**
     * Realiza uma requisição do tipo GET
     * @param urlService URL do serviço
     * @param parserResponse Responsável por interpretar a resposta
     */
    public static void get(String urlService, ParserResponse parserResponse) {
        OkHttpClient cliente = new OkHttpClient();
        Request requisicao = new Request.Builder()
                .url(Conexao.concatUrl(urlService))
                .get()
                .build();
        Conexao.requestTratament(cliente, requisicao, parserResponse);
    }
    /**
     * Realiza uma requisição do tipo DELETE
     * @param urlService URL do serviço
     * @param parserResponse Responsável por interpretar a resposta
     */
    public static void delete(String urlService, ParserResponse parserResponse) {
        OkHttpClient cliente = new OkHttpClient();
        Request requisicao = new Request.Builder()
                .url(Conexao.concatUrl(urlService))
                .delete()
                .build();
        Conexao.requestTratament(cliente, requisicao, parserResponse);
    }

    /**
     * @param resposta - Response para retirar o body e realizar o parse.
     */
    public static <T> T parseRespostaObject(Response resposta, Class<T> clazz) throws IOException {
        String json = resposta.body().string();
        Gson gson = new GsonBuilder().create();

        return gson.fromJson(json, clazz);
    }

    /**
     * @param resposta - Response para retirar o body e realizar o parse.
     */
    public static <T> List<T> parseRespostaList(Response resposta, Class<T[]> clazz) throws IOException {
        String json = resposta.body().string();
        Gson gson = new GsonBuilder().create();

        T[] array =  gson.fromJson(json, clazz);
        return Arrays.asList(array);
    }

    /**
     * Interface anonima para passar implementação do parser para
     * cada respectivo objeto.
     */
    public static interface ParserResponse {
        void parse(Response r);
    }

    /**
     * Realiza o tratamento da resposta recebida pelo Webservice
     * @param cliente Cliente HTTP
     * @param request Request
     * @param parserResponse Parser da Resposta
     */
    private static void requestTratament(OkHttpClient cliente, Request request, final ParserResponse parserResponse) {
        cliente.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call chamada, IOException erro) {
                EventBus.getDefault().post(new SemConexaoException("Verifique sua conexão"));
            }

            @Override
            public void onResponse(Call chamada, Response resposta) throws IOException {
                parserResponse.parse(resposta);
            }
        });
    }

    /**
     * Realiza a construção da URL para consulta ao serviço
     * @param urlService nome do serviço
     * @return String
     */
    private static String concatUrl(String urlService) {
        return API_URL + urlService;
    }
}
