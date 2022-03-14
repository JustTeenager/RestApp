package com.example.restapp.ui.authorization

import com.example.restapp.domain.repository.ProfileRepository
import com.example.restapp.ui.authorization.AuthorizationScreenViewModel.Event.OnLoginPressed
import com.example.restapp.ui.base.BaseEvent
import com.example.restapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthorizationScreenViewModel @Inject constructor(
    profileRepository: ProfileRepository
) :
    BaseViewModel<AuthorizationScreenViewModel.Event>() {

    override fun obtainEvent(event: Event) {
        when (event) {
            is OnLoginPressed -> {

            }
        }
    }

    sealed class Event : BaseEvent() {
        class OnLoginPressed(val login: String, val password: String) : Event()
    }

    private fun pressLogin() {

    }

}