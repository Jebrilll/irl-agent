import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "app_limit")
data class AppLimit(
    @PrimaryKey val packageName: String,
    val appName: String,
    val dailyLimitMinutes: Int,
    val currentUsageMinutes: Int = 0,
    val lastReset: Long = System.currentTimeMillis()
)