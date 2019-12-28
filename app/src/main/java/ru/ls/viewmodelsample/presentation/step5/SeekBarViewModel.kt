package ru.ls.viewmodelsample.presentation.step5

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SeekBarViewModel : ViewModel() {

	var seekbarValue: MutableLiveData<Int> = MutableLiveData()
}