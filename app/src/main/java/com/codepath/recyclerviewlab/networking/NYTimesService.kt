package com.codepath.recyclerviewlab.networking

import com.codepath.recyclerviewlab.models.NYTimesArticlesAPIResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NYTimesService {
    @GET("svc/search/v2/articlesearch.json") fun getArticlesByQuery(
        @Query("q") query: String?,
        @Query("page") page: Int,
        @Query("sort") sortBy: String?,
        @Query("fl") filter: String?,
        @Query("begin_date") beginDate: String?,
        @Query("api-key") apikey: String?
    ): Call<NYTimesArticlesAPIResponse>
}