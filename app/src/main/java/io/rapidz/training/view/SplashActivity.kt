package io.rapidz.training.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import io.rapidz.jetpackcomposetraining_assignment0.R
import io.rapidz.training.theme.APP_LOGO_SIZE
import io.rapidz.training.theme.AppTheme
import io.rapidz.training.theme.Color_Splash
import io.rapidz.training.theme.FONT_SIZE_30
import io.rapidz.training.theme.SPACING_50
import io.rapidz.training.theme.SPACING_80
import io.rapidz.training.utils.DrawableUtils.Drawable
import io.rapidz.training.utils.UiUtils.goToNextActivity
import io.rapidz.training.utils.UiUtils.launchMain

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity: ComponentActivity() {

    companion object {
        private const val TIMER_DELAY: Long = 3000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = Color_Splash) {
                    AppLogoWithText()
                }
                lifecycleScope.launchMain(TIMER_DELAY) {
                    goToNextActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                }
            }
        }
    }
}

@Composable
fun AppLogoWithText() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = SPACING_50),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Drawable(
            modifier = Modifier.size(APP_LOGO_SIZE),
            drawable = AppCompatResources.getDrawable(LocalContext.current, R.drawable.ic_splash_logo)!!
        )
        Spacer(modifier = Modifier.height(SPACING_80))
        Text(
            text = stringResource(id = R.string.hello_world),
            color = Color.White,
            fontSize = FONT_SIZE_30
        )
    }
}
