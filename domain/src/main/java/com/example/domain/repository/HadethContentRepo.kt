package com.example.domain.repository

interface HadethContentRepo {

    suspend fun hadethFiles(fileName: String, index: Int): List<String>

}