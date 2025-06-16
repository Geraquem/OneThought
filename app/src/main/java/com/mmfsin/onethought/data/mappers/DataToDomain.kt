package com.mmfsin.onethought.data.mappers

import com.mmfsin.onethought.data.models.WordsDTO
import com.mmfsin.onethought.domain.models.Words

fun WordsDTO.toWords(): Words = Words(word = word)

fun List<WordsDTO>.toWordsList(): List<Words> = this.map { it.toWords() }