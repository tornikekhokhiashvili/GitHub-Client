import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.githubclient.OrgsRepository
import com.example.githubclient.model.DetailOfOrg

@Composable
fun SecondScreen(orgName: String, orgInfo: String) {
    val detailList = remember { mutableStateOf(DetailOfOrg()) }
    val repository = OrgsRepository()
    val context = LocalContext.current
    LaunchedEffect(orgName, orgInfo) {
        try {
            val result = repository.getDetail(orgName, orgInfo)
            detailList.value = result
        } catch (e: Exception) {
            Toast.makeText(context, "Cannot load repository details", Toast.LENGTH_LONG).show()
        }
    }
    detailList.value.let { detail ->
        Column(modifier = Modifier.padding(16.dp)) {
            DetailItem(detail)
        }
    }
}

@Composable
fun DetailItem(orgDetail: DetailOfOrg) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = orgDetail.name)
        Text(text = "Parent:${orgDetail.fullName}")
        Text(text = "Forks:${orgDetail.forks}")
        Text(text = "Watchers:${orgDetail.watchers}")
        Text(text = "Issues:${orgDetail.issues}")
        Text(text = "Description:${orgDetail.description}")
    }
}