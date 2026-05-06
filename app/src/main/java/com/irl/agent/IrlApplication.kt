import android.app.Application
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.irl.agent.worker.UsageWorker
import java.util.concurrent.TimeUnit

class IrlApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val workRequest = PeriodicWorkRequestBuilder<UsageWorker>(15, TimeUnit.MINUTES).build()
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "usage_check",
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )
    }
}