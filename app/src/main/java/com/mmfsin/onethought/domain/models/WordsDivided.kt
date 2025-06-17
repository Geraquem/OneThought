package com.mmfsin.onethought.domain.models

data class WordsDivided(
    val topWords: List<Words>,
    val bottomWords: List<Words>
)

data class Words(
    val word: String
)