package com.example.test.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.annotation.LayoutRes
import androidx.lifecycle.LiveData
import androidx.viewbinding.ViewBinding
import com.example.test.presentation.utills.UIState


abstract class BaseFragment<ViewModel : BaseViewModel, Binding : ViewBinding>(
    @LayoutRes layoutId: Int,
) : Fragment(layoutId) {

    protected abstract val viewModel: ViewModel
    protected abstract val binding: Binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
        setupSubscribes()
        setupRequests()
        setupListeners()
    }

    protected open fun initialize() {
    }

    protected open fun setupListeners() {
    }

    protected open fun setupRequests() {
    }

    protected open fun setupSubscribes() {
    }

    protected fun <T> LiveData<UIState<T>>.observeUIState(
        action: (UIState<T>) -> Unit,
    ) {
        this@observeUIState.observe(viewLifecycleOwner) {
            action(it)
        }
    }

}