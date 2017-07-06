package br.ufg.inf.amigosocial.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Realiza a inclusão e remoção de dados em SharedPreferences para a aplicação
 * @author Gabriel
 * @see SharedPreferences
 * @version 1.0
 */

public class PreferenciasApp {

    /**
     * Chave de acesso as preferencias da aplicação
     */
    private static final String CHAVE_PREFERENCIAS = "amigo_social_prefs";

    /**
     * Inclui uma nova preferência do tipo inteiro para a aplicação
     * @param context Contexto da aplicação
     * @param preferencia Nome da chave de acesso ao valor
     * @param value Valor a ser salvo
     */
    public static void setPreferencia(Context context,String preferencia,int value){
        SharedPreferences preferences = context.getSharedPreferences(CHAVE_PREFERENCIAS,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(preferencia,value);
        editor.apply();
    }

    /**
     * Inclui uma nova preferência do tipo String para a aplicação
     * @param context Contexto da aplicação
     * @param preferencia Nome da chave de acesso ao valor
     * @param value Valor que será salvo
     */
    public static void setPreferencia(Context context,String preferencia,String value){
        SharedPreferences preferences = context.getSharedPreferences(CHAVE_PREFERENCIAS,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(preferencia, value);
        editor.apply();
    }

    /**
     * Retorna o valor de uma preferencia do tipo String
     * @param context Contexto da aplicação
     * @param preferencia Nome da chave de acesso ao valor
     * @param defaultValue Valor retornado caso a preferência não exista
     * @return String
     */
    public static String getPreferencia(Context context,String preferencia,String defaultValue){
        SharedPreferences preferences = context.getSharedPreferences(CHAVE_PREFERENCIAS,Context.MODE_PRIVATE);
        return preferences.getString(preferencia,defaultValue);
    }

    /**
     * Retorna o valor de uma preferência do tipo inteiro
     * @param context Contexto da aplicação
     * @param preferencia Nome da chave de acesso ao valor
     * @param defaultValue Valor retornado caso a preferência não exista
     * @return int
     */
    public static int getPreferencia(Context context,String preferencia,int defaultValue){
        SharedPreferences preferences = context.getSharedPreferences(CHAVE_PREFERENCIAS,Context.MODE_PRIVATE);
        return preferences.getInt(preferencia, defaultValue);
    }

    /**
     * Remove uma preferência que esteja salva
     * @param context Contexto da aplicação
     * @param preferencia Nome da chave de acesso ao valor
     */
    public static void removePreferencias(Context context, String preferencia){
        SharedPreferences preferences = context.getSharedPreferences(CHAVE_PREFERENCIAS,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(preferencia).apply();
    }

    /**
     * Remove várias preferências que estejam salvas
     * @param context Contexto da aplicação
     * @param preferencias Nomes das chaves de acesso ao valores
     */
    public static void removePreferencias(Context context,String[] preferencias){
        SharedPreferences preferences = context.getSharedPreferences(CHAVE_PREFERENCIAS,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        for (String preferencia : preferencias) editor.remove(preferencia).apply();
    }
}