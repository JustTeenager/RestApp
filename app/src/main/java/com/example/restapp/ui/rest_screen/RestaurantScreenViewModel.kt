package com.example.restapp.ui.rest_screen

import com.example.restapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RestaurantScreenViewModel @Inject constructor() : BaseViewModel<Nothing>() {
    override fun obtainEvent(event: Nothing) {}
}