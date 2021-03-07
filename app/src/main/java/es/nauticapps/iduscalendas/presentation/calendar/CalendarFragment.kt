package es.nauticapps.iduscalendas.presentation.calendar

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import es.nauticapps.iduscalendas.R
import es.nauticapps.iduscalendas.base.BaseExtraData
import es.nauticapps.iduscalendas.base.BaseFragment
import es.nauticapps.iduscalendas.data.Calendar
import es.nauticapps.iduscalendas.databinding.FragmentCalendarBinding
import es.nauticapps.iduscalendas.domain.CalendarDomainModel
import retrofit2.HttpException
import java.net.UnknownHostException

@AndroidEntryPoint
class CalendarFragment : BaseFragment<CalendarListState, CalendarViewModel, FragmentCalendarBinding>() {

    lateinit var myAdapter : CalendarFragmentAdapter
    lateinit var vm : CalendarViewModel

    override var viewModelClass: Class<CalendarViewModel> = CalendarViewModel::class.java

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCalendarBinding = FragmentCalendarBinding::inflate

    override fun setupView(viewModel: CalendarViewModel) {
        vm = viewModel

        myAdapter = CalendarFragmentAdapter(listOf<CalendarDomainModel>())
            //findNavController().navigate(HomeFragmentDirections.actionHomeFragment2ToArtistFragment(artist))

        val myRecyclerView : RecyclerView = binding.calendarFragmentRecycler
        myRecyclerView.apply {
            adapter = myAdapter
            layoutManager =  LinearLayoutManager(
                context, LinearLayoutManager.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
        }


        vm.getAllCalendars()
    }

    override fun onNormal(data: CalendarListState) {
        binding.calendarFragmentProgressBar.visibility = View.GONE
        myAdapter.updateList((data).calendars)
    }

    override fun onLoading(dataLoading: BaseExtraData?) {
        binding.calendarFragmentProgressBar.visibility = View.VISIBLE
    }

    override fun onError(dataError: Throwable) {
        val msg = when (dataError) {
            is HttpException ->
                if (dataError.code() == 401) { "Invalid Credentials" }
                else {  "Network Error: " + dataError.code().toString() }
            //is UnknownHostException -> "Please, Internet connection needed !!"
            else -> "Oops Unknown Error, Please try later !!"
        }
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_LONG).show();
    }


}