package com.codepath.recyclerviewlab.models

import com.google.gson.annotations.SerializedName

class ArticlesResponse {
    @JvmField @SerializedName("docs")
    var docs: List<Article>? = null
}