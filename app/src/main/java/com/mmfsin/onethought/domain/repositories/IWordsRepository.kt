package com.mmfsin.onethought.domain.repositories

import com.mmfsin.onethought.domain.models.Words

interface IWordsRepository {
    suspend fun getAdjectives(): List<Words>
}