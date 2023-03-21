package com.example.test.presentation.fragments.currency

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.test.R
import com.example.test.databinding.FragmentCurrencyBinding
import com.example.test.presentation.entities.toLocal
import com.example.test.presentation.fragments.BaseFragment
import com.example.test.presentation.utills.*
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class CurrencyFragment : BaseFragment<CurrencyViewModel,FragmentCurrencyBinding>(R.layout.fragment_currency) {

    override val binding: FragmentCurrencyBinding by viewBinding(FragmentCurrencyBinding::bind)
    override val viewModel: CurrencyViewModel by viewModels()
    private val currentTime = getCurrentTime()


    override fun setupListeners() {
        updateClick()
        clickOpenMap()
    }

    private fun updateClick() {
        binding.btnGetCurrency.setOnClickListener {
            viewModel.getDataRemote()
        }
    }

    private fun clickOpenMap() {
        binding.nextFragment.setOnClickListener {
            findNavController().navigate(R.id.action_currencyFragment_to_mapFragment)
        }
    }

    override fun setupSubscribes() {
        observeLocal()
        observeRemote()
    }

    private fun observeRemote() = with(binding) {
        viewModel.stateCurrencyRemote.observe(viewLifecycleOwner){
            when(it){
                is UIState.Empty -> {
                    pbRemote.visibility = View.GONE
                }
                is UIState.Error -> {
                    showToastShort(it.error)
                }
                is UIState.Loading -> {
                    pbRemote.visibility = View.VISIBLE
                }
                is UIState.Success -> {
                    pbRemote.visibility = View.GONE
                    remoteValue.text = it.data.secondNominal
                    remoteDate.text = it.data.date
                    viewModel.update(it.data.toLocal(currentTime))
                }
            }
        }
    }

    private fun observeLocal() = with(binding){
        viewModel.stateCurrencyLocal.observeUIState {
            when(it){
                is UIState.Empty -> {
                    pbRemote.visibility = View.GONE
                    localValue.text = DONTHAVELOCALDATA
                }
                is UIState.Error -> {
                    showToastShort(it.error)
                }
                is UIState.Loading -> {
                    pbRemote.visibility = View.VISIBLE
                }
                is UIState.Success -> {
                    pbLocal.visibility = View.GONE
                    lastSignText.text = it.data.lastSign
                    localValue.text = it.data.secondNominal
                    localDate.text = it.data.date
                }
            }
        }
    }
    companion object{
        const val DONTHAVELOCALDATA = "Local data is empty"
    }
}