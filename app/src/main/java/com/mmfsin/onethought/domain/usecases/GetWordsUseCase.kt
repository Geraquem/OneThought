package com.mmfsin.onethought.domain.usecases

import com.mmfsin.onethought.domain.models.Words
import com.mmfsin.onethought.domain.repositories.IWordsRepository
import javax.inject.Inject

class GetWordsUseCase @Inject constructor(
    private val repository: IWordsRepository
) {
    suspend fun execute(): List<Words> = repository.getWordsFromFirebase()
}