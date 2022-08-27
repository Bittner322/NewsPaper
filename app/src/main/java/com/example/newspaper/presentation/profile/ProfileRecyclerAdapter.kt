package com.example.newspaper.presentation.profile

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newspaper.R
import com.example.newspaper.data.repositories.models.ProfileCard

class ProfileRecyclerAdapter(
    private val onItemClick: (ProfileCard) -> Unit,
): RecyclerView.Adapter<ProfileRecyclerAdapter.ViewHolder>() {

    private val data = mutableListOf<ProfileCard>()

    class ViewHolder(
        itemView: View,
        onItemClick: (ProfileCard) -> Unit
    ): RecyclerView.ViewHolder(itemView) {

        private val profileRecyclerImageView : ImageView = itemView.findViewById(R.id.profileRecyclerImageView)
        private val profileRecyclerTextView: TextView = itemView.findViewById(R.id.profileRecyclerTextView)
        private var item: ProfileCard? = null

        init {
            itemView.setOnClickListener {
                item?.let { onItemClick.invoke(it) }
            }
        }

        fun setDataItems(item: ProfileCard) {
            this.item = item
            profileRecyclerImageView.setImageResource(item.iconRes)
            profileRecyclerTextView.text = itemView.context.resources.getString(item.cardNameRes)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<ProfileCard>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_profile, parent, false)
        return ViewHolder(itemView, onItemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setDataItems(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}