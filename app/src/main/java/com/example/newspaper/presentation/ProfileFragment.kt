package com.example.newspaper.presentation

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.newspaper.R
import com.example.newspaper.databinding.FragmentProfileBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class ProfileFragment : Fragment() {

    private val profileViewModel: ProfileViewModel by viewModels()

    private var _binding: FragmentProfileBinding? = null
    private val binding by lazy { _binding!! }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(layoutInflater)
        val view = binding.root

        val adapter = ProfileRecyclerAdapter()

        binding.profileRecyclerView.adapter = adapter

        lifecycleScope.launchWhenStarted {
            profileViewModel.cards
                .onEach {
                    adapter.setData(it)
                }
                .launchIn(this)
        }

        return view
    }


}