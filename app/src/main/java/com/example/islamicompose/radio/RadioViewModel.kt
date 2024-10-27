package com.example.islamicompose.radio

import android.media.MediaPlayer
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetRadioUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RadioViewModel @Inject constructor(
    private val getRadioUseCase: GetRadioUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(RadioState())
    val state: StateFlow<RadioState> = _state.asStateFlow()


    private var mediaPlayer: MediaPlayer? = null

    private var currentUrl: String? = null // Keep track of the current playing URL
    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying.asStateFlow()


    fun processIntent(intent: RadioIntent) {
        when (intent) {
            is RadioIntent.FetchRadios -> fetchRadios()
            is RadioIntent.PlayRadio -> handlePlayRadio(intent.url)
            RadioIntent.StopRadio -> handleStopRadio()
            RadioIntent.MoveToPrevious -> moveToPrevious()
            RadioIntent.MoveToNext -> moveToNext()
        }
    }

    private fun moveToPrevious() {
        _state.value = _state.value.copy(
            currentRadioIndex = (_state.value.currentRadioIndex - 1 + _state.value.radios.size) % _state.value.radios.size
        )
    }

    private fun moveToNext() {
        _state.value = _state.value.copy(
            currentRadioIndex = (_state.value.currentRadioIndex + 1) % _state.value.radios.size
        )
    }


    private fun handlePlayRadio(url: String) {
        if (_state.value.isPlaying && _state.value.currentUrl == url) {
            handleStopRadio()
        } else {
            playRadio(url)
        }
    }

    private fun playRadio(url: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mediaPlayer?.release()

                mediaPlayer = MediaPlayer().apply {
                    setDataSource(url)
                    setOnPreparedListener { player ->
                        player.start()
                        _state.value = _state.value.copy(isPlaying = true, currentUrl = url)
                    }
                    setOnCompletionListener {
                        _state.value = _state.value.copy(isPlaying = false, currentUrl = null)
                    }
                    prepareAsync()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun handleStopRadio() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
        _state.value = _state.value.copy(isPlaying = false, currentUrl = null)
    }


    private fun fetchRadios() {
        _state.value = RadioState(isLoading = true)
        viewModelScope.launch {
            try {
                val radios = getRadioUseCase.getRadio()
                _state.value = RadioState(radios = radios.radios)
            } catch (e: Exception) {
                Log.d("TAG", "fetchRadios: ${e.localizedMessage}")
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}