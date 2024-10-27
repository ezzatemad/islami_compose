package com.example.data.repoimpl

import android.content.Context
import com.example.domain.repository.HadethContentRepo
import javax.inject.Inject

class HadethContentRepoImpl @Inject constructor(private val context: Context) : HadethContentRepo {


    override suspend fun hadethFiles(fileName: String, index: Int): List<String> {
        val hadethContent = context.assets.open("h$index.txt").bufferedReader().use {
            it.readText()
        }
        val lines = hadethContent.trim().split("\n")

        return lines
    }
}