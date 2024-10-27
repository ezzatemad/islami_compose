package com.example.domain.repository

import com.example.domain.model.radio.RadioData

interface GetRadioRepo {

    suspend fun getRadio(): RadioData
}