package com.example.modern_architecture_template

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import data.UserData
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor():ViewModel(){

    sealed interface MainActivityUiState{
        data object Loading:MainActivityUiState
        data class Success(val userData: UserData)
    }
}