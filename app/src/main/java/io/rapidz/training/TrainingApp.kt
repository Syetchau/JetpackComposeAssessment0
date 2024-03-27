package io.rapidz.training

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.rapidz.training.storage.PreferenceManager
import timber.log.Timber

@HiltAndroidApp
class TrainingApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        PreferenceManager.init(this@TrainingApp)
    }
}