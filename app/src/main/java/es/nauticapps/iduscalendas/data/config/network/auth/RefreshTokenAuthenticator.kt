package es.nauticapps.iduscalendas.data.config.network.auth


import es.nauticapps.iduscalendas.data.config.network.auth.model.RequestIdusAuthDataModel
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject


class RefreshTokenAuthenticate @Inject constructor(private val authLocal: OAuthLocal) : Authenticator {

    lateinit var service: IdusServiceAuth

    override fun authenticate(route: Route?, response: Response): Request? {
        authLocal.clearPersistedOAuthToken()
        val token = refreshToken()
        return response.request.newBuilder().header("Authorization", "Bearer $token").build()
    }

    fun refreshToken(): String {
        if (::service.isInitialized) {
            val client = RequestIdusAuthDataModel(
                    "1079213088528-87bg8cqlu35o68c4i90tpasvu4n3orib.apps.googleusercontent.com",
                    "refresh_token",
                    "1//03aVH2FfLlKEqCgYIARAAGAMSNwF-L9IrTynqZsBGbSUfsZfCJ_WQ8_N3xvShMPh0IqypLk9FZsNN7jS9i5DphDy7mee-2NCxdDI"
            )
            val myservice = service.refreshToken(client.client_id, client.grand_type, client.refresh_token)
            try {
                val token = myservice.execute().body()?.access_token
                if (token != null) {
                    authLocal.persistOAuthToken(token)
                    return authLocal.getPersistedOAuthToken()
                }
            } catch(e: Exception) {
                return ""
            }
        }
        return ""
    }


}