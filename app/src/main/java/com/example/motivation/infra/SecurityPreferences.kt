package com.example.motivation.infra

import android.content.Context

class SecurityPreferences(context: Context) {

    //Cria um arquivo de shared preferences com o nome de "motivation" de modo privado,
    // ou seja, apenas esta aplicação terá acesso a esses dados.
    private val mShared =
        context.getSharedPreferences("motivation", Context.MODE_PRIVATE);

    fun storeString(key: String, value: String){
        //Salva um dado do tipo string no arquivo. O arg1 é uma string que vai servir como chave
        //e o arg2 é a string que vai ser o valor (key,value)
        mShared.edit().putString(key,value).apply()
        //Os métodos edit e putX - X = String, Float, etc - retornam um objeto SharedPreferences.Editor
        //desta maneira, funciona como um builder, é possível inserir vários valores usando putX().putX()
        //para efetivamente salvar esses dados, é necessário usar o apply()
    }
    fun getString(key: String): String{
       return mShared.getString(key, "") ?: ""
    }
}