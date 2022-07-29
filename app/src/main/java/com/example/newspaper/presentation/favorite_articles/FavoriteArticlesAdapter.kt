package com.example.newspaper.presentation.favorite_articles

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.example.newspaper.R
import com.example.newspaper.data.database.Article

class FavoriteArticlesAdapter(
    private val onToggleChecked: (Article) -> Unit,
    private val onToggleNonChecked: (Article) -> Unit,
): RecyclerView.Adapter<FavoriteArticlesAdapter.ViewHolder>() {

    var onItemClick: ((Article) -> Unit)? = null
    private val data = mutableListOf<Article>()

    inner class ViewHolder(
        itemView: View,
        private val onToggleChecked: (Article) -> Unit,
        private val onToggleNonChecked: (Article) -> Unit,
    ): RecyclerView.ViewHolder(itemView) {

        private val titleTextView : TextView = itemView.findViewById(R.id.titleTextView)
        private val authorTextView: TextView = itemView.findViewById(R.id.authorTextView)
        private val contentTextView: TextView = itemView.findViewById(R.id.contentTextView)
        private val favoriteToggle: ToggleButton = itemView.findViewById(R.id.favoriteToggleButton)
        private var item: Article? = null

        fun setDataItems(item: Article) {

            this.item = item

            titleTextView.text = item.title

            if(item.source.name != null)
                authorTextView.text = item.source.name
            else
                authorTextView.text = "Unknown resource"

            contentTextView.text = item.description

            favoriteToggle.isChecked = item.isFavorite
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(data[adapterPosition])
            }


            favoriteToggle.setOnCheckedChangeListener { _, isChecked ->
                if(isChecked) {
                    item?.let { onToggleChecked(it) }
                }
                else {
                    item?.let { onToggleNonChecked(it) }
                }
            }
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
        return ViewHolder(itemView, onToggleChecked, onToggleNonChecked)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setDataItems(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}