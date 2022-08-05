package com.example.newspaper.presentation.history

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.example.newspaper.R
import com.example.newspaper.data.database.Article

class HistoryRecyclerAdapter(
    private val onItemClick: (Article) -> Unit
): RecyclerView.Adapter<HistoryRecyclerAdapter.ViewHolder>() {

    private val data = mutableListOf<Article>()

    class ViewHolder(
        itemView: View,
        onItemClick: (Article) -> Unit
    ): RecyclerView.ViewHolder(itemView) {

        private val titleTextView : TextView = itemView.findViewById(R.id.titleTextView)
        private val authorTextView: TextView = itemView.findViewById(R.id.authorTextView)
        private val contentTextView: TextView = itemView.findViewById(R.id.contentTextView)
        private val favoriteToggle: ToggleButton = itemView.findViewById(R.id.favoriteToggleButton)
        private var item: Article? = null

        init {

            favoriteToggle.isEnabled = false

            itemView.setOnClickListener {
                item?.let { it1 -> onItemClick.invoke(it1) }
            }

        }

        fun setDataItems(item: Article) {
            this.item = item

            titleTextView.text = item.title

            if(item.author != null)
                authorTextView.text = item.author
            else
                authorTextView.text = "Unknown resource"

            contentTextView.text = item.description
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<Article>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return ViewHolder(itemView, onItemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setDataItems(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}