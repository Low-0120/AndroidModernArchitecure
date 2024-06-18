package com.example.modern_architecture_template

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.network.firebase.TokenProvider
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import data.UserData
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    auth: FirebaseAuth,
    tokenProvider: TokenProvider
) : ViewModel() {
    val uiState: StateFlow<MainActivityUiState> = flow<MainActivityUiState> {
        val token = tokenProvider.getToken()
        if (token == null) {
            MainActivityUiState.Loading
            emit(MainActivityUiState.Loading)
        } else {
            MainActivityUiState.Success(UserData(1))
            emit(MainActivityUiState.Success(UserData(1)))
        }

    }.stateIn(
        scope = viewModelScope,
        initialValue = MainActivityUiState.Loading,
        started = SharingStarted.WhileSubscribed(5_000)
    )
}

sealed interface MainActivityUiState {
    data object Loading : MainActivityUiState
    data class Success(val userData: UserData) : MainActivityUiState
}
