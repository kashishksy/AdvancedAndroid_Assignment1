package com.kashish.advancedandroid_assignment1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kashish.advancedandroid_assignment1.datastore.DataStoreManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

// MainViewModel.kt

class MainViewModel(private val dataStoreManager: DataStoreManager) : ViewModel() {
    private val _id = MutableStateFlow("842")
    val id: StateFlow<String> = _id

    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username

    private val _courseName = MutableStateFlow("")
    val courseName: StateFlow<String> = _courseName

    // Remove the init block that automatically collects

    fun updateId(newId: String) {
        _id.value = newId
    }

    fun updateUsername(newUsername: String) {
        _username.value = newUsername
    }

    fun updateCourseName(newCourseName: String) {
        _courseName.value = newCourseName
    }

    fun storeData() {
        viewModelScope.launch {
            dataStoreManager.saveUserData(
                _id.value,
                _username.value,
                _courseName.value
            )
        }
    }

    fun resetData() {
        viewModelScope.launch {
            dataStoreManager.clearUserData()
            // Reset UI state after clearing data
            _id.value = "842"
            _username.value = ""
            _courseName.value = ""
        }
    }

    fun loadData() {
        viewModelScope.launch {
            try {
                val userData = dataStoreManager.userDataFlow.first()
                // Update values based on the loaded data
                _id.value = userData.id.ifEmpty { "842" }
                _username.value = userData.username
                _courseName.value = userData.courseName
            } catch (e: Exception) {
                // Reset to defaults on error
                _id.value = "842"
                _username.value = ""
                _courseName.value = ""
            }
        }
    }

}