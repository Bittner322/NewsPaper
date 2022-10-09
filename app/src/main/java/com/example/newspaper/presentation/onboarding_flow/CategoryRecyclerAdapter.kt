package com.example.newspaper.presentation.onboarding_flow

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.example.newspaper.R
import com.example.newspaper.data.database.models.Category
import com.example.newspaper.data.repositories.models.CategoryCard

class CategoryRecyclerAdapter(
    private val onToggleChecked: (CategoryCard) -> Unit,
    private val onToggleNonChecked: (CategoryCard) -> Unit,
): RecyclerView.Adapter<CategoryRecyclerAdapter.ViewHolder>() {

    private val data = mutableListOf<CategoryCard>()

    class ViewHolder(
        itemView: View,
        private val onToggleChecked: (CategoryCard) -> Unit,
        private val onToggleNonChecked: (CategoryCard) -> Unit,
    ): RecyclerView.ViewHolder(itemView) {

        private val toggle: ToggleButton = itemView.findViewById(R.id.categoryRecyclerToggle)
        private var item: CategoryCard? = null

        init {
            toggle.setOnCheckedChangeListener { _, isChecked ->
                if(isChecked) {
                    item?.let { onToggleChecked(it) }
                }
                else {
                    item?.let { onToggleNonChecked(it) }
                }
            }
        }

        fun setDataItems(item: CategoryCard) {
            this.item = item
            toggle.textOff = itemView.context.resources.getString(item.categoryName)
            toggle.textOn = itemView.context.resources.getString(item.categoryName)
            toggle.text = itemView.context.resources.getString(item.categoryName)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<CategoryCard>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_categories, parent, false)
        return ViewHolder(itemView, onToggleChecked, onToggleNonChecked)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setDataItems(data[position])
    }

    override fun getItemCount() : Int {
        return data.size
    }
}