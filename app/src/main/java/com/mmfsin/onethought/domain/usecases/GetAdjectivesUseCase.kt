package com.mmfsin.onethought.domain.usecases

import com.mmfsin.onethought.domain.repositories.IWordsRepository
import javax.inject.Inject

class GetAdjectivesUseCase @Inject constructor(
    private val repository: IWordsRepository
) {
    suspend fun execute() = repository.getAdjectives()
}