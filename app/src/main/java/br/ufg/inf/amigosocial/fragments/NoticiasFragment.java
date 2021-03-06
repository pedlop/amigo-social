package br.ufg.inf.amigosocial.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

import java.io.IOException;
import java.util.List;

import br.ufg.inf.amigosocial.R;
import br.ufg.inf.amigosocial.adapters.NoticiasListAdapter;
import br.ufg.inf.amigosocial.conexao.Conexao;
import br.ufg.inf.amigosocial.dominio.Noticia;
import br.ufg.inf.amigosocial.dominio.Noticias;
import br.ufg.inf.amigosocial.exception.SemConexaoException;
import br.ufg.inf.amigosocial.util.AppConstantes;
import br.ufg.inf.amigosocial.util.RecyclerViewItemClickListener;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoticiasFragment extends BaseFragment {

    private RecyclerView mListaNoticias;
    private RecyclerView.LayoutManager mLayoutManager;
    private LinearLayout mSemConexaoLayout;
    private Button mTentarNovamente;
    private ProgressBar mProgressBar;
    private FrameLayout mFrameNoticias;

    public NoticiasFragment() {
        super();
        EventBus.getDefault().register(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_noticias, container, false);

        this.initComponentes(view);
        this.getNoticias();

        return view;
    }

    private void getNoticias() {
        mProgressBar.setVisibility(View.VISIBLE);
        Conexao.get("noticia", new Conexao.ParserResponse() {
            @Override
            public void parse(Response r) {
                try {
                    EventBus.getDefault().post(new Noticias(
                            Conexao.<Noticia>parseRespostaList(r, Noticia[].class)
                    ));
                    EventBus.getDefault().post(AppConstantes.NOTICIAS_CARREGAMENTO_COMPLETO);

                } catch (IOException | NullPointerException | IllegalStateException e) {
                    Log.d("NOTICIA_SERVICE", e.getMessage() + "\n");
                }
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Noticias noticias) {
        setNoticias(noticias.getNoticias());
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(SemConexaoException exception) {
        mSemConexaoLayout.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(AppConstantes event) {
        if (event.equals(AppConstantes.NOTICIAS_CARREGAMENTO_COMPLETO)) {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    private void setNoticias(final List<Noticia> noticias){
        if (noticias.size() > 0) {
            NoticiasListAdapter adapter = new NoticiasListAdapter(getActivity(), noticias);
            mListaNoticias.setAdapter(adapter);

            mListaNoticias.addOnItemTouchListener(new RecyclerViewItemClickListener(getActivity(), new RecyclerViewItemClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    String link = noticias.get(position).getLink();
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(link));
                    startActivity(intent);
                }
            }));

        } else {
            mListaNoticias.setVisibility(View.INVISIBLE);
        }
    }

    private void initComponentes(View view) {

        mFrameNoticias = (FrameLayout) view.findViewById(R.id.frame_noticias);

        mProgressBar = new ProgressBar(getActivity());
        mProgressBar.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        mFrameNoticias.addView(mProgressBar,0);

        mListaNoticias = (RecyclerView) view.findViewById(R.id.recycler_list);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mListaNoticias.setLayoutManager(mLayoutManager);

        mSemConexaoLayout = (LinearLayout) view.findViewById(R.id.sem_conexao);

        mTentarNovamente= (Button) view.findViewById(R.id.tentar_novamente);

        mTentarNovamente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNoticias();
            }
        });

    }



}