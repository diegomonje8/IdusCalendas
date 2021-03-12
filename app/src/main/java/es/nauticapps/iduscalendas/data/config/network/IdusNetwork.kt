package es.nauticapps.iduscalendas.data.config.network


import android.util.Log
import com.google.gson.GsonBuilder
import es.nauticapps.iduscalendas.data.CalendarResponseDataModel
import es.nauticapps.iduscalendas.data.idus.model.RequestNewCalendar
import javax.inject.Inject


class IdusNetwork @Inject constructor(private val service: IdusService) {

    suspend fun getAllCalendars() : CalendarResponseDataModel {

        val serc = service.getAllCalendars()
        return serc
    }

    suspend fun newCalendar(name: RequestNewCalendar) {
        //val answer = JSONObject("""{"summary":"test name"}""")

        val gson = GsonBuilder().serializeNulls().create()
        val foo = FooRequest()
        var json = gson.toJson(foo)
        Log.e("Hola", json)


        service.newCalendar(name)

    }


}

data class FooRequest(
    val summary: String = "TestCalendar",
    val description: String = "Unadescription"
)