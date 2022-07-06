package com.example.newspaper.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.newspaper.R
import com.example.newspaper.data.ProfileCard

class ProfileRecyclerAdapter: RecyclerView.Adapter<ProfileRecyclerAdapter.ViewHolder>() {

    private val data = mutableListOf<ProfileCard>()

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val profileRecyclerImageView : ImageView = itemView.findViewById(R.id.profileRecyclerImageView)
        private val profileRecyclerTextView: TextView = itemView.findViewById(R.id.profileRecyclerTextView)
        private val profileCardView: CardView = itemView.findViewById(R.id.profileRecyclerCardView)

        fun setDataItems(item: ProfileCard) {
            profileRecyclerImageView.setImageResource(item.picture)
            profileRecyclerTextView.text = item.name
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<ProfileCard>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.profile_recycler_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setDataItems(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}