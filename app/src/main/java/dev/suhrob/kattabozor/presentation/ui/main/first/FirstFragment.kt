package dev.suhrob.kattabozor.presentation.ui.main.first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import dev.suhrob.kattabozor.databinding.FragmentFirstBinding
import dev.suhrob.kattabozor.presentation.ui.BaseFragment
import dev.suhrob.kattabozor.presentation.ui.main.first.adapter.OfferAdapter
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class FirstFragment : BaseFragment<FragmentFirstBinding>() {
    override fun createBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentFirstBinding = FragmentFirstBinding.inflate(inflater)

    private val viewModel: FirstViewModel by viewModels()

    private val adapter by lazy {
        OfferAdapter(requireContext())
    }

    override fun created(view: View, savedInstanceState: Bundle?) {
        initAdapter()

    }

    private fun initAdapter() {
        binding.offerRv.adapter = adapter
        getOffers()
    }

    private fun getOffers() {
        lifecycleScope.launchWhenStarted {
            showMainProgress()
            viewModel.offers.collectLatest {
                it.data?.let { data ->
                    hideMainProgress()
                    adapter.submitList(data.offers)
                }
                if (it.error.isNotBlank()) {
                    hideMainProgress()
                    Toast.makeText(requireContext(), "Error: ${it.error}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}