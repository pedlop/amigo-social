package br.ufg.inf.amigosocial;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import br.ufg.inf.amigosocial.util.AppConstantes;
import br.ufg.inf.amigosocial.util.PreferenciasApp;

/**
 * @author gabriel
 * @see BaseActivity
 * @version 1.0.0
 * */
public class ConfiguracoesActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {


    /**
     *
     * @param savedInstanceState .
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_toolbar);

        toolbar.setTitle(getString(R.string.configuracoes));

        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.mipmap.ic_noticias);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        CheckBox checkBoxNotificacoes = (CheckBox) findViewById(R.id.check_box_notificacoes);
        CheckBox checkBoxNoticias = (CheckBox) findViewById(R.id.check_box_noticias);


        checkBoxNotificacoes.setOnCheckedChangeListener(this);

        checkBoxNoticias.setOnCheckedChangeListener(this);

    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.check_box_notificacoes:
                PreferenciasApp.setPreferencia(ConfiguracoesActivity.this, AppConstantes.OP_CONFIG_NOTIFICACOES.getChave(), b ? 1 : 0);
                break;
            case R.id.check_box_noticias:
                PreferenciasApp.setPreferencia(ConfiguracoesActivity.this, AppConstantes.OP_CONFIG_NOTICIA.getChave(), b ? 1 : 0);
                break;
            default:
                break;
        }
    }
}
