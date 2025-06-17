package com.mmfsin.onethought.domain.repositories

import com.mmfsin.onethought.domain.models.Words
import com.mmfsin.onethought.domain.models.WordsDivided

interface IWordsRepository {
    suspend fun getWordsFromFirebase(): List<Words>
    suspend fun getWordsFromBBDD(): WordsDivided
}