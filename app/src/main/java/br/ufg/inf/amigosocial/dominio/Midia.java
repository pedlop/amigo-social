package br.ufg.inf.amigosocial.dominio;

import com.google.gson.annotations.SerializedName;

/**
 * @author Pedro Victor - github.com/pedlop
 * @version 1.0
 * Created on 03/07/2017.
 */

public class Midia extends Postagem {
    @SerializedName("postagem_id")
    private int postagemID;
    @SerializedName("arquivo")
    private String arquivo;

    public int getPostagemID() {
        return postagemID;
    }

    public void setPostagemID(int postagemID) {
        this.postagemID = postagemID;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }
}
