package com.example.restapp.ui.authorization

import androidx.lifecycle.viewModelScope
import com.example.restapp.domain.repository.ProfileRepository
import com.example.restapp.ui.authorization.AuthorizationScreenViewModel.Event.OnLoginPressed
import com.example.restapp.ui.base.BaseEvent
import com.example.restapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthorizationScreenViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : BaseViewModel<AuthorizationScreenViewModel.Event>() {

    private val _authorizationStateFlow = MutableSharedFlow<AuthorizationState>(replay = 1)
    val authorizationState = _authorizationStateFlow
        .asSharedFlow()
        .onEach { delay(100L) }

    override fun obtainEvent(event: Event) {
        when (event) {
            is OnLoginPressed -> {
                pressLogin(event.login, event.password)
            }
            is Event.OnRegisterPressed -> {
                pressRegistration()
            }
        }
    }

    private fun pressLogin(login: String, password: String) = viewModelScope.launch {
        with(_authorizationStateFlow) {
            emit(AuthorizationState.NoAuthorization)
            profileRepository.login(login, password)
                .onFailure { emit(AuthorizationState.AuthorizedError) }
                .onSuccess { emit(AuthorizationState.Authorized) }
        }
    }

    private fun pressRegistration() = viewModelScope.launch {
        with(_authorizationStateFlow) {
            emit(AuthorizationState.Registration)
        }
    }

    sealed class AuthorizationState {
        object NoAuthorization : AuthorizationState()
        object Authorized : AuthorizationState()
        object AuthorizedError : AuthorizationState()
        object Registration : AuthorizationState()
    }

    sealed class Event : BaseEvent() {
        class OnLoginPressed(val login: String, val password: String) : Event()
        object OnRegisterPressed : Event()
    }

}