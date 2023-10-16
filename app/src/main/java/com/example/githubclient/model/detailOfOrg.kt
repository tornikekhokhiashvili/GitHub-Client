package com.example.githubclient.model

import com.google.gson.annotations.SerializedName

data class detailOfOrg(
    @SerializedName("name") var name :String,
    @SerializedName("full_name") var fullName:String,
    @SerializedName("forks") var forks:Int,
    @SerializedName("watchers_count") var watchers:Int,
    @SerializedName("open_issues_count") var issues:Int,
    @SerializedName("description") var description:String
)
