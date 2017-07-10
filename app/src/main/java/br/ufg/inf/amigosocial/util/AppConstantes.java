package br.ufg.inf.amigosocial.util;

/**
 * Created by ronyn on 06/07/2017.
 */

public enum AppConstantes {

    NOTICIAS_CARREGAMENTO_COMPLETO("noticias_carregamento_completo"),
    POSTAGENS_CARREGAMENTO_COMPLETO("postagens_carregamento_completo"),
    FAVORITOS_CARREGAMENTO_COMPLETO("favoritos_carragamento_completo"),
    /**
     * CHAVE DA OPÇÃO DE NOTÍCIAS SOBRE EDUCAÇÃO.
     */
    OP_CONFIG_EDC("config_op_edc"),

    /**
     * CHAVE DA OPÇÃO DE NOTÍCIAS SOBRE PROJETOS SOCIAIS.
     */
    OP_CONFIG_PROJ_SOC("config_op_proj_soc"),

    /**
     * CHAVE DA OPÇÃO DE NOTÍCIAS SOBRE O BRASIL.
     */
    OP_CONFIG_BRA("config_op_bra"),

    /**
     * CHAVE DA OPÇÃO DE NOTIFICAÇÕES HABILITADAS.
     */
    OP_CONFIG_NOTIFICACOES("config_op_notificacoes");

    private String chave;

    public String getChave() {
        return this.chave;
    }

    AppConstantes(String chave) {
        this.chave = chave;
    }

}
