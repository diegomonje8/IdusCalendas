package es.nauticapps.iduscalendas.data.idus.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import es.nauticapps.iduscalendas.data.Calendar
import es.nauticapps.iduscalendas.domain.CalendarDomainModel


@Entity(tableName = "calendars")
data class IdusEntityCalendarDataModel(
    @PrimaryKey var id: String,
    @ColumnInfo(name = "summary") var summary : String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "timeZone") var timeZone: String,
    @ColumnInfo(name = "accessRole") var accessRole: String
)

fun Calendar.toDaoModel(): IdusEntityCalendarDataModel {
    return IdusEntityCalendarDataModel(id, summary, description, timeZone, accessRole)
}

fun CalendarDomainModel.toDaoModel(): IdusEntityCalendarDataModel {
    return IdusEntityCalendarDataModel(id, summary, description, timeZone, accessRole)
}

fun IdusEntityCalendarDataModel.toDomainModel(): CalendarDomainModel {
    return CalendarDomainModel(id, summary, description, timeZone, accessRole)
}

fun Calendar.toDomainModel() : CalendarDomainModel {
    return CalendarDomainModel(id,summary,description,timeZone,accessRole)
}
