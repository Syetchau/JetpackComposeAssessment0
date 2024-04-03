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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
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
        Column(modifier = Modifier.padding(vertical = SPACING_32)) {
            TextLabel(label = getString(R.string.label_hello_world_2))
            ConstraintLayout(modifier = Modifier.fillMaxSize().padding(horizontal = SPACING_16)) {
                val (row, box) = createRefs()

                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.constrainAs(row) {
                    top.linkTo(parent.top)
                    bottom.linkTo(box.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }) {
                    Drawable(
                        modifier = Modifier.weight(1f),
                        drawable = AppCompatResources.getDrawable(this@ImageActivity, R.drawable.hello_world)!!
                    )
                    Drawable(
                        modifier = Modifier.size(EARTH_LOGO_SIZE),
                        drawable = AppCompatResources.getDrawable(this@ImageActivity, R.drawable.ic_world)!!
                    )
                }
                Box(modifier = Modifier.constrainAs(box) {
                    top.linkTo(row.bottom)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }) {
                    Drawable(
                        modifier = Modifier
                            .size(HEIGHT_50)
                            .background(Color.LightGray),
                        drawable = AppCompatResources.getDrawable(this@ImageActivity, android.R.drawable.btn_star_big_on)!!,
                        isClickable = true,
                        onClickEvent = {
                            Toast.makeText(this@ImageActivity, R.string.label_thanks_for_rating, Toast.LENGTH_LONG).show()
                        }
                    )
                }
            }
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