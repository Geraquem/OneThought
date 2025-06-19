package com.mmfsin.onethought.di

import com.mmfsin.onethought.data.repositories.RoomsRepository
import com.mmfsin.onethought.domain.repositories.IRoomsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RoomsRepositoryModule {
    @Binds
    fun bind(repository: RoomsRepository): IRoomsRepository
}