package com.mmfsin.onethought.data.repositories

import com.google.firebase.functions.FirebaseFunctions
import com.mmfsin.onethought.domain.repositories.IRoomsRepository
import javax.inject.Inject

class RoomsRepository @Inject constructor() : IRoomsRepository {

    override suspend fun createRoom(roomName: String) {

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
    }
}