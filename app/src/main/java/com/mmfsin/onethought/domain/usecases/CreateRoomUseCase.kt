package com.mmfsin.onethought.domain.usecases

import com.mmfsin.onethought.domain.repositories.IRoomsRepository
import javax.inject.Inject

class CreateRoomUseCase @Inject constructor(
    private val repository: IRoomsRepository
) {
    suspend fun execute(roomName: String) = repository.createRoom(roomName)
}