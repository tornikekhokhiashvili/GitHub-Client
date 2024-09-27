package com.example.githubclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.githubclient.model.ListOfOrg
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController, startDestination = "MainScreen"
            ) {
                composable("MainScreen") {
                    MainScreen(navController)
                }
                composable("SecondScreen/{orgName}/{orgDetail}",
                    arguments = listOf(navArgument("orgName") { type = NavType.StringType },
                        navArgument("orgDetail") { type = NavType.StringType })) { backStackEntry ->
                    val orgName = backStackEntry.arguments?.getString("orgName")
                    val orgDetail = backStackEntry.arguments?.getString("orgDetail")
                    SecondScreen(orgName.orEmpty(), orgDetail.orEmpty())
                }
            }
        }
    }

    @Composable
    fun MainScreen(navController: NavController) {
        val repository: OrgsRepository by lazy { OrgsRepository() }
        val coroutineScope = rememberCoroutineScope()
        val orgName = remember { mutableStateOf("") }
        val orgList = remember { mutableStateOf(emptyList<ListOfOrg>()) }
        Column {
            Row {
                TextField(value = orgName.value,
                    onValueChange = { orgName.value = it },
                    label = { Text(text = "Organization Name") })
                Button(onClick = {
                    if (orgName.value.isNotEmpty()) {
                        coroutineScope.launch {
                            val result = repository.getOrg(orgName.value)
                            if (result.isNotEmpty()) {
                                orgList.value = result
                            }
                        }
                    }
                }) {
                    Text(text = "SEARCH")
                }
            }
            OrgList(orgList.value, navController, orgName)
        }
    }

    @Composable
    fun OrgList(
        orglist: List<ListOfOrg>, navController: NavController, orgName: MutableState<String>
    ) {
        LazyColumn {
            items(orglist) { orgInfo ->
                OrgItem(orgInfo = orgInfo) {
                    navController.navigate("SecondScreen/${orgName.value}/${orgInfo.name}")
                }
                HorizontalDivider()
            }
        }
    }

    @Composable
    fun OrgItem(
        orgInfo: ListOfOrg, onItemClick: () -> Unit
    ) {
        Column(modifier = Modifier
            .padding(16.dp)
            .clickable { onItemClick() }) {
            Text(text = orgInfo.name)
            Text(text = orgInfo.description ?: "No description available")
        }
    }
}