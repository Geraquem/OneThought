package com.mmfsin.onethought.domain.usecases

import com.mmfsin.onethought.base.BaseUseCase
import com.mmfsin.onethought.domain.repositories.IWordsRepository
import javax.inject.Inject

class GetAdjectivesUseCase @Inject constructor(
    private val repository: IWordsRepository
) : BaseUseCase<Unit>() {

    override suspend fun execute() {
        repository.getAdjectives()
    }
}