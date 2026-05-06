import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.TextButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import android.content.pm.PackageManager

@Composable
fun AppPickerScreen(onAppSelected: (String, String) -> Unit) {
    val context = LocalContext.current
    val apps = remember {
        val pm = context.packageManager
        pm.getInstalledApplications(PackageManager.GET_META_DATA).map {
            it.packageName to pm.getApplicationLabel(it).toString()
        }.filter { it.second.isNotEmpty() }
    }
    LazyColumn {
        items(apps) { (packageName, appName) ->
            TextButton(onClick = { onAppSelected(packageName, appName) }) {
                Text(appName)
            }
        }
    }
}