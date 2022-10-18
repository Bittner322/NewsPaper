package com.example.newspaper.presentation.categories_activity

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.example.newspaper.R
import com.example.newspaper.data.repositories.models.CategoryData

class MyCategoriesRecyclerAdapter(
    private val onToggleChecked: (CategoryData) -> Unit,
    private val onToggleNonChecked: (CategoryData) -> Unit,
): RecyclerView.Adapter<MyCategoriesRecyclerAdapter.ViewHolder>() {

    private val data = mutableListOf<CategoryData>()

    class ViewHolder(
        itemView: View,
        private val onToggleChecked: (CategoryData) -> Unit,
        private val onToggleNonChecked: (CategoryData) -> Unit,
    ) : RecyclerView.ViewHolder(itemView) {

        private val toggle: ToggleButton = itemView.findViewById(R.id.categoryRecyclerToggle)
        private var item: CategoryData? = null

        init {
            toggle.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    item?.let { onToggleChecked(it) }
                } else {
                    item?.let { onToggleNonChecked(it) }
                }
            }
        }

        fun setDataItems(item: CategoryData) {
            this.item = item
            toggle.textOff = item.categoryName
            toggle.textOn = item.categoryName
            toggle.text = item.categoryName
            toggle.isChecked = item.isSelected
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<CategoryData>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_categories, parent, false)
        return ViewHolder(itemView, onToggleChecked, onToggleNonChecked)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setDataItems(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}