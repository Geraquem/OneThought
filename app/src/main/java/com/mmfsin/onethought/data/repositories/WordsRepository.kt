package com.mmfsin.onethought.data.repositories

import com.google.firebase.database.ktx.database
import com.google.firebase.functions.FirebaseFunctions
import com.google.firebase.ktx.Firebase
import com.mmfsin.onethought.data.mappers.toWordsList
import com.mmfsin.onethought.data.models.WordsDTO
import com.mmfsin.onethought.domain.models.Words
import com.mmfsin.onethought.domain.models.WordsDivided
import com.mmfsin.onethought.domain.repositories.IWordsRepository
import com.mmfsin.onethought.utils.ADJECTIVES
import com.mmfsin.onethought.utils.WORDS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.CountDownLatch
import javax.inject.Inject

class WordsRepository @Inject constructor() : IWordsRepository {

    private val reference = Firebase.database.reference

    override suspend fun getWordsFromFirebase(): List<Words> {
        val latch = CountDownLatch(1)
        val result = mutableListOf<WordsDTO>()

        reference.child(WORDS).child(ADJECTIVES).get().addOnSuccessListener {
            for (child in it.children) {
                result.add(WordsDTO(word = child.value.toString()))
            }
            latch.countDown()
        }.addOnFailureListener {
            println("error ${it.message}")
            latch.countDown()
        }

        withContext(Dispatchers.IO) {
            latch.await()
        }

        return result.toWordsList()
    }

    override suspend fun getWordsFromBBDD(): WordsDivided {

        val functions = FirebaseFunctions.getInstance()

        val data = hashMapOf(
            "respuesta1" to "palabraUno",
            "respuesta2" to "palabraDos"
        )

        functions.getHttpsCallable("verificarCoincidencia")
            .call(data)
            .addOnSuccessListener { result ->
                val map = result.data as Map<*, *>
                val coinciden = map["coinciden"] as Boolean
                if (coinciden) {
                    val a = 2
                } else {
                    val a = 2
                }
            }
            .addOnFailureListener { e ->
                val a = 2
            }


        return WordsDivided(
            listOf(
                Words(word = "uno"),
                Words(word = "dos"),
                Words(word = "tres"),
                Words(word = "cuatro"),
                Words(word = "cinco"),
                Words(word = "seis"),
                Words(word = "siete")
            ),
            listOf(
                Words(word = "uno"),
                Words(word = "dos"),
                Words(word = "tres"),
                Words(word = "cuatro"),
                Words(word = "cinco"),
                Words(word = "seis"),
                Words(word = "siete")
            )
        )
    }
}