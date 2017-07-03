package br.ufg.inf.amigosocial.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.ufg.inf.amigosocial.R;

/**
 * @author Pedro Victor - github.com/pedlop
 * @version 1.0
 * Created on 30/06/2017.
 */

public class PostagemViewHolder extends RecyclerView.ViewHolder {

    private TextView titulo, nomeInstituicao, descricao, data;
    private ImageView imagem;

    public PostagemViewHolder(View view) {
        super(view);
        this.titulo = (TextView) view.findViewById(R.id.postagem_titulo);
        //this.nomeInstituicao = (TextView) view.findViewById(R.id.postagem_intituicao_nome);
        this.descricao = (TextView) view.findViewById(R.id.postagem_descricao);
        this.data = (TextView) view.findViewById(R.id.postagem_data);
        this.imagem = (ImageView) view.findViewById(R.id.postagem_imagem);
    }

    public TextView getTitulo() {
        return titulo;
    }

    public TextView getNomeInstituicao() {
        return nomeInstituicao;
    }

    public TextView getDescricao() {
        return descricao;
    }

    public TextView getData() {
        return data;
    }

    public ImageView getImagem() {
        return imagem;
    }
}
