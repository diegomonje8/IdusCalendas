package es.nauticapps.iduscalendas.data.config.network.auth.model

data class ResponseIdusAuthDataModel(
    val access_token: String,
    val expires_in: Int,
    val scope: String,
    val token_type: String
)
