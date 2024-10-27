package com.example.islamicompose.qurencontent

sealed class QuranContentIntent {

    data class LoadContent(val suraName: String, val suraIndex: Int) : QuranContentIntent()
}