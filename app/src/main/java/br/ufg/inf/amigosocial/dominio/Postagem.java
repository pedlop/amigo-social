package br.ufg.inf.amigosocial.dominio;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author Pedro Victor - github.com/pedlop
 * @version 1.0
 * Created on 30/06/2017.
 */

public class Postagem implements Serializable {
    @SerializedName("id")
    private int id;
    @SerializedName("titulo")
    private String titulo;
    @SerializedName("cidade_id")
    private int cidadeID;
    @SerializedName("instituicao_id")
    private int instituicaoID;
    @SerializedName("descricao")
    private String descricao;
    @SerializedName("updated_at")
    private String data;

    public Postagem() {}

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getCidadeID() {
        return cidadeID;
    }

    public int getInstituicaoID() {
        return instituicaoID;
    }

    public String getDescricao() {
        return descricao;
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

    public void setCidadeID(int cidadeID) {
        this.cidadeID = cidadeID;
    }

    public void setInstituicaoID(int instituicaoID) {
        this.instituicaoID = instituicaoID;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setData(String data) {
        this.data = data;
    }
}
