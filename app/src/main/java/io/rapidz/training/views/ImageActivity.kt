package io.rapidz.training.views

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import dagger.hilt.android.AndroidEntryPoint
import io.rapidz.jetpackcomposetraining_assignment0.R
import io.rapidz.training.theme.Color_Hello_World_Txt
import io.rapidz.training.theme.EARTH_LOGO_SIZE
import io.rapidz.training.theme.FONT_SIZE_30
import io.rapidz.training.theme.FONT_WEIGHT_600
import io.rapidz.training.theme.HEIGHT_50
import io.rapidz.training.theme.SPACING_16
import io.rapidz.training.theme.SPACING_32
import io.rapidz.training.utils.DrawableUtils.Drawable

@AndroidEntryPoint
class ImageActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImageScreen()
        }
    }

    @Composable
    fun ImageScreen() {
        Column(
            modifier = Modifier.padding(vertical = SPACING_32),
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            TextLabel(label = getString(R.string.label_hello_world_2))
            Spacer(modifier = Modifier.weight(1f))
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = SPACING_16),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Drawable(
                    modifier = Modifier.weight(1f),
                    drawable = AppCompatResources.getDrawable(this@ImageActivity, R.drawable.hello_world)!!
                )
                Drawable(
                    modifier = Modifier.size(EARTH_LOGO_SIZE),
                    drawable = AppCompatResources.getDrawable(this@ImageActivity, R.drawable.ic_world)!!
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier.padding(horizontal = SPACING_16).align(Alignment.End)
            ) {
                Drawable(
                    modifier = Modifier.size(HEIGHT_50).background(Color.LightGray),
                    drawable = AppCompatResources.getDrawable(this@ImageActivity, android.R.drawable.btn_star_big_on)!!,
                    isClickable = true,
                    onClickEvent = {
                        Toast.makeText(this@ImageActivity, R.string.label_thanks_for_rating, Toast.LENGTH_LONG).show()
                    }
                )
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun TextLabel(label: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = label,
            fontSize = FONT_SIZE_30,
            color = Color_Hello_World_Txt,
            fontWeight = FontWeight(FONT_WEIGHT_600)
        )
    }
}