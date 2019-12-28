package ru.ls.viewmodelsample.presentation.step3

import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

/**
 * A ViewModel used for the {@link ChronoActivity3}.
 */
class LiveDataTimerViewModel : ViewModel() {

	companion object {
		private const val ONE_SECOND = 1000L
	}

	private val elapsedTime: MutableLiveData<Long> = MutableLiveData()
	private var initialTime: Long = 0L
	private var timer: Timer

	init {
		initialTime = SystemClock.elapsedRealtime()
		timer = Timer()

		timer.scheduleAtFixedRate(object : TimerTask() {

			override fun run() {
				val newValue: Long = (SystemClock.elapsedRealtime() - initialTime) / 1000
				// setValue() cannot be called from a background thread so post to main thread.
				elapsedTime.postValue(newValue)
			}
		}, ONE_SECOND, ONE_SECOND)
	}

	fun getElapsedTime(): LiveData<Long> = elapsedTime

	override fun onCleared() {
		super.onCleared()
		timer.cancel()
	}
}