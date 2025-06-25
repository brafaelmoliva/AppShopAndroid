import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appshop.dao.DatabaseProvider
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext




class LoginViewModel(private val context: Context) : ViewModel() {
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var loginResult by mutableStateOf<Boolean?>(null) // null = no intentado, true/false resultado

    private val userDao = DatabaseProvider.getDatabase(context).userDao()

    fun login() {
        viewModelScope.launch {
            val userExists = withContext(Dispatchers.IO) {
                userDao.getUserByEmailAndPassword(email, password) != null
            }
            loginResult = userExists
        }
    }
}
