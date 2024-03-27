package io.rapidz.training.utils

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.drawablepainter.rememberDrawablePainter

object DrawableUtils {

    @Composable
    fun Drawable(modifier: Modifier, drawable: Drawable, contentDescription: String?= null) {
        Box(modifier) {
            Image(
                painter = rememberDrawablePainter(drawable = drawable),
                contentDescription = contentDescription,
            )
        }
    }
}