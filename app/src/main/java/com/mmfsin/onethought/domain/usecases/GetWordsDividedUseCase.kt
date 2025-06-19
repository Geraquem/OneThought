package com.mmfsin.onethought.domain.usecases

import com.mmfsin.onethought.domain.models.WordsDivided
import com.mmfsin.onethought.domain.repositories.IWordsRepository
import javax.inject.Inject

class GetWordsDividedUseCase @Inject constructor(
    private val repository: IWordsRepository
) {
    suspend fun execute(): WordsDivided = repository.getWordsFromBBDD()
}