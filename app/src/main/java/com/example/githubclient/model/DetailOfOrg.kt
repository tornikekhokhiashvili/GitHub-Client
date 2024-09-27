package com.example.githubclient.model

import com.google.gson.annotations.SerializedName

data class DetailOfOrg(
    @SerializedName("name") var name :String = "",
    @SerializedName("full_name") var fullName:String = "",
    @SerializedName("forks") var forks:Int = 0,
    @SerializedName("watchers_count") var watchers:Int = 0,
    @SerializedName("open_issues_count") var issues:Int = 0,
    @SerializedName("description") var description:String = ""
)
