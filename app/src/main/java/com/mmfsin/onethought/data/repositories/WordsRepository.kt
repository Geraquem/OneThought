package com.mmfsin.onethought.data.repositories

import com.mmfsin.onethought.domain.repositories.IWordsRepository
import javax.inject.Inject

class WordsRepository @Inject constructor() : IWordsRepository {

    override fun getAdjectives(): List<String> {

        println("***********************************************************************************")
        println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee")
        println("***********************************************************************************")

        return emptyList()
    }
}