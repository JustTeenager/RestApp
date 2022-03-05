package com.example.restapp.ui.main_screen

import com.example.restapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor() : BaseViewModel<Nothing>() {
    override fun obtainEvent(event: Nothing) {}
}