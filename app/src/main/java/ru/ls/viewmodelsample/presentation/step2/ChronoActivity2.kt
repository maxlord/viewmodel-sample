package ru.ls.viewmodelsample.presentation.step2

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import ru.ls.viewmodelsample.R

class ChronoActivity2 : AppCompatActivity() {

	companion object {
		private const val TAG = "ChronoActivity2"
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setContentView(R.layout.activity_main)

		// The ViewModelStore provides a new ViewModel or one previously created.
		val chronometerViewModel = ViewModelProvider(this).get(ChronometerViewModel::class.java)

		if (chronometerViewModel.startTime == null) { // If the start date is not defined, it's a new ViewModel so set it.
			Log.d(TAG, "startTime is null")
			val startTime = SystemClock.elapsedRealtime()
			chronometerViewModel.startTime = startTime
			chronometer.base = startTime
		} else { // Otherwise the ViewModel has been retained, set the chronometer's base to the original starting time.
			Log.d(TAG, "startTime is restored from view model")
			chronometer.base = chronometerViewModel.startTime ?: 0L
		}

		chronometer.start()
	}
}