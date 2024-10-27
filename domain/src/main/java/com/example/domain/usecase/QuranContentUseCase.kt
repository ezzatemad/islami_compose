package com.example.domain.usecase

import com.example.domain.repository.QuranContentRepo
import javax.inject.Inject

class QuranContentUseCase @Inject constructor(
    val quranNamesRepo: QuranContentRepo
) {
    suspend fun readQuranContent(fileName: String, index: Int): List<String> {
        return quranNamesRepo.loadQuran(fileName, index)
    }
}