package es.nauticapps.iduscalendas.data.config.local

import android.content.Context
import androidx.room.Room
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class IdusDatabase @Inject constructor(@ApplicationContext private val context: Context) {

    fun loadDatabase(): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "idusappcalendar.db").build()
    }

}