package com.example.domain.usecase


import com.example.domain.model.radio.RadioData
import com.example.domain.repository.GetRadioRepo
import javax.inject.Inject

class GetRadioUseCase @Inject constructor(private val getRadioRepo: GetRadioRepo) {

    suspend fun getRadio(): RadioData {
        return getRadioRepo.getRadio()
    }
}