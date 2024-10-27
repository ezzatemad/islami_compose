package com.example.islamicompose

import com.example.domain.repository.GetRadioRepo
import com.example.domain.repository.HadethContentRepo
import com.example.domain.repository.QuranContentRepo
import com.example.domain.usecase.GetRadioUseCase
import com.example.domain.usecase.HadethContentUseCase
import com.example.domain.usecase.QuranContentUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {


    @Provides
    fun provideRadioUseCase(getRadioRepo: GetRadioRepo): GetRadioUseCase {
        return GetRadioUseCase(getRadioRepo)
    }


    @Provides
    fun provideQuranContentUseCase(quranContentRepo: QuranContentRepo): QuranContentUseCase {
        return QuranContentUseCase(quranContentRepo)
    }

    @Provides
    fun provideHadethContentUseCase(hadethContentRepo: HadethContentRepo): HadethContentUseCase {
        return HadethContentUseCase(hadethContentRepo)
    }
}