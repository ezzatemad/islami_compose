package com.example.islamicompose.radio

import com.example.domain.model.radio.Radio

data class RadioState(
    val radios: List<Radio> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val isPlaying: Boolean = false,
    val currentUrl: String? = null,
    val currentRadioIndex: Int = 0

)
