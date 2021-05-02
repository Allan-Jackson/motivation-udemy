package com.example.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.motivation.R
import com.example.motivation.infra.MotivationConstants
import com.example.motivation.infra.SecurityPreferences
import kotlinx.android.synthetic.main.activity_splash.*

/*Para usar o synthetics é preciso adicionar o seu plugin no gradle:
    plugins{
        id 'kotlin-android-extensions'
    }
 */
class SplashActivity : AppCompatActivity() {

    private lateinit var mSecurityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //supportActionBar é a barra com o nome da aplicação
        supportActionBar?.hide() //escondemos a supportActionBar caso ela seja diferente de null

        //cria a instância de SharedPreference para armazenar o nome do usuário
        mSecurityPreferences = SecurityPreferences(this)

        buttonSave.setOnClickListener(object: View.OnClickListener{ //Criando objeto anônimo
            override fun onClick(v: View){
                handleSave()
            }
        })
        verifyName()
    }
    private fun handleSave(){
        val name = editName.text.toString()
        if(name != ""){
            mSecurityPreferences.storeString(MotivationConstants.KEY.PERSON_NAME,name) //guarda o nome do usuário
            //Entender Lambda para saber como usar o ::
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }else{
            Toast.makeText(this,"Informe seu nome!",Toast.LENGTH_SHORT).show()
        }
    }
    private fun verifyName(){
        val name = mSecurityPreferences.getString(MotivationConstants.KEY.PERSON_NAME)
        if(name != ""){ //se já tiver um nome registrado, vai direto para Main Activity
            startActivity(Intent(this,MainActivity::class.java))
            finish() //destrói a Activity, impedindo que o usuário volte para colocar o nome após já ter salvo
        }
    }
}