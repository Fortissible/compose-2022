package com.example.composechampions2k22submission.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.example.composechampions2k22submission.core.domain.usecase.Interactor
import com.example.composechampions2k22submission.core.domain.usecase.UseCase

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun provideUseCase(interactor: Interactor) : UseCase
}