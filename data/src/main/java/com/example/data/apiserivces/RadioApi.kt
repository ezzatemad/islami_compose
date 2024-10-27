package com.example.data.apiserivces

import com.example.domain.model.radio.RadioData
import retrofit2.http.GET

interface RadioApi {


    @GET("radios")
    suspend fun getRadio(): RadioData

}