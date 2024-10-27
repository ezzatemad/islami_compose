package com.example.islamicompose.radio


sealed class RadioIntent {
    data object FetchRadios : RadioIntent()
    data class PlayRadio(val url: String) : RadioIntent()
    data object StopRadio : RadioIntent()
    data object MoveToPrevious : RadioIntent()
    data object MoveToNext : RadioIntent()
}
