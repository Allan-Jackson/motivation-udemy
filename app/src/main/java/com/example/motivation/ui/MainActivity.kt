package com.example.motivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.example.motivation.R
import com.example.motivation.infra.MotivationConstants
import com.example.motivation.infra.SecurityPreferences
import com.example.motivation.repository.Mock
import com.example.motivation.repository.Phrase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mSecurityPreferences: SecurityPreferences
    private var mPhraseFilter = MotivationConstants.PHRASE_FILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //supportActionBar é a barra com o nome da aplicação
        supportActionBar?.hide() //escondemos a supportActionBar caso ela seja diferente de null


        //seta o nome do usuário para o TextView
        mSecurityPreferences = SecurityPreferences(this)
        val name = mSecurityPreferences.getString(MotivationConstants.KEY.PERSON_NAME)
        textName.text = "${this.getString(R.string.ola)}, $name"

        //seta os listeners dos elementos necessários
        setListeners()

        //executa clique do filter ALL para já começar filtrado
        imageAll.callOnClick()
        buttonNewPhrase.callOnClick()

    }

    /**
     * Called when a view has been clicked.
     *
     * @param view The view that was clicked.
     */
    override fun onClick(view: View) {

        val id = view.id

        //cria uma lista contendo todos os ids dos ImageViews de filtro
        val listFilter = listOf(R.id.imageAll, R.id.imageHappy, R.id.imageMorning)
        if (id == R.id.buttonNewPhrase) {
            handleNewPhrase()
        } else if (id in listFilter) { //verifica se o id é de algum dos imageViews de filtro
            handleFilter(id) //chama o método responsável pelo filtro
        }
    }

    private fun handleFilter(id: Int) {
        //toda vez que esse método for chamado ele branqueia todos os ImageView e depois pinta o selecionado
        imageAll.setColorFilter(ContextCompat.getColor(this,R.color.white))
        imageHappy.setColorFilter(ContextCompat.getColor(this,R.color.white))
        imageMorning.setColorFilter(ContextCompat.getColor(this,R.color.white))

        //a lógica de verificar o filtro que foi clicado fica encapsulada neste método
        when (id) {
            R.id.imageAll -> {
                imageAll.setColorFilter(ContextCompat.getColor(this,R.color.accentColor))
                mPhraseFilter = MotivationConstants.PHRASE_FILTER.ALL
            }
            R.id.imageHappy -> {
                imageHappy.setColorFilter(ContextCompat.getColor(this,R.color.accentColor))
                mPhraseFilter = MotivationConstants.PHRASE_FILTER.HAPPY
            }
            R.id.imageMorning -> {
                imageMorning.setColorFilter(ContextCompat.getColor(this,R.color.accentColor))
                mPhraseFilter = MotivationConstants.PHRASE_FILTER.MORNING
            }

        }
    }

    //Processo de gerar nova frase do botão
    private fun handleNewPhrase() {
        textPhrase.text = Mock().getPhrase(mPhraseFilter)
    }

    //seta os eventos
    private fun setListeners(){
        buttonNewPhrase.setOnClickListener(this)
        imageAll.setOnClickListener(this)
        imageHappy.setOnClickListener(this)
        imageMorning.setOnClickListener(this)
    }
}