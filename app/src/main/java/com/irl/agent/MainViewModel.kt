import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(private val repository: AppRepository) : ViewModel() {
    val limits = repository.allLimits.asLiveData()

    fun addLimit(limit: AppLimit) = viewModelScope.launch {
        repository.insert(limit)
    }

    fun removeLimit(limit: AppLimit) = viewModelScope.launch {
        repository.delete(limit)
    }
}