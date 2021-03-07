package es.nauticapps.iduscalendas.presentation.calendar


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.nauticapps.iduscalendas.data.Calendar
import es.nauticapps.iduscalendas.databinding.ItemCalendarListBinding
import es.nauticapps.iduscalendas.domain.CalendarDomainModel

//class CalendarFragmentAdapter (private var calendars: List<Calendar>, private var listener: (calendar: Calendar) -> Unit) : RecyclerView.Adapter<CalendarFragmentAdapter.ViewHolder>() {
    class CalendarFragmentAdapter (private var calendars: List<CalendarDomainModel>) : RecyclerView.Adapter<CalendarFragmentAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemCalendarListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(ItemCalendarListBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false))

    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.binding.calendarItemListTextSummary.text = calendars[position].summary
        viewHolder.binding.calendarItemListTextTimeZone.text = calendars[position].timeZone

    }

    override fun getItemCount() = calendars.size

    fun updateList (newList: List<CalendarDomainModel>) {
        calendars = newList
        notifyDataSetChanged()
    }

}