package com.example.data.di

import com.example.data.repoimpl.GetRadioRepoImpl
import com.example.data.repoimpl.HadethContentRepoImpl
import com.example.data.repoimpl.QuranContentRepoImpl
import com.example.domain.repository.GetRadioRepo
import com.example.domain.repository.HadethContentRepo
import com.example.domain.repository.QuranContentRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRadioRepository(getRadioRepoImpl: GetRadioRepoImpl): GetRadioRepo


    @Binds
    abstract fun provideHadethContentRepository(hadethContentRepoImpl: HadethContentRepoImpl): HadethContentRepo


    @Binds
    abstract fun provideQuranContentRepository(quranContentRepoImpl: QuranContentRepoImpl): QuranContentRepo
}
