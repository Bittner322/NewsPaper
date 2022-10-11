package com.example.newspaper.presentation.onboarding_flow

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.newspaper.MyApplication
import com.example.newspaper.R
import com.example.newspaper.data.repositories.models.CategoryCard
import com.example.newspaper.databinding.FragmentOnboardingNumberThreeBinding
import com.example.newspaper.di.ComponentStorage
import com.example.newspaper.di.feature_components.DaggerOnboardingFragmentNumberThreeComponent
import com.example.newspaper.di.provideRootComponent
import com.example.newspaper.presentation.main_activity.MainActivity
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

private const val FIRST_LAUNCH = "firstLaunch"
private const val PREFS = "prefs"

class OnboardingFragmentNumberThree : Fragment() {

    private var _binding: FragmentOnboardingNumberThreeBinding? = null
    private val binding: FragmentOnboardingNumberThreeBinding
        get() = _binding!!

    @Inject
    lateinit var viewModelFactory: CategoryFragmentViewModelFactory

    private val viewModel: CategoryFragmentViewModel by viewModels { viewModelFactory }

    private val daggerComponentKey = "OnboardingFragmentNumberThree"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ComponentStorage.provideComponent(daggerComponentKey) {
            DaggerOnboardingFragmentNumberThreeComponent.factory().create(
                appComponent = ComponentStorage.provideRootComponent()
            )
        }.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingNumberThreeBinding.inflate(layoutInflater)

        val sharedPref = MyApplication.applicationContext().getSharedPreferences(PREFS, Context.MODE_PRIVATE)

        binding.onboardingStartButton.setOnClickListener {

            if(viewModel.getCountOfSelectedCategoriesFromDatabase() == 0) {
                Toast.makeText(
                    MyApplication.applicationContext(),
                    resources.getString(R.string.toast_you_did_not_picked_any_category),
                    LENGTH_LONG,
                ).show()
            }
            else {

                sharedPref.edit {
                    putBoolean(FIRST_LAUNCH, false)
                }

                val toMainActivityIntent = Intent(requireActivity(), MainActivity::class.java)
                requireActivity().startActivity(toMainActivityIntent)
                requireActivity().finish()
            }

        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CategoryRecyclerAdapter(
            onToggleChecked = ::setCategoryIsSelected,
            onToggleNonChecked = ::setCategoryIsNotSelected,
        )

        binding.categoriesRecycler.adapter = adapter

        lifecycleScope.launchWhenStarted {
            viewModel.categories
                .onEach { adapter.setData(it) }
                .launchIn(this)

        }
    }

    private fun setCategoryIsSelected(categoryCard: CategoryCard) {
        viewModel.setCategoryIsSelected(categoryCard.id)
    }

    private fun setCategoryIsNotSelected(categoryCard: CategoryCard) {
        viewModel.setCategoryIsNotSelected(categoryCard.id)
    }

    override fun onDetach() {

        if(!requireActivity().isChangingConfigurations) {
            ComponentStorage.clearComponent(daggerComponentKey)
        }

        val sharedPref = MyApplication.applicationContext().getSharedPreferences(PREFS, Context.MODE_PRIVATE)

        if(!sharedPref.contains(FIRST_LAUNCH)) {
            viewModel.deleteAllCategoriesFromDatabase()
        }

        super.onDetach()
    }
}