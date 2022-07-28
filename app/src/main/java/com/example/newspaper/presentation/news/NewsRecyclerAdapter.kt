package com.example.newspaper.presentation.news

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.example.newspaper.R
import com.example.newspaper.data.database.Article
import com.example.newspaper.data.database.ArticleDatabase
import com.example.newspaper.data.database.ArticleHistory
import com.example.newspaper.data.network.NewsResponse
import com.example.newspaper.presentation.full_article.FullArticleActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsRecyclerAdapter(
    private val onItemClick: (Article) -> Unit
): RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder>() {

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
         private val db = ArticleDatabase.getInstance(itemView.context)

         init {
             favoriteToggle.isChecked = item?.let { db.articleDao().checkArticleIsFavorite(it.articleId) } == true

             itemView.setOnClickListener {
                 item?.let {
                         it1 -> onItemClick.invoke(it1)
                     addArticleToHistory(item!!)
                 }
             }
             favoriteToggle.setOnCheckedChangeListener { _, checked ->
                 if(checked) {
                     item?.let { setArticleToFavorite(it) }
                 }
                 else {
                     item?.let { setArticleToNonFavorite(it) }
                 }
             }
         }

         private fun addArticleToHistory(article: Article) {
             CoroutineScope(Dispatchers.IO).launch {
                 db.historyDao().add(ArticleHistory(article.articleId, article.publishedAt))
             }
         }

         private fun setArticleToFavorite(article: Article) {
             CoroutineScope(Dispatchers.IO).launch {
                 item?.let { db.articleDao().setArticleFavorite(article.articleId) }
             }
         }

         private fun setArticleToNonFavorite(article: Article) {
             CoroutineScope(Dispatchers.IO).launch {
                 item?.let { db.articleDao().setArticleNonFavorite(article.articleId) }
             }
         }

        fun setDataItems(item: Article) {
            this.item = item

            titleTextView.text = item.title

            if(item.source.name != null)
                authorTextView.text = item.source.name
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