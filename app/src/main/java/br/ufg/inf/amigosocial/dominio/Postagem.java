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
    @SerializedName("descricao")
    private String descricao;
    @SerializedName("como_ajudar")
    private String comoAjudar;
    @SerializedName("cidade_id")
    private int cidadeID;
    @SerializedName("instituicao_id")
    private int instituicaoID;
    @SerializedName("latitude")
    private double institutoLatitude;
    @SerializedName("longitude")
    private double institutoLongitude;
    @SerializedName("upStringd_at")
    private String data;
    @SerializedName("midia")
    private Midia midia;
    @SerializedName("instituicao")
    private Instituicao instituicao;

    Postagem() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getCidadeID() {
        return cidadeID;
    }

    public void setCidadeID(int cidadeID) {
        this.cidadeID = cidadeID;
    }

    public int getInstituicaoID() {
        return instituicaoID;
    }

    public void setInstituicaoID(int instituicaoID) {
        this.instituicaoID = instituicaoID;
    }

    public double getInstitutoLatitude() {
        return institutoLatitude;
    }

    public void setInstitutoLatitude(int institutoLatitude) {
        this.institutoLatitude = institutoLatitude;
    }

    public double getInstitutoLongitude() {
        return institutoLongitude;
    }

    public void setInstitutoLongitude(int institutoLongitude) {
        this.institutoLongitude = institutoLongitude;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Midia getMidia() {
        return midia;
    }

    public void setMidia(Midia midia) {
        this.midia = midia;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }


    public void setInstitutoLatitude(double institutoLatitude) {
        this.institutoLatitude = institutoLatitude;
    }

    public String getComoAjudar() {
        return comoAjudar;
    }

    public void setComoAjudar(String comoAjudar) {
        this.comoAjudar = comoAjudar;
    }

    public void setInstitutoLongitude(double institutoLongitude) {
        this.institutoLongitude = institutoLongitude;

    }

}