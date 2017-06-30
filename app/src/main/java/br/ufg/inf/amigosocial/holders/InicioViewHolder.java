package br.ufg.inf.amigosocial.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.ufg.inf.amigosocial.R;

/**
 * Created by pedro on 30/06/2017.
 */

public class InicioViewHolder extends RecyclerView.ViewHolder {

    private TextView titulo, resumo, data;
    private ImageView imagem;

    public InicioViewHolder(View view) {
        super(view);
        this.titulo = (TextView) view.findViewById(R.id.inicio_titulo);
        this.resumo = (TextView) view.findViewById(R.id.inicio_resumo);
        this.data = (TextView) view.findViewById(R.id.inicio_data);
        this.imagem = (ImageView) view.findViewById(R.id.inicio_imagem);
    }

    public TextView getTitulo() {
        return titulo;
    }

    public TextView getResumo() {
        return resumo;
    }

    public TextView getData() {
        return data;
    }

    public ImageView getImagem() {
        return imagem;
    }
}
