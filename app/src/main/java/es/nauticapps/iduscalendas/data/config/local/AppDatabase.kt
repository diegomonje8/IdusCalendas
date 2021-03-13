package es.nauticapps.iduscalendas.data.config.local

import androidx.room.Database
import androidx.room.RoomDatabase
import es.nauticapps.iduscalendas.data.idus.model.IdusEntityCalendarDataModel
import es.nauticapps.iduscalendas.data.idus.model.IdusEventsDataModel

@Database(entities = [IdusEntityCalendarDataModel::class, IdusEventsDataModel::class],  version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun idusDao(): IdusDao
}