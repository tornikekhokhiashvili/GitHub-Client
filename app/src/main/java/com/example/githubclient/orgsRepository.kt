package com.example.githubclient

import com.example.githubclient.model.detailOfOrg
import com.example.githubclient.model.listOfOrg
import com.example.githubclient.network.ApiService
import com.example.githubclient.network.orgApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class orgsRepository(
    private val apiService: ApiService=orgApi.retrofitService
) {
    suspend fun getOrg(orgName: String):List<listOfOrg> = withContext(Dispatchers.IO){
        apiService.getInfo(orgName)
    }
//    suspend fun getDetail(orgName: String,orgDetail:String):List<detailOfOrg> = withContext(Dispatchers.IO){
//        apiService.getDetail(orgName,orgDetail)
//    }
    suspend fun getDetail():List<detailOfOrg> = withContext(Dispatchers.IO){
        apiService.getDetail()
}
}