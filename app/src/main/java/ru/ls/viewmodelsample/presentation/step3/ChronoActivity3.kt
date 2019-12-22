package ru.ls.viewmodelsample.presentation.step3

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.chrono_activity_3.*
import ru.ls.viewmodelsample.R

class ChronoActivity3 : AppCompatActivity() {

	private lateinit var liveDataTimerViewModel: LiveDataTimerViewModel

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setContentView(R.layout.chrono_activity_3)

		liveDataTimerViewModel = ViewModelProvider(this).get(LiveDataTimerViewModel::class.java)

		subscribe()
	}

	private fun subscribe() {
		val elapsedTimeObserver = Observer<Long> { t ->
			val newText = getString(R.string.seconds, t!!)
			timer_textview.text = newText
			Log.d("ChronoActivity3", "Updating timer")
		}

		//TODO: observe the ViewModel's elapsed time
		liveDataTimerViewModel!!.getElapsedTime().observe(this, elapsedTimeObserver)
	}
}