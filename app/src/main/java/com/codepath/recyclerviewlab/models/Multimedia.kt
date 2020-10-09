package com.codepath.recyclerviewlab.models

import com.google.gson.annotations.SerializedName

class Multimedia {
    @SerializedName("subtype")
    var subtype: String? = null

    @SerializedName("url")
    var url: String? = null
}