package br.ufg.inf.amigosocial.adapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import br.ufg.inf.amigosocial.R;
import br.ufg.inf.amigosocial.dominio.Inicio;
import br.ufg.inf.amigosocial.holders.InicioViewHolder;

/**
 * Created by pedro on 30/06/2017.
 */

public class InicioListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Inicio> listaPublicacoes;
    private Context context;

    public InicioListAdapter(Context context, ArrayList<Inicio> listaPublicacoes) {
        this.context = context;
        this.listaPublicacoes = listaPublicacoes;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_inicio, parent, false);
        return new InicioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        InicioViewHolder inicioViewHolder = (InicioViewHolder) holder;
        Inicio publicacao = listaPublicacoes.get(position);
        inicioViewHolder.getTitulo().setText(publicacao.getTitulo());
        inicioViewHolder.getResumo().setText(publicacao.getResumo());
        inicioViewHolder.getData().setText(publicacao.getData());
        if (publicacao.getImagem() != null) {
            Log.d("TAG",publicacao.getImagem()+"");
            Picasso.with(this.context).load(publicacao.getImagem()).error(R.mipmap.ic_launcher).into(inicioViewHolder.getImagem());
        } else {
            inicioViewHolder.getImagem().setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return listaPublicacoes.size();
    }
}
