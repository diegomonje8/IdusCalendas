package es.nauticapps.iduscalendas.data

import java.io.Serializable

data class CalendarResponseDataModel(
    val etag: String = "",
    val items: List<Calendar>,
    val kind: String = "",
    val nextSyncToken: String = ""
)

data class Calendar(
    var accessRole: String? = "",
    val backgroundColor: String = "",
    val colorId: String = "",
    val conferenceProperties: ConferenceProperties,
    val defaultReminders: List<DefaultReminder>,
    var description: String? = "",
    val etag: String = "",
    val foregroundColor: String = "",
    var id: String? = "",
    val kind: String = "",
    val notificationSettings: NotificationSettings,
    val primary: Boolean = false,
    val selected: Boolean = false,
    var summary: String? = "",
    var timeZone: String? = ""
)

data class ConferenceProperties(
    val allowedConferenceSolutionTypes: List<String> = listOf()
)

data class DefaultReminder(
    val method: String = "",
    val minutes: Int = 0
)

data class NotificationSettings(
    val notifications: List<Notification> = listOf()
)

data class Notification(
    val method: String = "",
    val type: String = ""
)
