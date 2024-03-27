package io.rapidz.training.utils

import android.content.Intent
import android.os.Looper
import androidx.activity.ComponentActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object UiUtils {

    fun ComponentActivity.goToNextActivity(
        intent: Intent,
        requestCode: Int? = null,
        isFinishing: Boolean = false
    ) {
        if(requestCode != null) {
            startActivityForResult(intent, requestCode)
        } else {
            startActivity(intent)
        }

        if(isFinishing) {
            finish()
        }
    }

    /**
     * Usually used for UI operation.
     */
    fun CoroutineScope.launchMain(delay: Long = 0L, block: CoroutineScope.() -> Unit) {
        if(delay <= 0 && Looper.myLooper() == Looper.getMainLooper()) {
            block()
            return
        }

        launch(Dispatchers.Main) {
            delay(delay)
            block()
        }
    }
}