package br.ufg.inf.amigosocial.dominio;

import java.util.List;

/**
 * @author gabriel
 * @version 1.0.0
 */
public class Favoritos {

    private List<Favorito> listaFavorito;

    public Favoritos(List<Favorito> listaFavorito) {
        this.listaFavorito = listaFavorito;
    }

    public List<Favorito> getListaFavorito() {
        return listaFavorito;
    }
}
