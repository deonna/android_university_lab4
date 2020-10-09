package com.codepath.recyclerviewlab

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codepath.recyclerviewlab.models.Article
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Locale
import java.time.format.DateTimeFormatter


class ArticleResultsAdapter : RecyclerView.Adapter<ArticleResultsAdapter.ViewHolder>() {

    private val articles = mutableListOf<Article>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val articleView = inflater.inflate(R.layout.fragment_article_result, parent, false)

        return ViewHolder(articleView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articles[position]
        holder.bind(article)
    }

    override fun getItemCount(): Int = articles.size

    fun setNewArticles(newArticles: List<Article>) {
        articles.clear()
        articles.addAll(newArticles)
    }

    fun addArticles(newArticles: List<Article>) {
        articles.addAll(newArticles)
    }

    class ViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var publicationDate: TextView
        private lateinit var headline: TextView
        private lateinit var snippet: TextView

        fun bind(article: Article) {
            publicationDate = itemView.findViewById(R.id.article_pub_date)
            headline = itemView.findViewById(R.id.article_headline)
            snippet = itemView.findViewById(R.id.article_snippet)

            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:s+SSSS", Locale.US)
            val date = article.publishDate?.let { dateFormat.parse(it) }

            val targetDateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.US)
            val targetDate = targetDateFormat.format(date)

            publicationDate.text = targetDate
            headline.text = article.headline?.main
            snippet.text = article.snippet
        }
    }
}