package com.codepath.recyclerviewlab.models

import com.google.gson.annotations.SerializedName

class ArticleHeadline {
    @SerializedName("main")
    var main: String? = null

    @SerializedName("print_headline")
    var printHeadline: String? = null
}