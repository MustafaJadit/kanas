package com.kodyuzz.kanas.data.remote.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PostCreationRequest(
    @Expose
    @SerializedName("imgUrl")
    var imageUrl:String,

    @Expose
    @SerializedName("imgWidth")
    var imgWidth:Int,

    @Expose
    @SerializedName("imgHeight")
    var imgHeight:Int
)