package dev.suhrob.kattabozor.data.remote

import dev.suhrob.kattabozor.data.remote.dto.offers.MainDto
import retrofit2.Response
import retrofit2.http.GET

interface MainService {

    /************************************************** OFFERS ******************************/

    @GET("hh/test/api/v1/offers")
    suspend fun getOffers(): Response<MainDto>

}
