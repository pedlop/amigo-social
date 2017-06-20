package br.ufg.inf.amigosocial;

import android.os.Bundle;

/**
 * Activity responsável por mostrar a tela inicial da aplicação com o menu de navegação
 * @author Rony Nogueira
 * @version 1.0
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
