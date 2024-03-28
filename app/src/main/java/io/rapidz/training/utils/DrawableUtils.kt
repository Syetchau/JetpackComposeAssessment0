package io.rapidz.training.utils

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.accompanist.drawablepainter.rememberDrawablePainter

object DrawableUtils {

    @Composable
    fun Drawable(
        modifier: Modifier,
        drawable: Drawable,
        contentDescription: String?= null,
        isClickable: Boolean = false,
        onClickEvent: (() -> Unit?)? =null
    ) {
        Box(
            modifier = modifier.clickable(enabled = isClickable) {
                onClickEvent?.invoke()
            },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = rememberDrawablePainter(drawable = drawable),
                contentDescription = contentDescription,
            )
        }
    }
}