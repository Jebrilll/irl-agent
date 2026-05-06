import android.app.usage.UsageStatsManager
import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.flow.first

class UsageWorker(context: Context, workerParams: WorkerParameters) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        val database = AppDatabase.getDatabase(applicationContext)
        val dao = database.appLimitDao()
        val limits = dao.getAll().first()
        val usageStatsManager = applicationContext.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        val endTime = System.currentTimeMillis()
        val beginTime = endTime - 24 * 60 * 60 * 1000
        val stats = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, beginTime, endTime)
        for (limit in limits) {
            val usage = stats.find { it.packageName == limit.packageName }?.totalTimeInForeground ?: 0L
            val minutes = (usage / 60000).toInt()
            if (minutes != limit.currentUsageMinutes) {
                dao.insert(limit.copy(currentUsageMinutes = minutes))
            }
            if (minutes >= limit.dailyLimitMinutes) {
                NotificationHelper(applicationContext).sendLimitExceededNotification(limit.appName)
                val intent = android.content.Intent(applicationContext, com.irl.agent.service.OverlayService::class.java)
                applicationContext.startService(intent)
            }
        }
        return Result.success()
    }
}