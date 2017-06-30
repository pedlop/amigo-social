package br.ufg.inf.amigosocial.dominio;

import com.google.gson.annotations.SerializedName;

/**
 * Created by pedro on 30/06/2017.
 */

public class Inicio {
    @SerializedName("id")
    private int id;
    @SerializedName("titulo")
    private String titulo;
    @SerializedName("resumo")
    private String resumo;
    @SerializedName("imagem")
    private String imagem;
    @SerializedName("link")
    private String link;
    @SerializedName("created_at")
    private String data;

    public Inicio() {

    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public String getImagem() {
        return imagem;
    }

    public String getData() {
        return data;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public void setData(String data) {
        this.data = data;
    }
}
