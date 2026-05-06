import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Delete
import kotlinx.coroutines.flow.Flow

@Dao
interface AppLimitDao {
    @Query("SELECT * FROM app_limit")
    fun getAll(): Flow<List<AppLimit>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(appLimit: AppLimit)

    @Delete
    suspend fun delete(appLimit: AppLimit)
}