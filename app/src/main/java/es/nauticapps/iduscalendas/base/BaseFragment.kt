package es.nauticapps.iduscalendas.base

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

abstract class BaseFragment<VS: BaseViewState, VM: BaseViewModel<VS>, B: ViewDataBinding> : Fragment() {

    /**
     * ViewModel vars
     */
    lateinit var viewModel: VM

    abstract var viewModelClass : Class<VM>

    /**
     * Data Binding
     */

    protected lateinit var binding : B

    abstract val bindingInflater : (LayoutInflater, ViewGroup?, Boolean) -> B

    /**
     * OnCreate
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = bindingInflater.invoke(inflater, container, false)
        return binding.root
    }

    /**
     * OnPause
     */
    override fun onPause() {
        super.onPause()

        //  Hide keyboard
        (requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(view?.windowToken, 0)
    }

    /**
     *  onResume
     *  Get or create ViewModel
     */
    override fun onResume() {
        super.onResume()

        //Get or create view models
        viewModel = ViewModelProvider(this).get(viewModelClass)

        setupView(viewModel)

        viewModel.getObservableState().observe(viewLifecycleOwner)  { state ->
            onNormal(state.data)
            when(state) {
                is BaseState.Loading -> onLoading(state.dataLoading)
                is BaseState.Error -> onError(state.dataError)
                else -> {}
            }
        }



        //then... Fragment starts!
        viewModel.onStart()

    }

    /**
     * Set Up View
     * ViewModel already exists
     */
    abstract fun setupView(viewModel: VM)

    /**
     * Manage State functions
     */
    abstract fun onNormal(data: VS)
    abstract fun onLoading(dataLoading: BaseExtraData?)
    abstract fun onError(dataError: Throwable)

}