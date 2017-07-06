package br.ufg.inf.amigosocial.dominio;

import java.util.List;

/**
 * @author Pedro Victor - github.com/pedlop
 * @version 1.0
 * Created on 05/07/2017.
 */

public class Postagens {

    //Lista de postagens
    private List<Postagem> postagens;

    /**
     * Construtor padrão
     * @param postagens - Lista de postagens
     */
    public Postagens(List<Postagem> postagens) {
        this.postagens = postagens;
    }

    /**
     * Retorna a lista de postagens
     * @return List
     */
    public List<Postagem> getPostagens() {
        return this.postagens;
    }
}
