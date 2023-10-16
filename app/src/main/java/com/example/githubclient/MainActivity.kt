package com.example.githubclient

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.githubclient.model.listOfOrg
import com.example.githubclient.ui.theme.GitHubClientTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository: orgsRepository by lazy { orgsRepository() }
        setContent {
            GitHubClientTheme {
                Greeting()
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Greeting() {
        val repository: orgsRepository by lazy { orgsRepository() }
        val coroutineScope = rememberCoroutineScope()
        var orgName by remember { mutableStateOf(TextFieldValue()) }
        val orgList = remember { mutableStateOf(emptyList<listOfOrg>()) }
        Column {
            Row {
                TextField(value = orgName,
                    onValueChange = { orgName = it },
                    label = { Text(text = "Organization Name") })
                Button(onClick = {
                    if (orgName.text.isNotEmpty()) {
                        coroutineScope.launch {
                            val result = repository.getOrg(orgName.text)
                            if (result.isNotEmpty()) {
                                orgList.value = result
                            }
                        }
                    }
                }
                ) {
                    Text(text = "SEARCH")
                }
            }
            OrgList(orgList.value)
        }

    }

//    @Composable
//    fun OrgListScreen(orgList: List<listOfOrg>) {
//        val navController = rememberNavController()
//        OrgList(orgList, onItemClick = { orgInfo ->
//            navController.navigate("orgDetailScreen/${orgInfo.name}?orgName")
//        })
//    }

//    onItemClick: (listOfOrg) -> Unit
//    , onItemClick = onItemClick
    @Composable
    fun OrgList(orglist: List<listOfOrg>) {
        LazyColumn {
            items(orglist) { orgInfo ->
                OrgItem(orgInfo = orgInfo)
                Divider()
            }
        }
    }

//    , onItemClick: (listOfOrg) -> Unit
    @Composable
    fun OrgItem(orgInfo: listOfOrg) {
        Column(
            modifier = Modifier
                .padding(16.dp)
//                .clickable { onItemClick(orgInfo) }
        ) {
            Text(text = orgInfo.name)
            Text(text = orgInfo.description ?: "No description available")
        }
    }



    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        GitHubClientTheme {
            Greeting()
        }
    }
}