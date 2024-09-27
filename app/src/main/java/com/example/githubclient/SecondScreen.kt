package com.example.githubclient

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.githubclient.model.DetailOfOrg

@Composable
fun SecondScreen(orgName: String,orgInfo: String) {
    val detailList = remember { mutableStateOf(DetailOfOrg()) }
    val repository = OrgsRepository()

    LaunchedEffect(orgName,orgInfo){
        val result=repository.getDetail(orgName,orgInfo)
        detailList.value=result
    }
    DetailItem(detailList.value)
}

@Composable
fun DetailItem(orgDetail: DetailOfOrg) {
    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Text(text = orgDetail.name )
        Text(text = orgDetail.fullName)
        Text(text = orgDetail.forks.toString())
        Text(text = orgDetail.watchers.toString())
        Text(text = orgDetail.issues.toString())
        Text(text = orgDetail.description)
    }
}