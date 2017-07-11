package br.ufg.inf.amigosocial.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.List;

import br.ufg.inf.amigosocial.DetalhePostagemActivity;
import br.ufg.inf.amigosocial.R;
import br.ufg.inf.amigosocial.adapters.PostagemListAdapter;
import br.ufg.inf.amigosocial.conexao.Conexao;
import br.ufg.inf.amigosocial.dominio.Postagem;
import br.ufg.inf.amigosocial.dominio.Postagens;
import br.ufg.inf.amigosocial.util.AppConstantes;
import br.ufg.inf.amigosocial.util.RecyclerViewItemClickListener;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class InicioFragment extends BaseFragment {

    private RecyclerView mListaPostagens;
    private FrameLayout mPostagensLayout;
    private ProgressBar mProgressBar;

    public InicioFragment() {
        super();
        EventBus.getDefault().register(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        this.initComponentes(view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.getPostagens();
    }

    private void getPostagens() {
        Conexao.get("postagem", new Conexao.ParserResonse() {
            @Override
            public void parse(Response r) {
                try {
                    EventBus.getDefault().post(new Postagens(
                            Conexao.<Postagem>parseRespostaList(r, Postagem[].class)
                    ));
                    EventBus.getDefault().post(AppConstantes.POSTAGENS_CARREGAMENTO_COMPLETO);
                } catch (IOException | NullPointerException | IllegalStateException e) {
                    Log.d("POSTAGENS_SERVICE", e.getMessage() + "\n");
                }
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Postagens postagens) {
        this.setPostagens(postagens.getPostagens());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(AppConstantes event) {
        if(event.equals(AppConstantes.POSTAGENS_CARREGAMENTO_COMPLETO)) {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    /**
     * Realiza o preenchimento da lista de postagens
     * @see Postagens
     * @param postagens Lista de Postagens
     */
    private void setPostagens(final List<Postagem> postagens){
        if (postagens.size() > 0) {
            PostagemListAdapter adapter = new PostagemListAdapter(getActivity(), postagens);
            mListaPostagens.setAdapter(adapter);

            mListaPostagens.addOnItemTouchListener(new RecyclerViewItemClickListener(getActivity(), new RecyclerViewItemClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    abrirDetalhePostagem(postagens.get(position));
                }
            }));
        } else {
            mListaPostagens.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Realiza a inicialização e bind dos componentes do layout
     * @param view Layout do fragment
     */
    private void initComponentes(View view) {

        mListaPostagens = (RecyclerView) view.findViewById(R.id.recycler_list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mListaPostagens.setLayoutManager(mLayoutManager);

        mPostagensLayout = (FrameLayout) view.findViewById(R.id.frame_postagens);

        mProgressBar = new ProgressBar(getActivity());
        mProgressBar.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        mPostagensLayout.addView(mProgressBar, 0);
        mProgressBar.setVisibility(View.VISIBLE);

    }

    /**
     * Abre a activity responsável por detalhar os dados de uma postagem
     * @param postagem Postagem que será exibida
     */
    private void abrirDetalhePostagem(Postagem postagem) {
        Intent intent = new Intent(getActivity(), DetalhePostagemActivity.class);
        intent.putExtra("POSTAGEM", postagem);
        startActivity(intent);
    }

}
