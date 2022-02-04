package com.ssafy.near.api

import com.ssafy.near.dto.HandSignInfo
import com.ssafy.near.dto.Model
import com.ssafy.near.dto.ModelList
import org.jetbrains.annotations.NotNull
import retrofit2.Response
import retrofit2.http.*

interface HandSignApi {
    @GET("api/hand/")
    suspend fun loadHandSignList(): Response<ModelList<HandSignInfo>>

    @GET("api/hand/bookmark/{id}")
    suspend fun loadBookmarkList(@Path("id") @NotNull id: String): Response<ModelList<HandSignInfo>>

    @FormUrlEncoded
    @POST("api/hand/bookmark/add")
    suspend fun addBookmark(@FieldMap req: MutableMap<String, String>): Response<Model<Boolean?>>

    @FormUrlEncoded
    @DELETE("api/hand/bookmark/delete")
    suspend fun deleteBookmark(@FieldMap req: MutableMap<String, String>): Response<Model<Boolean?>>
}