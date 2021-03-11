package es.nauticapps.iduscalendas.presentation.calendar.add

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import es.nauticapps.iduscalendas.R
import es.nauticapps.iduscalendas.base.BaseExtraData
import es.nauticapps.iduscalendas.base.BaseFragment
import es.nauticapps.iduscalendas.databinding.FragmentCalendarAddBinding
import es.nauticapps.iduscalendas.presentation.calendar.edit.CalendarEditFragmentArgs

@AndroidEntryPoint
class CalendarAddFragment : BaseFragment<CalendarAddListState, CalendarAddViewModel, FragmentCalendarAddBinding>() {

    lateinit var vm : CalendarAddViewModel

    override var viewModelClass: Class<CalendarAddViewModel> = CalendarAddViewModel::class.java

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCalendarAddBinding = FragmentCalendarAddBinding::inflate

    companion object {
        const val FIELD_KEY_TITLE = "title"
        const val CODE_ALL_RIGHT = 1000
    }


    override fun setupView(viewModel: CalendarAddViewModel) {
        vm = viewModel

        setupEditText()
        setupButton()

    }

    override fun onNormal(data: CalendarAddListState) {
        binding.calendarAddFragmentProgressBar.visibility = View.GONE
        binding.calendarAddLayoutInputtText.error = null
        binding.calendarAddInputtText.setText(data.name)
    }

    override fun onLoading(dataLoading: BaseExtraData?) {
        dataLoading?.let {
            when (it.type) {
                CODE_ALL_RIGHT ->
                    MaterialAlertDialogBuilder(requireActivity())
                            .setCancelable(false)
                            .setTitle(getString(R.string.add_calendar))
                            .setMessage(getString(R.string.new_calendar_added))
                            .setPositiveButton(getString(R.string.ok)) { _, _ ->
                            }
                            .show()
                else -> {
                }
            }
        } ?: run {
            binding.calendarAddFragmentProgressBar.visibility = View.VISIBLE
        }
    }

    override fun onError(dataError: Throwable) {
        if (dataError is FieldErrorException) {
            when (dataError.fieldName) {
                FIELD_KEY_TITLE -> binding.calendarAddLayoutInputtText.error = getString(R.string.error_field_required)
                else -> { }
            }
        }
    }

    /**
     * Setup view
     */
    private fun setupEditText() {

        binding.calendarAddInputtText.doOnTextChanged { inputext, _ ,_ ,_ ->
            vm.onActionSetTitle(inputext.toString())
        }
    }



    private fun setupButton() {
        binding.calendarAddButton.setOnClickListener {
            vm.onActionAddCalendar()
        }

    }

}