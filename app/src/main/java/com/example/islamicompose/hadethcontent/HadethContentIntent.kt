package com.example.islamicompose.hadethcontent

sealed class HadethContentIntent {

    data class LoadContent(val suraName: String, val suraIndex: Int) : HadethContentIntent()
}