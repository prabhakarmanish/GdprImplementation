package coded.toolbox.gdprimplementation

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.android.gms.ads.interstitial.InterstitialAd

@Composable
fun AppContent(gdprManager: GDPRManager) {
    val context = LocalContext.current as Activity
    var hasConsent by remember { mutableStateOf<Boolean?>(null) }
    var interstitialAd: InterstitialAd? by remember { mutableStateOf(null) }

    LaunchedEffect(gdprManager) {
        gdprManager.hasConsent.collect {
            hasConsent = it
            if (it == true) {
                loadInterstitialAd(context) { ad ->
                    interstitialAd = ad
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        when (hasConsent) {
            null -> GDPRBanner(gdprManager) { consent -> hasConsent = consent }
            true -> {
                Text("Personalized ads enabled")
                Button(onClick = { interstitialAd?.show(context) }) {
                    Text("Show Interstitial Ad")
                }
                BannerAdView()
            }

            false -> Text("Personalized ads disabled")
        }
    }
}
