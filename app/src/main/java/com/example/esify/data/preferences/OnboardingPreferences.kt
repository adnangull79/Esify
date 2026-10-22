package com.example.esify.data.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OnboardingPreferences(context: Context) {

    private val prefs: SharedPreferences = context.applicationContext.getSharedPreferences(
        PREFS_NAME,
        Context.MODE_PRIVATE
    )

    suspend fun isOnboardingCompleted(): Boolean = withContext(Dispatchers.IO) {
        prefs.getBoolean(KEY_ONBOARDING_COMPLETED, false)
    }

    suspend fun setOnboardingCompleted(completed: Boolean = true) = withContext(Dispatchers.IO) {
        prefs.edit().putBoolean(KEY_ONBOARDING_COMPLETED, completed).apply()
    }

    fun isOnboardingCompletedSync(): Boolean {
        return prefs.getBoolean(KEY_ONBOARDING_COMPLETED, false)
    }

    fun setOnboardingCompletedSync(completed: Boolean = true) {
        prefs.edit().putBoolean(KEY_ONBOARDING_COMPLETED, completed).commit()
    }

    fun clear() {
        prefs.edit().clear().commit()
    }

    companion object {
        const val PREFS_NAME = "esify_onboarding_preferences"
        const val KEY_ONBOARDING_COMPLETED = "key_onboarding_completed"
    }
}

@Composable
fun rememberOnboardingPreferences(): OnboardingPreferences {
    val context = LocalContext.current
    return remember { OnboardingPreferences(context) }
}
