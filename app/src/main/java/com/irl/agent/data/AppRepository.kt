class AppRepository(private val dao: AppLimitDao) {
    val allLimits: kotlinx.coroutines.flow.Flow<List<AppLimit>> = dao.getAll()

    suspend fun insert(limit: AppLimit) = dao.insert(limit)

    suspend fun delete(limit: AppLimit) = dao.delete(limit)
}