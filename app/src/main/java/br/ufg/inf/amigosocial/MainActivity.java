package br.ufg.inf.amigosocial;

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
 * @author Pedro Victor
 * @version 1.0
 */


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BaseActivity baseActivity = new BaseActivity();

        /*toolbar = (Toolbar) findViewById(R.id.tabs);
        setSupportActionBar(toolbar);*/

        viewPager = (ViewPager) findViewById(R.id.viewpager);

        /**
         * Inicializando a viewpager com os fragmentos
         * newInstance('Texto dentro da tab'), 'Texto embaixo do Icone')
         */
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(MyFragment.newInstance("Favoritos Fragment"), "");
        adapter.addFragment(MyFragment.newInstance("Início Fragment"), "");
        adapter.addFragment(MyFragment.newInstance("Notícias Fragment"), "");
        viewPager.setAdapter(adapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);

        //adicionando viewpager no tabLayout
        tabLayout.setupWithViewPager(viewPager);

        /**
         *  Para ter ícones junto com texto, mas esses ícones não serão alterados na seleção das tabs.
         *  Então use o seletor 'selector' para que os ícones mudem.
         */

        tabLayout.getTabAt(0).setIcon(R.mipmap.ic_favoritos);
        tabLayout.getTabAt(1).setIcon(R.mipmap.ic_inicial);
        tabLayout.getTabAt(2).setIcon(R.mipmap.ic_noticias);

        /**
         * Para personalizar o layout da tab, colocando a imagem em cima do texto.
         */
        //TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tabview, null);
        //tabOne.setText("Home Fragment");
        //tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_home_selector, 0, 0);
        //tabLayout.getTabAt(0).setCustomView(tabOne);

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<MyFragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(MyFragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
