package com.example.data.repoimpl

import com.example.data.apiserivces.RadioApi
import com.example.domain.model.radio.RadioData
import com.example.domain.repository.GetRadioRepo
import javax.inject.Inject

class GetRadioRepoImpl @Inject constructor(private val radioApi: RadioApi) : GetRadioRepo {
    override suspend fun getRadio(): RadioData {
        return radioApi.getRadio()
    }

}