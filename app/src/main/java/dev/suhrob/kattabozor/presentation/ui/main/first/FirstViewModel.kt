package dev.suhrob.kattabozor.presentation.ui.main.first

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.suhrob.kattabozor.domain.common.Resource
import dev.suhrob.kattabozor.domain.model.MainModel
import dev.suhrob.kattabozor.domain.repository.MainRepository
import dev.suhrob.kattabozor.presentation.common.UIObjectState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirstViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    private val _offers = MutableStateFlow(UIObjectState<MainModel>())
    val offers = _offers.asStateFlow()

    private fun getOffers() {
        viewModelScope.launch {
            mainRepository.getOffers().onEach {
                when (it) {
                    is Resource.Error -> {
                        _offers.value = UIObjectState(it.message ?: "")
                    }
                    is Resource.Loading -> {
                        _offers.value = UIObjectState(isLoading = true)
                    }
                    is Resource.Success -> {
                        _offers.value = UIObjectState(data = it.data)
                    }
                }
            }.launchIn(CoroutineScope(Dispatchers.IO))
        }
    }

    init {
        getOffers()
    }

}