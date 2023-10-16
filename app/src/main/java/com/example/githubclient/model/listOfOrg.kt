package com.example.githubclient.model

import com.google.gson.annotations.SerializedName

data class listOfOrg(
    @SerializedName("name") var name :String,
    @SerializedName("description")var description:String
)
