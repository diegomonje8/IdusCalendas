package es.nauticapps.iduscalendas.presentation.calendar.edit

import android.annotation.TargetApi
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
import es.nauticapps.iduscalendas.databinding.FragmentCalendarEditBinding
import es.nauticapps.iduscalendas.presentation.calendar.add.FieldErrorException

@AndroidEntryPoint
class CalendarEditFragment : BaseFragment<CalendarEditListState, CalendarEditViewModel, FragmentCalendarEditBinding>() {

    lateinit var vm : CalendarEditViewModel

    override var viewModelClass: Class<CalendarEditViewModel> = CalendarEditViewModel::class.java

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCalendarEditBinding = FragmentCalendarEditBinding::inflate

    private val args: CalendarEditFragmentArgs by navArgs()

    companion object {
        const val FIELD_KEY_TITLE = "title"
        const val FIELD_KEY_DESC = "description"
        const val CODE_UPDATE_RIGHT = 1000
        const val CODE_DELETE_RIGHT = 1001
    }

    override fun setupView(viewModel: CalendarEditViewModel) {
        vm = viewModel

        setupEditTexts()
        setupButtons()

        vm.setCalendar(args.calendar)
    }

    override fun onNormal(data: CalendarEditListState) {
        binding.calendarEditFragmentProgressBar.visibility = View.GONE
        binding.calendarEditLayoutInputTitle.error = null
        binding.calendarEditInputTitle.setText(data.title)
        binding.calendarEditLayoutInputDesc.error = null
        binding.calendarEditInputDesc.setText(data.description)
        binding.calendarEditTimeZone.text = data.calendar?.timeZone ?: ""
        binding.calendarEditRole.text = data.calendar?.accessRole ?: ""
        if (data.calendar?.accessRole != "owner" ) {
            binding.calendarEditDeleteButton.isEnabled = false
            binding.calendarEditSaveButton.isEnabled = false
        }
    }

    override fun onLoading(dataLoading: BaseExtraData?) {
        dataLoading?.let {
            when (it.type) {
                CODE_UPDATE_RIGHT ->
                    MaterialAlertDialogBuilder(requireActivity())
                            .setCancelable(false)
                            .setTitle(getString(R.string.edit_calendar))
                            .setMessage("Your calendar updates successfully")
                            .setPositiveButton(getString(R.string.ok)) { _, _ ->

                            }
                            .show()
                CODE_DELETE_RIGHT ->
                    MaterialAlertDialogBuilder(requireActivity())
                            .setCancelable(false)
                            .setTitle(getString(R.string.delete_calendar))
                            .setMessage("Oops your calendar was delete !!")
                            .setPositiveButton(getString(R.string.ok)) { _, _ ->

                            }
                            .show()
                else -> {
                }
            }
        } ?: run {
            binding.calendarEditFragmentProgressBar.visibility = View.VISIBLE
        }
    }

    override fun onError(dataError: Throwable) {
        if (dataError is FieldErrorException) {
            when (dataError.fieldName) {
                FIELD_KEY_TITLE -> binding.calendarEditInputTitle.error = getString(R.string.error_field_required)
                FIELD_KEY_DESC -> binding.calendarEditInputDesc.error = getString(R.string.error_field_required)
                else -> { }
            }
        }
    }

    /**
     * Setup view
     */
    private fun setupEditTexts() {

        binding.calendarEditInputTitle.doOnTextChanged { inputext, _ ,_ ,_ ->
            vm.onActionSetTitle(inputext.toString())
        }

        binding.calendarEditInputDesc.doOnTextChanged { inputext, _ ,_ ,_ ->
            vm.onActionSetDesc(inputext.toString())
        }
    }



    private fun setupButtons() {
        binding.calendarEditSaveButton.setOnClickListener {
            vm.onActionSaveCalendar()
        }

        binding.calendarEditDeleteButton.setOnClickListener {
            vm.onActionDeleteCalendar()
        }

    }

}