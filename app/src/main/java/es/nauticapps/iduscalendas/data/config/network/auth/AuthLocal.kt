package es.nauticapps.iduscalendas.data.config.network.auth


import android.content.SharedPreferences
import javax.inject.Inject

class OAuthLocal @Inject constructor(private val sharedPreferences: SharedPreferences) {

    companion object {
        private const val OAUTH_TOKEN = "OAUTH_TOKEN"
    }

    fun persistOAuthToken(oAuthToken: String) {
        sharedPreferences.edit().putString(OAUTH_TOKEN, oAuthToken).apply()
    }

    fun getPersistedOAuthToken(): String {
        return sharedPreferences.getString(OAUTH_TOKEN, "") ?: ""
    }

    fun clearPersistedOAuthToken() {
        sharedPreferences.edit().remove(OAUTH_TOKEN).apply()
    }
}