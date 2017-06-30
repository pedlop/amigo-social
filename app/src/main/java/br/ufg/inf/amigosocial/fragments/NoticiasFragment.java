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
import java.util.List;

import br.ufg.inf.amigosocial.R;
import br.ufg.inf.amigosocial.adapters.NoticiasListAdapter;
import br.ufg.inf.amigosocial.conexao.WebNoticias;
import br.ufg.inf.amigosocial.dominio.Noticia;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoticiasFragment extends BaseFragment {

    private RecyclerView mListaNoticias;
    private RecyclerView.LayoutManager mLayoutManager;

    public NoticiasFragment() {
        super();
        EventBus.getDefault().register(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_noticias, container, false);

        mListaNoticias = (RecyclerView) view.findViewById(R.id.recycler_list);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mListaNoticias.setLayoutManager(mLayoutManager);

        this.getNoticias();

        return view;
    }

    private void getNoticias() {
        WebNoticias webNoticias = new WebNoticias("noticia");
        webNoticias.getRequisicao();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ArrayList<Noticia> noticias) {
        Log.d(this.getClass().getSimpleName(),noticias.size() + "");
        this.setNoticias(noticias);
    }

    private void setNoticias(ArrayList<Noticia> noticias){
        if (noticias.size() > 0) {
            NoticiasListAdapter adapter = new NoticiasListAdapter(getActivity(), noticias);
            mListaNoticias.setAdapter(adapter);
        } else {
            mListaNoticias.setVisibility(View.INVISIBLE);
        }
    }

}
