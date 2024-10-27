package com.example.domain.usecase

import com.example.domain.repository.HadethContentRepo
import com.example.domain.repository.QuranContentRepo
import javax.inject.Inject

class HadethContentUseCase @Inject constructor(
    val hadethContentRepo: HadethContentRepo
) {
    suspend fun readHadethContent(fileName: String, index: Int): List<String> {
        return hadethContentRepo.hadethFiles(fileName, index)
    }
}