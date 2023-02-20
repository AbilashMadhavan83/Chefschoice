package com.example.chefschoice.ui.favorites

import android.content.ContentValues
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chefschoice.data.repository.AppRepository
import com.example.chefschoice.data.enums.ApiStatus
import com.example.chefschoice.data.model.*
import kotlinx.coroutines.launch

class FavoritesViewModel(private val repository: AppRepository) : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<ApiStatus>()
    // The external immutable LiveData for the request status
    val status: LiveData<ApiStatus> = _status

    init {

    }



















}