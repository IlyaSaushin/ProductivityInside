package com.earl.productivityinside.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.earl.productivityinside.domain.Interactor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainFragmentViewModel @Inject constructor(
    private val interactor: Interactor
) : ViewModel() {

    fun getLocationByIp(ipAdr: String) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.getLocationByIp(ipAdr)
        }
    }
}