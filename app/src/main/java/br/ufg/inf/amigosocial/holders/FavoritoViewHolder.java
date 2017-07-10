package br.ufg.inf.amigosocial.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.ufg.inf.amigosocial.R;

/**
 * @author gabriel
 * @see
 * @version 1.0.0
 */
public class FavoritoViewHolder extends RecyclerView.ViewHolder {

    private TextView titulo, resumo;

    private ImageView imagem;

    public FavoritoViewHolder(View view) {
        super(view);
        this.titulo = (TextView) view.findViewById(R.id.text_titulo_card_fav);
        this.resumo = (TextView) view.findViewById(R.id.text_resumo_card_fav);

        this.imagem = (ImageView) view.findViewById(R.id.img_card_fav);
    }

    public TextView getTitulo() {
        return titulo;
    }

    public void setTitulo(TextView titulo) {
        this.titulo = titulo;
    }

    public TextView getResumo() {
        return resumo;
    }

    public void setResumo(TextView resumo) {
        this.resumo = resumo;
    }

    public ImageView getImagem() {
        return imagem;
    }

    public void setImagem(ImageView imagem) {
        this.imagem = imagem;
    }
}
