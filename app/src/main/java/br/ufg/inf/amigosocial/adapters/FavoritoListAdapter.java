package br.ufg.inf.amigosocial.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.ufg.inf.amigosocial.R;
import br.ufg.inf.amigosocial.dominio.Favorito;
import br.ufg.inf.amigosocial.holders.FavoritoViewHolder;

/**
 * @author gabriel
 * @see
 * @version 1.0.0
 */
public class FavoritoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Favorito> listaFavorito;

    public FavoritoListAdapter(Context context, List<Favorito> listaFavorito) {
        this.context = context;
        this.listaFavorito = listaFavorito;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_favorito, parent, false);
        return new FavoritoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        FavoritoViewHolder favoritoViewHolder = (FavoritoViewHolder) holder;
        Favorito favorito = listaFavorito.get(position);
        String titulo = favorito.getPostagem().getTitulo();
        String resumo = favorito.getPostagem().getDescricao();
        favoritoViewHolder.getTitulo().setText(
                titulo.length() > 20 ? titulo.substring(0, 19).concat("...") : titulo);
        favoritoViewHolder.getResumo().setText(
                resumo.length() > 180 ? resumo.substring(0,199).concat("...") : resumo
        );

        if (favorito.getPostagem().getMidia().getArquivo() != null)
            Picasso.with(this.context).load(favorito.getPostagem().getMidia().getArquivo())
                    .error(R.mipmap.ic_launcher)
                    .into(favoritoViewHolder.getImagem());
        else
            favoritoViewHolder.getImagem().setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        return listaFavorito.size();
    }

}
