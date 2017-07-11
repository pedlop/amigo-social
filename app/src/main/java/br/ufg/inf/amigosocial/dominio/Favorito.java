package br.ufg.inf.amigosocial.dominio;

import com.google.gson.annotations.SerializedName;


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
    private String dateDelete;

    @SerializedName("created_at")
    private String dateCreated;

    @SerializedName("updated_at")
    private String dateUpdate;

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

    public String getdateDelete() {
        return dateDelete;
    }

    public void setdateDelete(String dateDelete) {
        this.dateDelete = dateDelete;
    }

    public String getdateCreated() {
        return dateCreated;
    }

    public void setdateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getdateUpdate() {
        return dateUpdate;
    }

    public void setdateUpdate(String dateUpdate) {
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
