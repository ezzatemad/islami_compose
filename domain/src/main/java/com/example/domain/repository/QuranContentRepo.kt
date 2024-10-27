package com.example.domain.repository

interface QuranContentRepo {

    suspend fun loadQuran(fileName: String, index: Int): List<String>

}