package com.example.newspaper.presentation.categories_activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.newspaper.data.repositories.models.CategoryData
import com.example.newspaper.databinding.ActivityMyCategoriesBinding
import com.example.newspaper.di.ComponentStorage
import com.example.newspaper.di.feature_components.DaggerMyCategoriesActivityComponent
import com.example.newspaper.di.provideRootComponent
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MyCategoriesActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: MyCategoriesActivityViewModelFactory

    private val viewModel: MyCategoriesViewModel by viewModels { viewModelFactory }

    private val daggerComponentKey = "MyCategoriesActivity"

    private var _binding: ActivityMyCategoriesBinding? = null
    private val binding: ActivityMyCategoriesBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ComponentStorage.provideComponent(daggerComponentKey) {
            DaggerMyCategoriesActivityComponent.factory().create(
                appComponent = ComponentStorage.provideRootComponent()
            )
        }.inject(this)

        _binding = ActivityMyCategoriesBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        binding.categoriesBackButton.setOnClickListener {
            finish()
        }

        val adapter = MyCategoriesRecyclerAdapter(
            onToggleChecked = ::setCategoryIsSelected,
            onToggleNonChecked = ::setCategoryIsNonSelected
        )

        binding.myCategoriesRecyclerView.adapter = adapter

        lifecycleScope.launchWhenStarted {
            viewModel.categories
                .onEach { adapter.setData(it) }
                .launchIn(this)
        }
    }

    private fun setCategoryIsSelected(categoryCard: CategoryData) {
        viewModel.setCategoryIsSelected(categoryCard.id)
        viewModel.clearArticlesTable()
    }

    private fun setCategoryIsNonSelected(categoryCard: CategoryData) {
        viewModel.setCategoryIsNotSelected(categoryCard.id)
        viewModel.clearArticlesTable()
    }

    override fun onDestroy() {

        if (!isChangingConfigurations) {
            ComponentStorage.clearComponent(daggerComponentKey)
        }

        super.onDestroy()
    }
}