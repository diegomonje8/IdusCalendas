package es.nauticapps.iduscalendas.data.idus.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import es.nauticapps.iduscalendas.domain.EventDomainModel


@Entity(tableName = "events")
data class IdusEntityEventDataModel(
    @PrimaryKey var id: String,
    @ColumnInfo(name = "idcalendar")  var idcalendar: String,
    @ColumnInfo(name = "summary") var summary : String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "location") var location: String,
    @ColumnInfo(name = "start") var start: String,
    @ColumnInfo(name = "end") var end: String,
    @ColumnInfo(name = "status") var status: Int
)

fun IdusEntityEventDataModel.toDomainModel(): EventDomainModel {
    return EventDomainModel(id,summary,description,location,start,end)
}

