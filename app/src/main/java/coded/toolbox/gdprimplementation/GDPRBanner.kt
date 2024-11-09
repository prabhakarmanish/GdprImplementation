package coded.toolbox.gdprimplementation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun GDPRBanner(gdprManager: GDPRManager, onConsentChanged: (Boolean) -> Unit) {
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "We use cookies to personalize ads. Do you accept?")
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = {
                coroutineScope.launch {
                    gdprManager.setConsent(true)
                    onConsentChanged(true)
                }
            }) {
                Text("Accept")
            }
            Button(onClick = {
                coroutineScope.launch {
                    gdprManager.setConsent(false)
                    onConsentChanged(false)
                }
            }) {
                Text("Decline")
            }
        }
    }
}
