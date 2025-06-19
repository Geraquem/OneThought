package com.mmfsin.onethought.domain.repositories

interface IRoomsRepository {
    suspend fun createRoom(roomName: String)
}