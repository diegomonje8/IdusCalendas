package es.nauticapps.iduscalendas.presentation.events.edit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import es.nauticapps.iduscalendas.R
import es.nauticapps.iduscalendas.base.BaseExtraData
import es.nauticapps.iduscalendas.base.BaseFragment
import es.nauticapps.iduscalendas.databinding.FragmentEventEditBinding
import es.nauticapps.iduscalendas.presentation.calendar.add.FieldErrorException

@AndroidEntryPoint
class EventEditFragment : BaseFragment<EventEditListState, EventEditViewModel, FragmentEventEditBinding>() {

    lateinit var vm : EventEditViewModel

    override var viewModelClass: Class<EventEditViewModel> = EventEditViewModel::class.java

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentEventEditBinding = FragmentEventEditBinding::inflate

    private val args: EventEditFragmentArgs by navArgs()

    companion object {
        const val FIELD_KEY_SUMMARY = "title"
        const val FIELD_KEY_DESC = "description"
        const val CODE_UPDATE_RIGHT = 1000
        const val CODE_DELETE_RIGHT = 1001
        const val CODE_ADD_RIGHT = 1002
    }

    override fun setupView(viewModel: EventEditViewModel) {
        vm = viewModel

        setupEditTexts()
        setupButtons()



        vm.setCalendar(args.event, args.calendar.id)
    }

    override fun onNormal(data: EventEditListState) {

        binding.eventEditFragmentProgressBar.visibility = View.GONE
        binding.eventEditLayoutSummary.error = null
        binding.eventEditLayoutDesc.error = null
        binding.eventEditTextSummary.setText(data.summary)
        binding.eventEditTextDesc.setText(data.description)
        binding.eventEditTextLocation.setText(data.location)
        binding.eventEditTextStartDate.setText(data.start)
        binding.eventEditTextEndDate.setText(data.end)

    }

    override fun onLoading(dataLoading: BaseExtraData?) {
        dataLoading?.let {
            when (it.type) {
                CODE_UPDATE_RIGHT ->
                    MaterialAlertDialogBuilder(requireActivity())
                        .setCancelable(false)
                        .setTitle(getString(R.string.edit_calendar))
                        .setMessage("Your event updates successfully")
                        .setPositiveButton(getString(R.string.ok)) { _, _ ->

                        }
                        .show()
                CODE_DELETE_RIGHT ->
                    MaterialAlertDialogBuilder(requireActivity())
                        .setCancelable(false)
                        .setTitle(getString(R.string.delete_calendar))
                        .setMessage("Oops your event was delete !!")
                        .setPositiveButton(getString(R.string.ok)) { _, _ ->

                        }
                        .show()
                CODE_ADD_RIGHT ->
                    MaterialAlertDialogBuilder(requireActivity())
                        .setCancelable(false)
                        .setTitle(getString(R.string.delete_calendar))
                        .setMessage("A event was succefully addes")
                        .setPositiveButton(getString(R.string.ok)) { _, _ ->

                        }
                        .show()
                else -> {
                }
            }
        } ?: run {
            binding.eventEditFragmentProgressBar.visibility = View.VISIBLE
        }
    }

    override fun onError(dataError: Throwable) {
        if (dataError is FieldErrorException) {
            when (dataError.fieldName) {
                FIELD_KEY_SUMMARY -> binding.eventEditTextSummary.error = getString(R.string.error_field_required)
                FIELD_KEY_DESC -> binding.eventEditTextDesc.error = getString(R.string.error_field_required)
                else -> { }
            }
        }
    }

    /**
     * Setup view
     */
    private fun setupEditTexts() {

        binding.eventEditTextSummary.doOnTextChanged { inputext, _ ,_ ,_ ->
            vm.onActionSetSummary(inputext.toString())
        }

        binding.eventEditTextDesc.doOnTextChanged { inputext, _ ,_ ,_ ->
            vm.onActionSetDescription(inputext.toString())
        }

        binding.eventEditTextLocation.doOnTextChanged { inputext, _ ,_ ,_ ->
            vm.onActionSetLocation(inputext.toString())
        }

        binding.eventEditTextStartDate.doOnTextChanged { inputext, _ ,_ ,_ ->
            vm.onActionSetStart(inputext.toString())
        }

        binding.eventEditTextEndDate.doOnTextChanged { inputext, _ ,_ ,_ ->
            vm.onActionSetStop(inputext.toString())
        }
    }



    private fun setupButtons() {

        binding.eventEditTitle.text = args.calendar.summary
        if (args.event.id.isEmpty()) {
            binding.eventEditAddButton.visibility = View.VISIBLE
            binding.eventEditDeleteButton.visibility = View.GONE
            binding.eventEditSaveButton.visibility = View.GONE
        }
        else {
            binding.eventEditAddButton.visibility = View.GONE
            binding.eventEditDeleteButton.visibility = View.VISIBLE
            binding.eventEditSaveButton.visibility = View.VISIBLE
        }

        binding.eventEditSaveButton.setOnClickListener {
            vm.onActionSaveEvent()
        }

        binding.eventEditDeleteButton.setOnClickListener {
            vm.onActionDeleteCalendar()
        }

        binding.eventEditAddButton.setOnClickListener {
            vm.onActionAddEvent()
        }

    }



}