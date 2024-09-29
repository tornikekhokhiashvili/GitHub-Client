package com.example.githubclient

import com.example.githubclient.model.DetailOfOrg
import com.example.githubclient.model.ListOfOrg
import com.example.githubclient.network.ApiService
import com.example.githubclient.network.OrgApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OrgsRepository(
    private val apiService: ApiService=OrgApi.retrofitService
) {
    suspend fun getOrg(orgName: String):List<ListOfOrg> = withContext(Dispatchers.IO){
        try {
            apiService.getInfo(orgName)
        }   catch (e:Exception){
            emptyList()
        }
    }
    suspend fun getDetail(orgName: String,orgDetail:String):DetailOfOrg = withContext(Dispatchers.IO){
            try {
                apiService.getDetail(orgName,orgDetail)
            }catch (e:Exception){
                throw e
            }
    }
}