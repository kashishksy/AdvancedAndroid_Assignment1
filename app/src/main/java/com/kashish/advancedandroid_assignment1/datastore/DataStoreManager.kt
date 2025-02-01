package com.kashish.advancedandroid_assignment1.datastore

import com.kashish.advancedandroid_assignment1.datastore.UserData
import com.kashish.advancedandroid_assignment1.datastore.UserPreferencesSerializer
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException

private const val DATA_STORE_FILE_NAME = "user_data.pb"

class DataStoreManager(context: Context) {
    private val userDataStore = context.userDataStore

    val userDataFlow: Flow<UserData> = userDataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(UserData.getDefaultInstance())
            } else {
                throw exception
            }
        }

    suspend fun saveUserData(id: String, username: String, courseName: String) {
        userDataStore.updateData { currentData ->
            currentData.toBuilder()
                .setId(id)
                .setUsername(username)
                .setCourseName(courseName)
                .build()
        }
    }

    suspend fun clearUserData() {
        userDataStore.updateData { currentData ->
            currentData.toBuilder()
                .clearId()
                .clearUsername()
                .clearCourseName()
                .build()
        }
    }

    companion object {
        private val Context.userDataStore: DataStore<UserData> by dataStore(
            fileName = DATA_STORE_FILE_NAME,
            serializer = UserPreferencesSerializer
        )
    }
}