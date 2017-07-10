package br.ufg.inf.amigosocial;

import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Activity base para todas as demais utilizadas na aplicação
 * @author Rony Nogueira
 * @version 1.0
 */

public class BaseActivity extends AppCompatActivity {

    protected TextView getTextViewById(int id) {
        return (TextView) findViewById(id);
    }

    protected ImageView getImageViewById(int id) {
        return (ImageView) findViewById(id);
    }

}
