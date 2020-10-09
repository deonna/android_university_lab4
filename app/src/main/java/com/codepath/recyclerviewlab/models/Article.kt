package com.codepath.recyclerviewlab.models

import com.google.gson.annotations.SerializedName

class Article {
    @SerializedName("id")
    var id: String? = null

    @SerializedName("headline")
    var headline: ArticleHeadline? = null

    @SerializedName("web_url")
    var webUrl: String? = null

    @SerializedName("multimedia")
    var multimedia: List<Multimedia>? = null

    @SerializedName("snippet")
    var snippet: String? = null

    @SerializedName("word_count")
    var wordCount: String? = null

    @SerializedName("print_section")
    var printSection: String? = null

    @SerializedName("section_name")
    var sectionName: String? = null

    @SerializedName("print_page")
    var printPage: String? = null

    @SerializedName("pub_date")
    var publishDate: String? = null
}