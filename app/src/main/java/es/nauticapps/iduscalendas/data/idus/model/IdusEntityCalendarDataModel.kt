package es.nauticapps.iduscalendas.data.idus.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import es.nauticapps.iduscalendas.data.Calendar
import es.nauticapps.iduscalendas.domain.CalendarDomainModel
import es.nauticapps.iduscalendas.domain.EventDomainModel


@Entity(tableName = "calendars")
data class IdusEntityCalendarDataModel(
    @PrimaryKey var id: String,
    @ColumnInfo(name = "summary") var summary : String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "timeZone") var timeZone: String,
    @ColumnInfo(name = "accessRole") var accessRole: String,
    @ColumnInfo(name = "status") var status: Int
)

@Entity(tableName = "events")
data class IdusEventsDataModel(
    @PrimaryKey var id: String,
    @ColumnInfo(name = "calendarId") var calendarId : String,
    @ColumnInfo(name = "summary") var summary : String,
    @ColumnInfo(name = "description") var description : String,
    @ColumnInfo(name = "location") var location : String,
    @ColumnInfo(name = "start") var start : String,
    @ColumnInfo(name = "end") var end : String,
)

fun IdusEventsDataModel.toDomaiModel() : EventDomainModel {
    return EventDomainModel(id,summary,description,location,start,end)
}


//fun Calendar.toDaoModel(): IdusEntityCalendarDataModel {
//    return IdusEntityCalendarDataModel(id ?: "", summary ?: "", description ?: "", timeZone ?: "", accessRole ?: "", status())
//}

fun CalendarDomainModel.toDaoModel(): IdusEntityCalendarDataModel {
    return IdusEntityCalendarDataModel(id, summary, description, timeZone, accessRole, status = 0)
}

fun IdusEntityCalendarDataModel.toDomainModel(): CalendarDomainModel {
    return CalendarDomainModel(id, summary, description, timeZone, accessRole)
}

fun Calendar.toDomainModel() : CalendarDomainModel {
    return CalendarDomainModel(id ?: "",summary ?: "",description ?: "",timeZone ?: "",accessRole ?: "")
}
