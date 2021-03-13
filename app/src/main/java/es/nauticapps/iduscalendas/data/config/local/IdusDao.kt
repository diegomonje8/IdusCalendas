package es.nauticapps.iduscalendas.data.config.local

import androidx.room.*
import es.nauticapps.iduscalendas.data.idus.model.IdusEntityCalendarDataModel
import es.nauticapps.iduscalendas.data.idus.model.IdusEntityEventDataModel


@Dao
interface IdusDao {

    @Query("SELECT * FROM calendars")
    suspend fun getAllCalendars(): List<IdusEntityCalendarDataModel>

    @Insert
    suspend fun insertCalendar(calendar: IdusEntityCalendarDataModel)

    @Update
    suspend fun updateCalendar(calendar: IdusEntityCalendarDataModel)

    @Delete
    suspend fun deleteCalendar(calendar: IdusEntityCalendarDataModel)

    @Query("DELETE FROM calendars WHERE status = 0")
    suspend fun deleteAllCalendars()








}