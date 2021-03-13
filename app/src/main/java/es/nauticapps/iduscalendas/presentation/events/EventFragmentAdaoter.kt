package es.nauticapps.iduscalendas.presentation.events

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.nauticapps.iduscalendas.databinding.ItemEventListBinding
import es.nauticapps.iduscalendas.domain.EventDomainModel

class EventFragmentAdapter (private var events: List<EventDomainModel>, private var listener: (event: EventDomainModel) -> Unit) : RecyclerView.Adapter<EventFragmentAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemEventListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(ItemEventListBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false))

    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.binding.eventItemListStartDate.text = events[position].startDate
        viewHolder.binding.eventItemListEndDate.text = events[position].endDate
        viewHolder.binding.eventItemListTextSummary.text = events[position].summary
        viewHolder.binding.eventItemListTextDesc.text = events[position].description
        viewHolder.binding.eventItemListTextLocation.text = events[position].location
        viewHolder.binding.eventItemListEditButton.setOnClickListener {
            listener(events[position])
        }

    }

    override fun getItemCount() = events.size

    fun updateList (newList: List<EventDomainModel>) {
        events = newList
        notifyDataSetChanged()
    }

}