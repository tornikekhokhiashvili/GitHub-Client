package com.example.githubclient.network

import com.example.githubclient.model.detailOfOrg
import com.example.githubclient.model.listOfOrg
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path

//private const val BASE_URL="https://api.github.com/orgs/square/repos"
private const val BASE_URL = "https://api.github.com/"
interface ApiService{
    @GET("orgs/{orgName}/repos")
    suspend fun getInfo(@Path("orgName") orgName: String):List<listOfOrg>
//    @GET("repos/{orgName}/{Name}")
//    suspend fun getDetail(@Path("orgName")orgName: String,@Path("Name")Name:String):List<detailOfOrg>
//    @GET("repos/square/{Name}")
//    suspend fun getDetail(@Path("Name")Name:String):List<detailOfOrg>
    @GET("repos/square/plastic")
    suspend fun getDetail():List<detailOfOrg>
}
private val retrofit=Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

object orgApi{
    val retrofitService:ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}