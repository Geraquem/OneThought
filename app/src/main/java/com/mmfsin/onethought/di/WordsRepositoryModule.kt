package com.mmfsin.onethought.di

import com.mmfsin.onethought.data.repositories.WordsRepository
import com.mmfsin.onethought.domain.repositories.IWordsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface WordsRepositoryModule {
    @Binds
    fun bind(repository: WordsRepository): IWordsRepository
}