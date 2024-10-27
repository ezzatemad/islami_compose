package com.example.islamicompose.hadethcontent

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.HadethContentUseCase
import com.example.domain.usecase.QuranContentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HadethContentViewModel @Inject constructor(
    private val hadethContentUseCase: HadethContentUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HadethContentState())
    val state: StateFlow<HadethContentState> = _state


    fun handleIntent(intent: HadethContentIntent) {
        when (intent) {
            is HadethContentIntent.LoadContent -> loadHadethFile(intent.suraName, intent.suraIndex)
        }
    }

    fun loadHadethFile(fileName: String, index: Int) {
        viewModelScope.launch {
            _state.value = HadethContentState(isLoading = true)
            try {
                val result = hadethContentUseCase.readHadethContent(fileName, index)
                _state.value = HadethContentState(HadethContent = result)
            } catch (ex: Exception) {
                _state.value = HadethContentState(error = ex.message)
            }
        }
    }
}