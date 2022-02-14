package com.ssafy.near.api

import com.ssafy.near.dto.RoomInfo
import retrofit2.Response
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface GameApi {
    @FormUrlEncoded
    @POST("api/game/room")
    suspend fun createRoom(@FieldMap req: MutableMap<String, String>): Response<RoomInfo>

    @GET("api/game/rooms")
    suspend fun loadRooms(): Response<MutableList<RoomInfo>>
}