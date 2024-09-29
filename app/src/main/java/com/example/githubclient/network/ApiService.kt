package com.example.githubclient.network

import com.example.githubclient.model.DetailOfOrg
import com.example.githubclient.model.ListOfOrg
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://api.github.com/"
interface ApiService{
    @GET("orgs/{orgName}/repos")
    suspend fun getInfo(@Path("orgName") orgName: String):List<ListOfOrg>
    @GET("repos/{orgName}/{orgDetail}")
    suspend fun getDetail(@Path("orgName")orgName: String,@Path("orgDetail")orgDetail:String):DetailOfOrg
}
private val client = OkHttpClient.Builder()
    .connectTimeout(1, TimeUnit.SECONDS)
    .readTimeout(1, TimeUnit.SECONDS)
    .writeTimeout(1, TimeUnit.SECONDS)
    .build()
private val retrofit=Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .client(client)
    .build()

object OrgApi{
    val retrofitService:ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}