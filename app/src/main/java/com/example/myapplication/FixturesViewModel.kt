package com.example.myapplication

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class FixturesViewModel(private val repository: MatchRepository) : ViewModel() {

    val allMatches = repository.getAllMatches()


    fun insertMatches(matches: List<MatchEntity>) {
        viewModelScope.launch {
            repository.insertMatches(matches)
        }
    }

    fun getFixtureInformation() {
        viewModelScope.launch {
            val mHandler = Handler(Looper.getMainLooper())
            val url = "https://3e0093ee-a397-4a87-bc26-ef97480c5292.mock.pstmn.io/fixtures"
            val request = Request.Builder().url(url).build()
            val client = OkHttpClient()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                        val adapter: JsonAdapter<List<MatchEntity>> = moshi.adapter(
                            Types.newParameterizedType(List::class.java, MatchEntity::class.java)
                        )

                        val json = "https://3e0093ee-a397-4a87-bc26-ef97480c5292.mock.pstmn.io/fixtures"
                        val matchEntities: List<MatchEntity>? = adapter.fromJson(json)
                        mHandler.post {
                            if (matchEntities != null) {
                                insertMatches(matchEntities)
                            }
                        }
                        response.body?.close()
                    }
                }
            })
        }
    }

        data class AppState(
            val loading: Boolean = false,
            val data: List<String> = emptyList(),
            val error: Throwable? = null
        )
    }