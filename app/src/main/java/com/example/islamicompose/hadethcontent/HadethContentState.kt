package com.example.islamicompose.hadethcontent

data class HadethContentState(

    val isLoading: Boolean = true,
    val HadethContent: List<String> = emptyList(),
    val error: String? = null
)