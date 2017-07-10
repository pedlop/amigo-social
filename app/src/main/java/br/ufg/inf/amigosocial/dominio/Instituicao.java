package br.ufg.inf.amigosocial.dominio;

import com.google.gson.annotations.SerializedName;

/**
 * @author Pedro Victor - github.com/pedlop
 * @version 1.0
 * Created on 03/07/2017.
 */

public class Instituicao extends Postagem {
    @SerializedName("cnpj")
    private String cnpj;
    @SerializedName("nome")
    private String nome;
    @SerializedName("endereco")
    private String endereco;

    public Instituicao() {}

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
