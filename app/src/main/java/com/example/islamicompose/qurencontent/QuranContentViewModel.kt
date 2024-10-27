package com.example.islamicompose.qurencontent

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.QuranContentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuranContentViewModel @Inject constructor(
    private val quranContentUseCase: QuranContentUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(QuranContentState())
    val state: StateFlow<QuranContentState> = _state



    fun handleIntent(intent: QuranContentIntent) {
        when (intent) {
            is QuranContentIntent.LoadContent -> loadJsonFile(intent.suraName, intent.suraIndex)
        }
    }

    fun loadJsonFile(fileName: String, index: Int) {
        viewModelScope.launch {
            _state.value = QuranContentState(isLoading = true)
            try {
                val result = quranContentUseCase.readQuranContent(fileName, index)
                _state.value = QuranContentState(QuranContent = result)
            } catch (ex: Exception) {
                _state.value = QuranContentState(error = ex.message)
            }
        }
    }
}