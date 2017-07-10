package br.ufg.inf.amigosocial.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import br.ufg.inf.amigosocial.R;
import br.ufg.inf.amigosocial.adapters.FavoritoListAdapter;
import br.ufg.inf.amigosocial.conexao.WebFavoritos;
import br.ufg.inf.amigosocial.dominio.Favorito;
import br.ufg.inf.amigosocial.dominio.Favoritos;
import br.ufg.inf.amigosocial.dominio.Noticias;
import br.ufg.inf.amigosocial.exception.SemConexaoException;
import br.ufg.inf.amigosocial.util.AppConstantes;

/**
 * @author gabriel
 * @see BaseFragment
 * @version 1.0.0
 *  A simple {@link Fragment} subclass.
 */
public class FavoritosFragment extends BaseFragment {

    private RecyclerView mListaFavorito;
    private RecyclerView.LayoutManager mLayoutManager;
    private LinearLayout mSemConexaoLayout;
    private Button mTentarNovamente;
    private ProgressBar mProgressBar;
    private FrameLayout mFrameFavorito;


    public FavoritosFragment() {
        super();
        EventBus.getDefault().register(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_favoritos, container, false);
        this.getFavoritos();
        this.initComponentes(view);
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Favoritos favoritos) {
        setFavoritos(favoritos.getListaFavorito());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(SemConexaoException exception) {
        mSemConexaoLayout.setVisibility(View.VISIBLE);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(AppConstantes event) {
        if (event.equals(AppConstantes.FAVORITOS_CARREGAMENTO_COMPLETO)) {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    private void setFavoritos(List<Favorito> favoritos) {
        if (favoritos.size() > 0) {
            FavoritoListAdapter adapter = new FavoritoListAdapter(getActivity(), favoritos);
            mListaFavorito.setAdapter(adapter);
        } else
            mListaFavorito.setVisibility(View.INVISIBLE);
    }


    private void getFavoritos() {
        mProgressBar.setVisibility(View.VISIBLE);
        WebFavoritos webFavoritos = new WebFavoritos();
        webFavoritos.getRequisicao();
    }

    private void initComponentes(View view) {
        mFrameFavorito = (FrameLayout) view.findViewById(R.id.frame_favoritos);

        mProgressBar = new ProgressBar(getActivity());
        mProgressBar.setLayoutParams(
                new LinearLayoutCompat.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        );

        mFrameFavorito.addView(mProgressBar, 0);

        mListaFavorito = (RecyclerView) view.findViewById(R.id.recycler_list);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mListaFavorito.setLayoutManager(mLayoutManager);

        mSemConexaoLayout = (LinearLayout) view.findViewById(R.id.sem_conexao);

        mTentarNovamente= (Button) view.findViewById(R.id.tentar_novamente);

        mTentarNovamente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFavoritos();
            }
        });

    }
}