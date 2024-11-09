package coded.toolbox.gdprimplementation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.android.gms.ads.MobileAds
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gdprManager = GDPRManager(applicationContext)

        runBlocking {
            val consent = gdprManager.hasConsent.first() ?: false
            MobileAds.initialize(this@MainActivity) {
            }
        }

        setContent {
            AppContent(gdprManager)
        }
    }
}
