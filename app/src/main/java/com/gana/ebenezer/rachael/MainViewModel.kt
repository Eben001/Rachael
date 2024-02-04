package com.gana.ebenezer.rachael

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.File

class MainViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _audioData: MutableStateFlow<ByteArray?> = MutableStateFlow(null)

    val audioData: StateFlow<ByteArray?> = _audioData

    fun sendAudioFile(audioFile: File?) {
        viewModelScope.launch {
            val data = repository.sendAudioFile(audioFile)
            _audioData.value = data
        }

    }


}