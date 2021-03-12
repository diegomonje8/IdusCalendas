package es.nauticapps.iduscalendas.data.idus.model

data class EventResponseDataModel(
    val accessRole: String,
    val defaultReminders: List<DefaultReminder>,
    val etag: String,
    val items: List<EventItem>,
    val kind: String,
    val nextSyncToken: String,
    val summary: String,
    val timeZone: String,
    val updated: String
)

data class DefaultReminder(
    val method: String,
    val minutes: Int
)

data class EventItem(
    val attachments: List<EventAttachment>,
    val attendees: List<EventAttendee>,
    val conferenceData: EventConferenceData,
    val created: String,
    val creator: EventCreator,
    val description: String,
    val end: EventEnd,
    val endTimeUnspecified: Boolean,
    val etag: String,
    val eventType: String,
    val guestsCanInviteOthers: Boolean,
    val hangoutLink: String,
    val htmlLink: String,
    val iCalUID: String,
    val id: String,
    val kind: String,
    val location: String,
    val organizer: EventOrganizer,
    val privateCopy: Boolean,
    val reminders: EventReminders,
    val sequence: Int,
    val source: EventSource,
    val start: EventStart,
    val status: String,
    val summary: String,
    val transparency: String,
    val updated: String,
    val visibility: String
)

data class EventAttachment(
    val fileUrl: String,
    val iconLink: String,
    val title: String
)

data class EventAttendee(
    val displayName: String,
    val email: String,
    val organizer: Boolean,
    val responseStatus: String,
    val self: Boolean
)

data class EventConferenceData(
    val conferenceId: String,
    val conferenceSolution: EventConferenceSolution,
    val entryPoints: List<EventEntryPoint>,
    val signature: String
)

data class EventCreator(
    val email: String,
    val self: Boolean
)

data class EventEnd(
    val date: String,
    val dateTime: String
)

data class EventOrganizer(
    val displayName: String,
    val email: String,
    val self: Boolean
)

data class EventReminders(
    val useDefault: Boolean
)

data class EventSource(
    val title: String,
    val url: String
)

data class EventStart(
    val date: String,
    val dateTime: String
)

data class EventConferenceSolution(
    val iconUri: String,
    val key: EventKey,
    val name: String
)

data class EventEntryPoint(
    val entryPointType: String,
    val label: String,
    val pin: String,
    val regionCode: String,
    val uri: String
)

data class EventKey(
    val type: String
)
