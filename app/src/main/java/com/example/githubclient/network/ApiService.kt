package com.example.githubclient.network

import com.example.githubclient.model.DetailOfOrg
import com.example.githubclient.model.ListOfOrg
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://api.github.com/"
interface ApiService{
    @GET("orgs/{orgName}/repos")
    suspend fun getInfo(@Path("orgName") orgName: String):List<ListOfOrg>
    @GET("repos/{orgName}/{orgDetail}")
    suspend fun getDetail(@Path("orgName")orgName: String,@Path("orgDetail")orgDetail:String):DetailOfOrg
}
private val retrofit=Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

object OrgApi{
    val retrofitService:ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}