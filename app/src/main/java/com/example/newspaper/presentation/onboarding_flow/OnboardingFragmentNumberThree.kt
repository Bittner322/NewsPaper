package com.example.newspaper.presentation.onboarding_flow

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.newspaper.MyApplication
import com.example.newspaper.data.database.Category
import com.example.newspaper.data.repositories.models.CategoryCard
import com.example.newspaper.databinding.FragmentOnboardingNumberThreeBinding
import com.example.newspaper.di.DaggerOnboardingFragmentNumberThreeComponent
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

        MyApplication.provideComponent(daggerComponentKey) {
            DaggerOnboardingFragmentNumberThreeComponent.factory().create(
                appComponent = MyApplication.appComponent
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

            sharedPref.edit {
                putBoolean(FIRST_LAUNCH, false)
            }

            val toMainActivityIntent = Intent(activity, MainActivity::class.java)
            activity?.startActivity(toMainActivityIntent)
            activity?.finish()

        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CategoryRecyclerAdapter(
            onToggleChecked = ::addCategory,
            onToggleNonChecked = ::deleteCategory,
        )

        binding.categoriesRecycler.adapter = adapter

        lifecycleScope.launchWhenStarted {
            viewModel.categories
                .onEach { adapter.setData(it) }
                .launchIn(this)
        }
    }

    private fun addCategory(categoryCard: CategoryCard) {
        viewModel.addCategoryIntoDatabase(Category(categoryCard.name.lowercase()))
    }

    private fun deleteCategory(categoryCard: CategoryCard) {
        viewModel.deleteCategoryFromDatabase(Category(categoryCard.name.lowercase()))
    }
}