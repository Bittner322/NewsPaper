package com.example.newspaper.presentation.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.newspaper.MyApplication
import com.example.newspaper.data.repositories.models.ProfileCard
import com.example.newspaper.databinding.FragmentProfileBinding
import com.example.newspaper.di.ComponentStorage
import com.example.newspaper.di.feature_components.DaggerProfileFragmentComponent
import com.example.newspaper.di.provideRootComponent
import com.example.newspaper.presentation.dialogs.UsernameChangeDialog
import com.example.newspaper.presentation.faq.FaqActivity
import com.example.newspaper.presentation.favorite_articles.FavoriteArticleActivity
import com.example.newspaper.presentation.history.HistoryActivity
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class ProfileFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ProfileViewModelFactory

    private val profileViewModel: ProfileViewModel by viewModels { viewModelFactory }

    private val daggerComponentKey = "ProfileFragment"

    
    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ComponentStorage.provideComponent(daggerComponentKey) {
            DaggerProfileFragmentComponent.factory().create(
                appComponent = ComponentStorage.provideRootComponent()
            )
        }.inject(this)
    }

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
                .onEach { adapter.setData(it) }
                .launchIn(this)

            profileViewModel.usernameStateFlow
                .onEach { binding.userNameTextView.text = it }
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

    override fun onDetach() {

        if(!requireActivity().isChangingConfigurations) {
            ComponentStorage.clearComponent(daggerComponentKey)
        }

        super.onDetach()
    }
}