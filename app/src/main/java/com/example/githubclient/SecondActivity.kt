package com.example.githubclient

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.githubclient.model.detailOfOrg
import com.example.githubclient.model.listOfOrg
import com.example.githubclient.ui.theme.GitHubClientTheme
import kotlinx.coroutines.launch

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GitHubClientTheme {
                    Greeting()
            }
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Greeting() {
    val detailList = remember { mutableStateOf(emptyList<detailOfOrg>()) }
    val repository: orgsRepository by lazy { orgsRepository() }
    val coroutineScope = rememberCoroutineScope()
    coroutineScope.launch {
        var result=repository.getDetail()
        detailList.value=result
    }
    OrgList(detaillist =detailList.value )
}
@Composable
fun OrgList(detaillist: List<detailOfOrg>) {
    LazyColumn {
        items(detaillist) { orgInfo ->
            detailItem(orgDetail = orgInfo)
            Divider()
        }
    }
}
@Composable
fun detailItem(orgDetail: detailOfOrg) {
    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Text(text = orgDetail.name )
        Text(text = orgDetail.fullName)
        Text(text = orgDetail.forks.toString())
        Text(text = orgDetail.watchers.toString())
        Text(text = orgDetail.issues.toString())
        Text(text = orgDetail.description ?: "No description available")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GitHubClientTheme {
        Greeting()
    }
}