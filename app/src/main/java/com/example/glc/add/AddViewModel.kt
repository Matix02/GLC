package com.example.glc.add

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.glc.add.model.Game
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

private const val TAG = "AddRepositoryAction"

class AddViewModel @Inject constructor(private val twichRepository: TwichRepository) : ViewModel() {

    var addGameList = MutableLiveData<NetworkResult<Game>>()

    suspend fun searchGame(title: String) {
        viewModelScope.launch() {
            addGameList.value = NetworkResult.Loading()
            /*sprawdzić czy jest interent
            if (hasInternetConnection)*/
            try {
                val response = twichRepository.searchGames(title)
                if (response.isSuccessful) {
                    addGameList.value = response.body()?.let { NetworkResult.Success(it) }
                    Log.d(TAG, "In Try block = ${response.body()}")
                } else {
                    addGameList.value = NetworkResult.Error(response.message())
                }
            } catch (error: Exception) {
                addGameList.value = NetworkResult.Error(error.message)
                Log.e(TAG, "Exception = $error")
            }
        }
    }
}
