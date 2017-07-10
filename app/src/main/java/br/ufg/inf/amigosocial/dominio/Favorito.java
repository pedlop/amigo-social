package br.ufg.inf.amigosocial.dominio;

import com.google.gson.annotations.SerializedName;

/**
 * @author gabriel
 * @version 1.0.0
 */
public class Favorito {

    @SerializedName("id")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
