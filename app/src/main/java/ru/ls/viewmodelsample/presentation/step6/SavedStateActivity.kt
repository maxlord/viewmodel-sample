package ru.ls.viewmodelsample.presentation.step6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.saved_state_activity.*
import ru.ls.viewmodelsample.R

class SavedStateActivity: AppCompatActivity() {

	private var savedStateViewModel: SavedStateViewModel? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setContentView(R.layout.saved_state_activity)

		// Obtain the ViewModel
		savedStateViewModel = ViewModelProvider(this).get(SavedStateViewModel::class.java)

		// Show the ViewModel property's values in a TextView
		savedStateViewModel?.getName()?.observe(this,
			Observer<String> { savedString ->
				saved_vm_tv.text = getString(R.string.saved_in_vm, savedString)
			})

		// Save button
		save_bt.setOnClickListener {
			val newName = name_et.text.toString()
			savedStateViewModel?.saveNewName(newName)
		}
	}
}