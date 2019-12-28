package ru.ls.viewmodelsample.presentation.step5

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_step5.*
import ru.ls.viewmodelsample.R

// Shows a SeekBar that should be synced with a value in a ViewModel.
class Fragment_step5 : Fragment() {

	private var seekBarViewModel: SeekBarViewModel? = null

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		val root = inflater.inflate(R.layout.fragment_step5, container, false)

		seekBarViewModel = ViewModelProvider(requireActivity()).get(SeekBarViewModel::class.java)

		return root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		subscribeSeekBar()
	}

	private fun subscribeSeekBar() {
		// Update the ViewModel when the SeekBar is changed.

		seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {

			override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
				if (fromUser) {
					Log.d("Step5", "Progress changed!")
					seekBarViewModel?.seekbarValue?.value = progress
				}
			}

			override fun onStartTrackingTouch(seekBar: SeekBar?) {

			}

			override fun onStopTrackingTouch(seekBar: SeekBar?) {

			}
		})

		// Update the SeekBar when the ViewModel is changed.
		seekBarViewModel!!.seekbarValue.observe(
			requireActivity(),
			Observer<Int> { value -> value?.let { seekBar.progress = it } }
		)
	}
}