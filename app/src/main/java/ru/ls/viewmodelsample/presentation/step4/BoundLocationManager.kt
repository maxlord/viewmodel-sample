package ru.ls.viewmodelsample.presentation.step4

import android.content.Context
import android.location.LocationListener
import android.location.LocationManager
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

class BoundLocationManager {

	companion object {

		fun bindLocationListenerIn(
			lifecycleOwner: LifecycleOwner,
			listener: LocationListener,
			context: Context
		) {
			BoundLocationListener(lifecycleOwner, listener, context)
		}
	}

	@SuppressWarnings("MissingPermission")
	class BoundLocationListener(
		private val lifecycleOwner: LifecycleOwner,
		private val listener: LocationListener,
		private val context: Context
	) : LifecycleObserver {

		init {
			lifecycleOwner.lifecycle.addObserver(this)
		}

		private var locationManager: LocationManager? = null

		@OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
		fun addLocationListener() {
			// Note: Use the Fused Location Provider from Google Play Services instead.
			// https://developers.google.com/android/reference/com/google/android/gms/location/FusedLocationProviderApi

			locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
			locationManager?.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, listener)
			Log.d("BoundLocationMgr", "Listener added")

			// Force an update with the last location, if available.
			val lastLocation = locationManager?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
			lastLocation?.let { listener.onLocationChanged(it) }
		}

		@OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
		fun removeLocationListener() {
			locationManager?.removeUpdates(listener)
			locationManager = null
			Log.d("BoundLocationMgr", "Listener removed")
		}
	}
}