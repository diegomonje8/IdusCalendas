package es.nauticapps.iduscalendas.presentation.events

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import es.nauticapps.iduscalendas.R
import es.nauticapps.iduscalendas.base.BaseExtraData
import es.nauticapps.iduscalendas.base.BaseFragment
import es.nauticapps.iduscalendas.databinding.FragmentEventBinding
import es.nauticapps.iduscalendas.domain.EventDomainModel
import es.nauticapps.iduscalendas.presentation.calendar.edit.CalendarEditFragmentArgs
import retrofit2.HttpException

@AndroidEntryPoint
class EventFragment : BaseFragment<EventListState, EventViewModel, FragmentEventBinding>() {

    lateinit var myAdapter : EventFragmentAdapter
    lateinit var vm : EventViewModel

    override var viewModelClass: Class<EventViewModel> = EventViewModel::class.java

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentEventBinding = FragmentEventBinding::inflate

    private val args: EventFragmentArgs by navArgs()

    override fun setupView(viewModel: EventViewModel) {
        vm = viewModel

        myAdapter = EventFragmentAdapter(listOf<EventDomainModel>()) { event ->
            findNavController().navigate(EventFragmentDirections.actionEventFragmentToEventEditFragment(args.calendar, event))
        }

        binding.eventFragmentFloatingButton.setOnClickListener(View.OnClickListener {
            findNavController().navigate(EventFragmentDirections.actionEventFragmentToEventEditFragment(args.calendar, EventDomainModel("","", "", "", "", "")))
        })

        binding.eventFragmentCalendarSummary.text = args.calendar.summary

        val myRecyclerView : RecyclerView = binding.eventFragmentRecycler
        myRecyclerView.apply {
            adapter = myAdapter
            layoutManager =  LinearLayoutManager(
                    context, LinearLayoutManager.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
        }


        vm.getAllEvents(args.calendar)
    }

    override fun onNormal(data: EventListState) {
        binding.eventFragmentProgressBar.visibility = View.GONE
        myAdapter.updateList((data).events)
    }

    override fun onLoading(dataLoading: BaseExtraData?) {
        binding.eventFragmentProgressBar.visibility = View.VISIBLE
    }

    override fun onError(dataError: Throwable) {
        val msg = when (dataError) {
            is HttpException ->
                if (dataError.code() == 401) { getString(R.string.invalid_credentials) }
                else {  getString(R.string.networl_error) + dataError.code().toString() }
            //is UnknownHostException -> "Please, Internet connection needed !!"
            else -> getString(R.string.unknow_error)
        }
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_LONG).show();
    }


}