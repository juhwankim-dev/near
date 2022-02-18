package com.ssafy.near.api

import com.ssafy.near.dto.Model
import com.ssafy.near.dto.ModelList
import com.ssafy.near.dto.RoomInfo
import retrofit2.Response
import retrofit2.http.*

interface GameApi {
    @FormUrlEncoded
    @POST("api/game/room")
    suspend fun createRoom(@FieldMap req: MutableMap<String, String>): Response<RoomInfo>

    @GET("api/game/rooms")
    suspend fun loadRooms(): Response<ModelList<RoomInfo>>

    @GET("api/game/room/{roomId}")
    suspend fun loadRoom(@Path("roomId") roomId: String): Response<Model<RoomInfo>>

    @DELETE("api/game/room/{roomId}")
    suspend fun deleteRoom(@Path("roomId") roomId: String): Response<Model<Boolean>>
}