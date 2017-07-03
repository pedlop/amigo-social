package br.ufg.inf.amigosocial.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import br.ufg.inf.amigosocial.R;
import br.ufg.inf.amigosocial.adapters.PostagemListAdapter;
import br.ufg.inf.amigosocial.conexao.WebPostagens;
import br.ufg.inf.amigosocial.dominio.Postagem;

/**
 * A simple {@link Fragment} subclass.
 */
public class InicioFragment extends BaseFragment {

    /*private RecyclerView mListaPostagens;
    private RecyclerView.LayoutManager mLayoutManager;

    public InicioFragment() {
        super();
        EventBus.getDefault().register(this);
    }*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.card_postagem, container, false);

        /*mListaPostagens = (RecyclerView) view.findViewById(R.id.recycler_list);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mListaPostagens.setLayoutManager(mLayoutManager);

        this.getPostagens();*/

        return view;
    }

    /*private void getPostagens() {
        WebPostagens webPostagens = new WebPostagens("postagem");
        webPostagens.getRequisicao();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ArrayList<Postagem> postagens) {
        Log.d(this.getClass().getSimpleName(),postagens.size() + "");
        this.setPostagens(postagens);
    }

    private void setPostagens(ArrayList<Postagem> postagens){
        if (postagens.size() > 0) {
            PostagemListAdapter adapter = new PostagemListAdapter(getActivity(), postagens);
            mListaPostagens.setAdapter(adapter);
        } else {
            mListaPostagens.setVisibility(View.INVISIBLE);
        }
    }*/

}
