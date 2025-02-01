// In app/src/main/java/.../viewmodel/ViewModelFactory.kt
package com.kashish.advancedandroid_assignment1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kashish.advancedandroid_assignment1.datastore.DataStoreManager

class ViewModelFactory(
    private val dataStoreManager: DataStoreManager
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(dataStoreManager) as T
    }
}