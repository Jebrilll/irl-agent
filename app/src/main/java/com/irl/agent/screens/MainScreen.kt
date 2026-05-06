import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.irl.agent.MainViewModel
import com.irl.agent.data.AppLimit

@Composable
fun MainScreen(viewModel: MainViewModel, navController: NavController) {
    val limits by viewModel.limits.observeAsState(emptyList())
    var showPicker by remember { mutableStateOf(false) }

    if (showPicker) {
        AppPickerScreen { packageName, appName ->
            viewModel.addLimit(AppLimit(packageName, appName, 30))
            showPicker = false
        }
    } else {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text("irl agent") })
            },
            bottomBar = {
                BottomAppBar {
                    BottomAppBarItem(
                        icon = { Icon(Icons.Default.Home, contentDescription = null) },
                        label = { Text("Limits") },
                        selected = true,
                        onClick = { }
                    )
                    BottomAppBarItem(
                        icon = { Icon(Icons.Default.Info, contentDescription = null) },
                        label = { Text("Wellbeing") },
                        selected = false,
                        onClick = { navController.navigate("wellbeing") }
                    )
                    BottomAppBarItem(
                        icon = { Icon(Icons.Default.Settings, contentDescription = null) },
                        label = { Text("About") },
                        selected = false,
                        onClick = { navController.navigate("about") }
                    )
                }
            }
        ) { padding ->
            Column(modifier = Modifier.padding(padding)) {
                Text("App Limits")
                LazyColumn {
                    items(limits) { limit ->
                        Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(limit.appName)
                                Text("${limit.currentUsageMinutes}/${limit.dailyLimitMinutes} min")
                                LinearProgressIndicator(
                                    progress = limit.currentUsageMinutes.toFloat() / limit.dailyLimitMinutes
                                )
                            }
                        }
                    }
                }
                Button(onClick = { showPicker = true }) {
                    Text("Add App")
                }
            }
        }
    }
}