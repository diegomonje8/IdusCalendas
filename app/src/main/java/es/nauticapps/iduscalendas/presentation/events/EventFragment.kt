package es.nauticapps.iduscalendas.presentation.events

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import es.nauticapps.iduscalendas.R
import es.nauticapps.iduscalendas.base.BaseExtraData
import es.nauticapps.iduscalendas.base.BaseFragment
import es.nauticapps.iduscalendas.databinding.FragmentEventBinding
import es.nauticapps.iduscalendas.domain.EventDomainModel
import retrofit2.HttpException

@AndroidEntryPoint
class EventFragment : BaseFragment<EventListState, EventViewModel, FragmentEventBinding>() {

    lateinit var myAdapter : EventFragmentAdapter
    lateinit var vm : EventViewModel

    override var viewModelClass: Class<EventViewModel> = EventViewModel::class.java

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentEventBinding = FragmentEventBinding::inflate

    override fun setupView(viewModel: EventViewModel) {
        vm = viewModel

        myAdapter = EventFragmentAdapter(listOf<EventDomainModel>()) { event ->
            //findNavController().navigate(CalendarFragmentDirections.actionCalendarFragmentToCalendarEditFragment(calendar))
        }

        binding.eventFragmentFloatingButton.setOnClickListener(View.OnClickListener {
            //findNavController().navigate(CalendarFragmentDirections.actionCalendarFragmentToCalendarAddFragment())
        })

        val myRecyclerView : RecyclerView = binding.eventFragmentRecycler
        myRecyclerView.apply {
            adapter = myAdapter
            layoutManager =  LinearLayoutManager(
                    context, LinearLayoutManager.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
        }


        vm.getAllEvents()
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