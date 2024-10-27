package com.example.data.repoimpl

import android.content.Context
import com.example.domain.repository.QuranContentRepo
import javax.inject.Inject

class QuranContentRepoImpl @Inject constructor(private val context: Context) : QuranContentRepo {

    override suspend fun loadQuran(fileName: String, index: Int): List<String> {

        val quranContent = context.assets.open("$index.txt").bufferedReader().use {
            it.readText()
        }
        val lines = quranContent.trim().split("\n")
        return lines
    }
}