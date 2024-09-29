package com.example.githubclient

import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubclient.model.ListOfOrg
import kotlinx.coroutines.launch
import android.content.Context

class OrgsViewModel:ViewModel() {
    private val repository:OrgsRepository by lazy {OrgsRepository() }
   val orgList= mutableStateOf(emptyList<ListOfOrg>())
    val orgName= mutableStateOf("")
    val isLoading= mutableStateOf(false)

    fun fetchOrgList(orgName:String,context: Context){
        viewModelScope.launch {
            isLoading.value=true
            orgList.value= emptyList()
            val result=repository.getOrg(orgName)
            if (result.isNotEmpty()){
                orgList.value=result
            }else{
                Toast.makeText(context,"Cannot load repositories",Toast.LENGTH_LONG).show()
            }
            isLoading.value=false
        }
    }
}