package com.ssafy.near.api

import com.ssafy.near.dto.HandSignInfo
import com.ssafy.near.dto.Model
import com.ssafy.near.dto.ModelList
import org.jetbrains.annotations.NotNull
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HandSignApi {
    @GET("api/hand/")
    suspend fun loadHandSignList(): Response<ModelList<HandSignInfo>>

    @GET("api/hand/bookmark")
    suspend fun loadBookmarkList(@Query("id") @NotNull id: String): Response<List<HandSignInfo>>

    @GET("api/hand/add")
    suspend fun addBookmark(
        @Query("handcontent_key") @NotNull handcontent_key: String,
        @Query("id") @NotNull id: String,
    ): Response<Model<Boolean?>>

    @GET("api/hand/delete")
    suspend fun deleteBookmark(
        @Query("handcontent_key") @NotNull handcontent_key: String,
        @Query("id") @NotNull id: String,
    ): Response<Model<Boolean?>>
}