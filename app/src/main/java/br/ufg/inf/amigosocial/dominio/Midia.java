package br.ufg.inf.amigosocial.dominio;

import com.google.gson.annotations.SerializedName;

/**
 * @author Pedro Victor - github.com/pedlop
 * @version 1.0
 * Created on 03/07/2017.
 */

public class Midia extends Postagem {
    @SerializedName("id")
    private int id;
    @SerializedName("postagem_id")
    private int postagemID;
    @SerializedName("arquivo")
    private String midia;

    public int getId() {
        return id;
    }

    public int getPostagemID() {
        return postagemID;
    }

    public String getMidia() {
        return midia;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPostagemID(int postagemID) {
        this.postagemID = postagemID;
    }

    public void setMidia(String midia) {
        this.midia = midia;
    }

}
