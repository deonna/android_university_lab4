package com.codepath.recyclerviewlab.models

import com.google.gson.annotations.SerializedName

class NYTimesArticlesAPIResponse {
    @SerializedName("status")
    var status: String? = null

    @JvmField @SerializedName("response")
    var response: ArticlesResponse? = null
}