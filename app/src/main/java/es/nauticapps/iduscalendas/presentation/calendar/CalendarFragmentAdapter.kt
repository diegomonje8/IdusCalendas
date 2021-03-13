package es.nauticapps.iduscalendas.presentation.calendar


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.nauticapps.iduscalendas.databinding.ItemCalendarListBinding
import es.nauticapps.iduscalendas.domain.CalendarDomainModel

class CalendarFragmentAdapter(private var calendars: List<CalendarDomainModel>, private var callback: onCardClicksListener): RecyclerView.Adapter<CalendarFragmentAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemCalendarListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(ItemCalendarListBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false))

    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.binding.calendarItemListTextSummary.text = calendars[position].summary
        viewHolder.binding.calendarItemListTextTimeZone.text = calendars[position].timeZone
        viewHolder.binding.calendarItemListEditButton.setOnClickListener {
           callback.onEditButtonClicked(calendars[position])
        }
        viewHolder.binding.calendarItemListCard.setOnClickListener {
            callback.onCalendarClicked(calendars[position])
        }

    }

    override fun getItemCount() = calendars.size

    fun updateList (newList: List<CalendarDomainModel>) {
        calendars = newList
        notifyDataSetChanged()
    }

    interface onCardClicksListener {
        fun onCalendarClicked(item: CalendarDomainModel)
        fun onEditButtonClicked(item: CalendarDomainModel)
    }
}