package es.nauticapps.iduscalendas.data
data class CalendarResponseDataModel(
    val etag: String,
    val items: List<Calendar>,
    val kind: String,
    val nextSyncToken: String
)

data class Calendar(
    val accessRole: String,
    val backgroundColor: String,
    val colorId: String,
    val conferenceProperties: ConferenceProperties,
    val defaultReminders: List<DefaultReminder>,
    val description: String,
    val etag: String,
    val foregroundColor: String,
    val id: String,
    val kind: String,
    val notificationSettings: NotificationSettings,
    val primary: Boolean,
    val selected: Boolean,
    val summary: String,
    val timeZone: String
)

data class ConferenceProperties(
    val allowedConferenceSolutionTypes: List<String>
)

data class DefaultReminder(
    val method: String,
    val minutes: Int
)

data class NotificationSettings(
    val notifications: List<Notification>
)

data class Notification(
    val method: String,
    val type: String
)
