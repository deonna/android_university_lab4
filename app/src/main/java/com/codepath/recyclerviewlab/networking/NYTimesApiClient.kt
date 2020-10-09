package com.codepath.recyclerviewlab.networking

import com.codepath.recyclerviewlab.models.Article
import com.codepath.recyclerviewlab.models.NYTimesArticlesAPIResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * This code represents the networking layer of the application,
 * Other than updating the API key, you will NOT need to touch this code for the lab,
 * However it may be useful to reference the logic for your future projects
 *
 *
 * IMPORTANT INSTRUCTIONS BELOW ===========================================================
 * TODO: You have to update API_KEY variable with your own NY-Times developer api key, see
 * https://developer.nytimes.com/get-started to create your own developer account,
 * after copy and paste the API key under your Account -> Apps -> <Your App> -> API Keys
 *
 * You will also need to authorize "Article Search API" in your developer App
</Your> */
class NYTimesApiClient {
    private val nyTimesService: NYTimesService

    /**
     * gets the articles given a specific query, default page number to 0
     * @param articlesListResponse
     * @param query
     */
    fun getArticlesByQuery(
        articlesListResponse: CallbackResponse<List<Article?>?>,
        query: String?
    ) {
        getArticlesByQuery(articlesListResponse, query, 0)
    }

    /**
     * gets the articles given a specific query and page number
     * @param articlesListResponse
     * @param query
     * @param pageNumber
     */
    fun getArticlesByQuery(
        articlesListResponse: CallbackResponse<List<Article?>?>,
        query: String?,
        pageNumber: Int
    ) {
        // this hard codes to only get the articles sorted by "relevance" sort order
        // you can actually alter the api query to have more search filters or change the sort order to search by "newest"
        // see https://developer.nytimes.com/docs/articlesearch-product/1/routes/articlesearch.json/get for more information on API documentation
        val current = nyTimesService.getArticlesByQuery(
            query,
            pageNumber,
            SORT_BY,
            API_FILTER,
            BEGIN_DATE,
            API_KEY
        )

        current.enqueue(object : Callback<NYTimesArticlesAPIResponse> {
            override fun onResponse(
                call: Call<NYTimesArticlesAPIResponse>,
                response: Response<NYTimesArticlesAPIResponse>
            ) {
                val articlesResponse = response.body().response
                if (response.isSuccessful && articlesResponse != null) {
                    articlesListResponse.onSuccess(articlesResponse.docs)
                } else {
                    articlesListResponse.onFailure(Throwable("error with response code " + response.code() + " " + response.message()))
                }
            }

            override fun onFailure(call: Call<NYTimesArticlesAPIResponse>, t: Throwable) {
                articlesListResponse.onFailure(t)
            }
        })
    }

    companion object {
        // TODO: Replace the below API key with your own generated key
        private const val API_KEY = "<YOUR-API-KEY-GOES-HERE>"

        // returns the
        private const val API_FILTER =
            "headline, web_url, snippet, pub_date, word_count, print_page, print_section, section_name"
        private const val BEGIN_DATE = "20100101"
        private const val SORT_BY = "relevance"
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.nytimes.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        nyTimesService = retrofit.create(NYTimesService::class.java)
    }
}