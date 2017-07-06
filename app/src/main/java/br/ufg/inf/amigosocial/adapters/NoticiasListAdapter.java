package br.ufg.inf.amigosocial.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.ufg.inf.amigosocial.R;
import br.ufg.inf.amigosocial.dominio.Noticia;
import br.ufg.inf.amigosocial.holders.NoticiaViewHolder;

/**
 * Created by ronyn on 29/06/2017.
 */

public class NoticiasListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Noticia> listaNoticias;
    private Context context;


    public NoticiasListAdapter(Context context, List<Noticia> listaNoticias) {
        this.context = context;
        this.listaNoticias = listaNoticias;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_noticia, parent, false);
        return new NoticiaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        try {
            NoticiaViewHolder noticiaViewHolder = (NoticiaViewHolder) holder;
            Noticia noticia = listaNoticias.get(position);
            noticiaViewHolder.getTitulo().setText(noticia.getTitulo());
            noticiaViewHolder.getResumo().setText(noticia.getResumo());
            noticiaViewHolder.getData().setText(noticia.getData());
            if (noticia.getImagem() != null) {
                Log.d("TAG",noticia.getImagem()+"");
                Picasso.with(this.context).load(noticia.getImagem()).error(R.mipmap.ic_launcher).into(noticiaViewHolder.getImagem());
            } else {
                noticiaViewHolder.getImagem().setVisibility(View.GONE);
            }
        } catch (ClassCastException e) {

        }
    }

    @Override
    public int getItemCount() {
        return listaNoticias.size();
    }
}
