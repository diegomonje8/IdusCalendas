package es.nauticapps.iduscalendas.data.config.local

import androidx.room.Database
import androidx.room.RoomDatabase
import es.nauticapps.iduscalendas.data.idus.model.IdusEntityCalendarDataModel

@Database(entities = [IdusEntityCalendarDataModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun idusDao(): IdusDao
}