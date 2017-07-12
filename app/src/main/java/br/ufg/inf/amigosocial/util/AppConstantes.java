package br.ufg.inf.amigosocial.util;

/**
 * Created by ronyn on 06/07/2017.
 */

public enum AppConstantes {

    NOTICIAS_CARREGAMENTO_COMPLETO("noticias_carregamento_completo"),
    POSTAGENS_CARREGAMENTO_COMPLETO("postagens_carregamento_completo"),
    FAVORITOS_CARREGAMENTO_COMPLETO("favoritos_carragamento_completo"),
    FAVORITOS_EMAIL("favoritos_email"),
    FAVORITO_ADICIONADO_SUCESSO("add_favorito_sucesso"),
    FAVORITO_ADICIONADO_FALHA("add_favorito_falha"),
    FAVORITO_REMOVIDO_SUCESSO("rm_favorito_sucesso"),
    FAVORITO_REMOVIDO_FALHA("rm_favorito_falha"),
    FAVORITOS_ATUALIZADOS("favoritos_atualizados"),
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
