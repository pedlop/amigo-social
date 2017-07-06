package br.ufg.inf.amigosocial;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Activity responsável por mostrar a tela inicial da aplicação com o menu de navegação
 * Mostrar a tela raiz com as tabs
 * @author Rony Nogueira
 * @author Pedro Victor - github.com/pedlop
 * @version 1.0
 */


import java.util.ArrayList;
import java.util.List;

import br.ufg.inf.amigosocial.adapters.FragmentViewPagerAdapter;
import br.ufg.inf.amigosocial.fragments.FavoritosFragment;
import br.ufg.inf.amigosocial.fragments.InicioFragment;
import br.ufg.inf.amigosocial.fragments.NoticiasFragment;

public class MainActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {

    //private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.app_toolbar);

        setSupportActionBar(mToolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager);

        /**
         * Inicializando a viewpager com os fragmentos
         */
        FragmentViewPagerAdapter adapter = new FragmentViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FavoritosFragment());
        adapter.addFragment(new InicioFragment());
        adapter.addFragment(new NoticiasFragment());
        viewPager.setAdapter(adapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);

        //adicionando viewpager no tabLayout
        tabLayout.setupWithViewPager(viewPager);

        /**
         *  Para ter ícones junto com texto, mas esses ícones não serão alterados na seleção das tabs.
         *  Então use o seletor 'selector' para que os ícones mudem.
         */

        tabLayout.getTabAt(0).setIcon(R.mipmap.ic_favoritos);
        tabLayout.getTabAt(1).setIcon(R.mipmap.ic_inicial).select();
        tabLayout.getTabAt(2).setIcon(R.mipmap.ic_noticias);

        tabLayout.addOnTabSelectedListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Preenche o menu; Aqui adiciona itens à barra de ação se estiver presente.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // O item da barra de ação de controle é clicado aqui.
        // A barra de ação manipulará automaticamente os cliques no botão Home / Up,
        // desde que você especifique uma atividade pai no AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Intent intent = new Intent(this, ConfiguracoesActivity.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        String titulo = "";
        switch (tab.getPosition()) {
            case 0:
                titulo = getString(R.string.favoritos);
                break;
            case 1:
                titulo = getString(R.string.inicio);
                break;
            case 2:
                titulo = getString(R.string.noticias);
                break;

        }
        mToolbar.setTitle(titulo);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
