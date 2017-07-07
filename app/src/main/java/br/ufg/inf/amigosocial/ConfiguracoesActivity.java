package br.ufg.inf.amigosocial;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
     * Variaveis que correspondem aos
     * check box da activity_configuracoes.xml
     * */
    private CheckBox checkBoxEducacao;
    private CheckBox checkBoxProjSocial;
    private CheckBox checkBoxBrasil;
    private CheckBox checkBoxNotificacoes;



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

        /**
         * Opções de habilitar notícias.
         */
        checkBoxEducacao   = (CheckBox) findViewById(R.id.check_box_noticias_edc);
        checkBoxEducacao.setOnCheckedChangeListener(this);

        checkBoxProjSocial = (CheckBox) findViewById(R.id.check_box_noticias_proj_soc);
        checkBoxProjSocial.setOnCheckedChangeListener(this);

        checkBoxBrasil     = (CheckBox) findViewById(R.id.check_box_noticias_bra);
        checkBoxBrasil.setOnCheckedChangeListener(this);

        /**
         * Opções de habilitar notificação
         * */
        checkBoxNotificacoes = (CheckBox) findViewById(R.id.check_box_notificacoes);
        checkBoxNotificacoes.setOnCheckedChangeListener(this);

        atualizaMenuOpcoes();
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.check_box_noticias_edc:
                PreferenciasApp.setPreferencia(ConfiguracoesActivity.this, AppConstantes.OP_CONFIG_EDC.getChave(), b ? 1 : 0);
                break;
            case R.id.check_box_noticias_proj_soc:
                PreferenciasApp.setPreferencia(ConfiguracoesActivity.this, AppConstantes.OP_CONFIG_PROJ_SOC.getChave(), b ? 1 : 0);
                break;

            case R.id.check_box_noticias_bra:
                PreferenciasApp.setPreferencia(ConfiguracoesActivity.this, AppConstantes.OP_CONFIG_BRA.getChave(), b ? 1 : 0);
                break;
            case R.id.check_box_notificacoes:
                PreferenciasApp.setPreferencia(ConfiguracoesActivity.this, AppConstantes.OP_CONFIG_NOTIFICACOES.getChave(), b ? 1 : 0);
                break;
            default:
                break;
        }
    }

    /**
     * Atualiza o menu com as de acordo com as opções que já foram escolhidas
     * pelo usúario.
     */
    private void atualizaMenuOpcoes() {
        buscaPreferenciaPrefencesBase(checkBoxEducacao, AppConstantes.OP_CONFIG_EDC);
        buscaPreferenciaPrefencesBase(checkBoxProjSocial, AppConstantes.OP_CONFIG_PROJ_SOC);
        buscaPreferenciaPrefencesBase(checkBoxBrasil, AppConstantes.OP_CONFIG_BRA);
        buscaPreferenciaPrefencesBase(checkBoxNotificacoes, AppConstantes.OP_CONFIG_NOTIFICACOES);
    }

    /**
     * Atualiza o checkbox passado de acordo com a base de
     * dados.
     * @param checkBox - Checkbox a ser atualizado.
     * @param constantes - Chave da preferencia.
     */
    private void buscaPreferenciaPrefencesBase(CheckBox checkBox, AppConstantes constantes) {
        checkBox.setChecked(buscaPreferenciaPreferencesBase(this, constantes));
    }

    /**
     * Metodo estatico para uso em outras classes que desejem
     * saber se a opções da configuracão estão ou não habilitadas
     * @param context - Context da tela atual.
     * @param constantes - Enum da configração que deseja-se saber.
     * @return <code>True</code> Habilitado <code>False</code> Desabilitado.
     */
    public static boolean buscaPreferenciaPreferencesBase(Context context, AppConstantes constantes) {
        int pref = PreferenciasApp.getPreferencia(context, constantes.getChave(), 0);
        return pref == 1 ? true : false;
    }
}
