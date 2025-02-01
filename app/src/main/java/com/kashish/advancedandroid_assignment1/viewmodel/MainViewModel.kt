package com.kashish.advancedandroid_assignment1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kashish.advancedandroid_assignment1.datastore.DataStoreManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainViewModel(private val dataStoreManager: DataStoreManager) : ViewModel() {

    // State Flows for UI observation
    private val _id = MutableStateFlow("842") // Default value from student ID
    val id: StateFlow<String> = _id

    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username

    private val _courseName = MutableStateFlow("")
    val courseName: StateFlow<String> = _courseName

    init {
        // Automatically load data when ViewModel is created
        viewModelScope.launch {
            dataStoreManager.userDataFlow.collectLatest { userData ->
                _id.value = userData.id.ifEmpty { "842" }
                _username.value = userData.username
                _courseName.value = userData.courseName
            }
        }
    }

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
            // Reset to default ID while keeping others empty
            _id.value = "842"
            _username.value = ""
            _courseName.value = ""
        }
    }
}