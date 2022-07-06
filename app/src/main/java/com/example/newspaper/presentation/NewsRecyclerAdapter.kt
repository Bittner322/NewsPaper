package com.example.newspaper.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.newspaper.R
import com.example.newspaper.data.NewsResponse

class NewsRecyclerAdapter: RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder>() {

    var onItemClick: ((NewsResponse.Articles) -> Unit)? = null
    private val data = mutableListOf<NewsResponse.Articles>()

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val titleTextView : TextView = itemView.findViewById(R.id.titleTextView)
        private val authorTextView: TextView = itemView.findViewById(R.id.authorTextView)
        private val contentTextView: TextView = itemView.findViewById(R.id.contentTextView)

        fun setDataItems(item: NewsResponse.Articles) {
            titleTextView.text = item.title

            if(item.source.name != null)
                authorTextView.text = item.source.name
            else
                authorTextView.text = "Unknown resource"

            contentTextView.text = item.description
        }

        init {
            itemView.setOnClickListener() {
                onItemClick?.invoke(data[adapterPosition])
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<NewsResponse.Articles>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.news_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setDataItems(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}