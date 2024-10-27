package com.example.islamicompose.qurencontent

data class QuranContentState(

    val isLoading: Boolean = true,
    val QuranContent: List<String> = emptyList(),
    val error: String? = null
)