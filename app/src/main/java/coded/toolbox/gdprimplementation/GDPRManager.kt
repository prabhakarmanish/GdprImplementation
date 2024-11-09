package coded.toolbox.gdprimplementation

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("gdpr_preferences")

class GDPRManager(private val context: Context) {
    private val consentKey = booleanPreferencesKey("gdpr_consent")

    val hasConsent: Flow<Boolean?> = context.dataStore.data
        .map { preferences -> preferences[consentKey] }

    suspend fun setConsent(consent: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[consentKey] = consent
        }
    }
}
