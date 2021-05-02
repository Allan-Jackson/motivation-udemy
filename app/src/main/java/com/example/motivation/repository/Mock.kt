package com.example.motivation.repository

import com.example.motivation.infra.MotivationConstants
import java.util.*


/*
    Mock é um termo da área de tester usado para indicar objetos que simulam outros objetos,
    como essa nossa classe no pacote repository que vai servir pra simular um banco de dados
 */

data class Phrase(val description: String, val category: Int)

class Mock{
    private val ALL = MotivationConstants.PHRASE_FILTER.ALL
    private val HAPPY = MotivationConstants.PHRASE_FILTER.HAPPY
    private val MORNING = MotivationConstants.PHRASE_FILTER.MORNING

    //instancia a lista de frases
    private val mListPhrase = listOf<Phrase>(
            Phrase("Não sabendo que era impossível, foi lá e fez.", HAPPY),
            Phrase("Você não é derrotado quando perde, você é derrotado quando desiste!", HAPPY),
            Phrase("Quando está mais escuro, vemos mais estrelas!", HAPPY),
            Phrase("Insanidade é fazer sempre a mesma coisa e esperar um resultado diferente.", HAPPY),
            Phrase("Não pare quando estiver cansado, pare quando tiver terminado.", HAPPY),
            Phrase("O que você pode fazer agora que tem o maior impacto sobre o seu sucesso?", HAPPY),
            Phrase("A melhor maneira de prever o futuro é inventá-lo.", MORNING),
            Phrase("Você perde todas as chances que você não aproveita.", MORNING),
            Phrase("Fracasso é o condimento que dá sabor ao sucesso.", MORNING),
            Phrase("Enquanto não estivermos comprometidos, haverá hesitação!", MORNING),
            Phrase("Se você não sabe onde quer ir, qualquer caminho serve.", MORNING),
            Phrase("Se você acredita, faz toda a diferença.", MORNING),
            Phrase("Riscos devem ser corridos, porque o maior perigo é não arriscar nada!", MORNING)
    )

    fun getPhrase(categoryId: Int): String{

        //se a categoria for ALL, o filtro vai ser sempre TRUE, então retornará a mesma lista original
        val filteredList = mListPhrase.filter { (it.category == categoryId || categoryId == ALL) }

        //o nextInt não é inclusivo, logo ele vai pegar de 0 até o tamanho da lista menos 1
        val rand = Random().nextInt(filteredList.size)
        return filteredList[rand].description //retornara a frase na posição rand
    }

}