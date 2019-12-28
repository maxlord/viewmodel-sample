package ru.ls.viewmodelsample.presentation.step6

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class SavedStateViewModel(private val state: SavedStateHandle) : ViewModel() {

	companion object {
		private const val NAME_KEY = "name"
	}

	// Expose an immutable LiveData
	fun getName(): LiveData<String> {
		// getLiveData obtains an object tat is associated with the key wrapped in a LiveData
		// so it can be observed for changes.
		return state.getLiveData(NAME_KEY)
	}

	fun saveNewName(newName: String) {
		// Sets a new value for the object associated to the key. There's no need to set it
		// as a LiveData.
		state.set(NAME_KEY, newName)
	}
}