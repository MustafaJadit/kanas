package com.kodyuzz.kanas.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.kodyuzz.kanas.data.model.Dummy

data class DummyResponse(
    @Expose
    @SerializedName("statusCode")
    var statusCode:String,

    @Expose
    @SerializedName("message")
    var message:String,

    @Expose
    @SerializedName("data")
    val data:List<Dummy>
)