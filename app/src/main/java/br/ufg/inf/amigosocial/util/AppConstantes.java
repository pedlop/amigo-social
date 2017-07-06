package br.ufg.inf.amigosocial.util;

/**
 * Created by ronyn on 06/07/2017.
 */

public enum AppConstantes {

    NOTICIAS_CARREGAMENTO_COMPLETO("noticias_carregamento_completo"),
    POSTAGENS_CARREGAMENTO_COMPLETO("postagens_carregamento_completo"),
    OP_CONFIG_NOTICIA("config_op_noticia"),
    OP_CONFIG_NOTIFICACOES("config_op_notificacoes");

    private String chave;

    public String getChave() {
        return this.chave;
    }

    AppConstantes(String chave) {
        this.chave = chave;
    }

}
