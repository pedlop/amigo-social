package br.ufg.inf.amigosocial.dominio;

import java.util.List;

/**
 * @author Rony Nogueira
 * @version 1.0
 */

public class Noticias {

    /**
     * Lista com as noticías carregadas
     */
    private List<Noticia> noticias;

    /**
     * Construtor default
     * @param noticias Lista de Noticías
     */
    public Noticias(List<Noticia> noticias) {
        this.noticias = noticias;
    }

    /**
     * Retorna a lista de notícias
     * @return List
     */
    public List<Noticia> getNoticias() {
        return noticias;
    }
}
