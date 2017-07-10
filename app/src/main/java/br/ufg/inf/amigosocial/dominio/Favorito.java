package br.ufg.inf.amigosocial.dominio;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * @author gabriel
 * @version 1.0.0
 */
public class Favorito {

    @SerializedName("id")
    private int id;

    @SerializedName("email")
    private String email;

    @SerializedName("postagem_id")
    private int postagemId;

    @SerializedName("deleted_at")
    private Date dateDelete;

    @SerializedName("created_at")
    private Date dateCreated;

    @SerializedName("updated_at")
    private Date dateUpdate;

    @SerializedName("postagem")
    private Postagem postagem;

    @SerializedName("instituicao")
    private Instituicao instituicao;

    @SerializedName("usuario")
    private Usuario usuario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPostagemId() {
        return postagemId;
    }

    public void setPostagemId(int postagemId) {
        this.postagemId = postagemId;
    }

    public Date getDateDelete() {
        return dateDelete;
    }

    public void setDateDelete(Date dateDelete) {
        this.dateDelete = dateDelete;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public Postagem getPostagem() {
        return postagem;
    }

    public void setPostagem(Postagem postagem) {
        this.postagem = postagem;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
