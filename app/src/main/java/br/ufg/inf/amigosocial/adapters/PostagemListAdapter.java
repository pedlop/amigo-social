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
import br.ufg.inf.amigosocial.dominio.Postagem;
import br.ufg.inf.amigosocial.holders.PostagemViewHolder;

/**
 * @author Pedro Victor - github.com/pedlop
 * @version 1.0
 * Created on 30/06/2017.
 */

public class PostagemListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Postagem> listaPostagens;
    private Context context;

    public PostagemListAdapter(Context context, List<Postagem> listaPostagens) {
        this.context = context;
        this.listaPostagens = listaPostagens;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_postagem, parent, false);
        return new PostagemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PostagemViewHolder postagemViewHolder = (PostagemViewHolder) holder;
        Postagem postagem = listaPostagens.get(position);
        postagemViewHolder.getTitulo().setText(postagem.getTitulo());

        if (postagem.getMidia().getArquivo() != null) {
            Picasso.with(this.context).load(postagem.getMidia().getArquivo()).error(R.mipmap.ic_launcher).into(postagemViewHolder.getImagem());
        } else {
        postagemViewHolder.getImagem().setVisibility(View.GONE);
        }
        postagemViewHolder.getNomeInstituicao().setText(postagem.getInstituicao().getNome());
        postagemViewHolder.getDescricao().setText(postagem.getDescricao());
        postagemViewHolder.getData().setText(postagem.getData());
    }

    @Override
    public int getItemCount() {
        return listaPostagens.size();
    }
}
