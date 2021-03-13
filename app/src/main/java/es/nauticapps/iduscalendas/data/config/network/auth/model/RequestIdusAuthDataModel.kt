package es.nauticapps.iduscalendas.data.config.network.auth.model

data class RequestIdusAuthDataModel(
    val client_id : String,
    val grand_type: String,
    val refresh_token: String
)
