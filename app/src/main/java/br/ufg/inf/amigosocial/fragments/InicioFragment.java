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
import br.ufg.inf.amigosocial.adapters.InicioListAdapter;
import br.ufg.inf.amigosocial.dominio.Inicio;

/**
 * A simple {@link Fragment} subclass.
 */
public class InicioFragment extends BaseFragment {

    /*private RecyclerView mListaPublicacoes;
    private RecyclerView.LayoutManager mLayoutManager;

    public InicioFragment() {
        super();
        EventBus.getDefault().register(this);
    }*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.card_inicio, container, false);

        /*mListaPublicacoes = (RecyclerView) view.findViewById(R.id.recycler_list);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mListaPublicacoes.setLayoutManager(mLayoutManager);*/

        return view;
    }

    /*@Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ArrayList<Inicio> publicacoes) {
        Log.d(this.getClass().getSimpleName(),publicacoes.size() + "");
        this.setPublicacoes(publicacoes);
    }

    private void setPublicacoes(ArrayList<Inicio> publicacoes){
        if (publicacoes.size() > 0) {
            InicioListAdapter adapter = new InicioListAdapter(getActivity(), publicacoes);
            mListaPublicacoes.setAdapter(adapter);
        } else {
            mListaPublicacoes.setVisibility(View.INVISIBLE);
        }
    }*/

}
