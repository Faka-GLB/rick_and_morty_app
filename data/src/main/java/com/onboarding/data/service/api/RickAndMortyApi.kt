package com.onboarding.data.service.api

import com.onboarding.data.service.response.CharacterBaseResponse
import com.onboarding.data.service.response.EpisodeBaseResponse
import retrofit2.Call
import retrofit2.http.GET

interface RickAndMortyApi {
    @GET("character")
    fun callAllCharacters(): Call<CharacterBaseResponse>
    @GET("episode")
    fun callAllEpisodes(): Call<EpisodeBaseResponse>
}
