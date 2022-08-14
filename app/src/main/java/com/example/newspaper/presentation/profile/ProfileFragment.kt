package com.example.newspaper.presentation.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.newspaper.data.repositories.models.ProfileCard
import com.example.newspaper.databinding.FragmentProfileBinding
import com.example.newspaper.presentation.dialogs.UsernameChangeDialog
import com.example.newspaper.presentation.faq.FaqActivity
import com.example.newspaper.presentation.favorite_articles.FavoriteArticleActivity
import com.example.newspaper.presentation.history.HistoryActivity
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ProfileFragment : Fragment() {

    private val profileViewModel: ProfileViewModel by viewModels()

    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ProfileRecyclerAdapter(
            onItemClick = ::onProfileCardClick
        )

        binding.profileRecyclerView.adapter = adapter

        lifecycleScope.launchWhenStarted {
            profileViewModel.cards
                .onEach {
                    adapter.setData(it)
                }
                .launchIn(this)
        }

        binding.userNameTextView.setOnClickListener {
            val dialogFragment = UsernameChangeDialog()
            val manager = parentFragmentManager
            dialogFragment.show(manager, "userNameChangeDialog")
        }
    }

    private fun onProfileCardClick(profileCard: ProfileCard) {
        when (profileCard) {
            ProfileCard.FAVOURITES -> {
                val toFavoriteArticleActivityIntent = Intent(requireActivity(), FavoriteArticleActivity::class.java)
                startActivity(toFavoriteArticleActivityIntent)
            }
            ProfileCard.HISTORY -> {
                val toHistoryActivityIntent = Intent(requireActivity(), HistoryActivity::class.java)
                startActivity(toHistoryActivityIntent)
            }
            ProfileCard.FAQ -> {
                val toFaqActivityIntent = Intent(requireActivity(), FaqActivity::class.java)
                startActivity(toFaqActivityIntent)
            }
        }
    }
}