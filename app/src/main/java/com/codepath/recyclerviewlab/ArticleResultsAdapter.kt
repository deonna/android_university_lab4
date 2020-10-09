package com.codepath.recyclerviewlab

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.imageLoader
import coil.load
import com.codepath.recyclerviewlab.models.Article
import java.text.SimpleDateFormat
import java.util.Locale


class ArticleResultsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_ARTICLE = 1
        private const val VIEW_TYPE_FIRST_PAGE_ARTICLE = 2

        fun getParsedDate(publishDate: String?): String {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:s+SSSS", Locale.US)
            val date = publishDate.let { dateFormat.parse(it) }

            val displayDateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.US)

            return displayDateFormat.format(date)
        }
    }

    private val articles = mutableListOf<Article>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        return when (viewType) {
            VIEW_TYPE_FIRST_PAGE_ARTICLE -> {
                val firstPageArticleView =
                    inflater.inflate(R.layout.fragment_article_result_first_page, parent, false)
                FirstPageArticleViewHolder(firstPageArticleView)
            }

            else -> {
                val articleView = inflater.inflate(R.layout.fragment_article_result, parent, false)
                ArticleViewHolder(articleView)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val article = articles[position]

        (holder as? ArticleVH)?.bind(holder.itemView, article)
    }

    override fun getItemViewType(position: Int): Int {
        val article = articles[position]

        return when (article.printPage) {
            "1" -> VIEW_TYPE_FIRST_PAGE_ARTICLE
            else -> VIEW_TYPE_ARTICLE
        }
    }

    override fun getItemCount(): Int = articles.size

    fun setNewArticles(newArticles: List<Article>) {
        articles.clear()
        articles.addAll(newArticles)
    }

    fun addArticles(newArticles: List<Article>) {
        articles.addAll(newArticles)
    }

    class FirstPageArticleViewHolder(private val itemView: View) :
        RecyclerView.ViewHolder(itemView), ArticleVH {

        override var publicationDate: TextView? = null
        override var headline: TextView? = null
        override var snippet: TextView? = null
        override var image: ImageView? = null

        var firstPageHeader: TextView? = null

        override fun bind(itemView: View, article: Article) {
            super.bind(itemView, article)

            firstPageHeader = itemView.findViewById(R.id.first_page_header)

            firstPageHeader?.text =
                itemView.context.getString(R.string.first_page, article.sectionName)
        }
    }

    interface ArticleVH {
        var publicationDate: TextView?
        var headline: TextView?
        var snippet: TextView?
        var image: ImageView?

        fun bind(itemView: View, article: Article) {
            publicationDate = itemView.findViewById(R.id.article_pub_date)
            headline = itemView.findViewById(R.id.article_headline)
            snippet = itemView.findViewById(R.id.article_snippet)

            publicationDate?.text = getParsedDate(article.publishDate)
            headline?.text = article.headline?.main
            snippet?.text = article.snippet

            article.multimedia?.firstOrNull()
                ?.let { image?.load(it.url, itemView.context.imageLoader) }
        }
    }

    class ArticleViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView),
        ArticleVH {

        override var publicationDate: TextView? = null
        override var headline: TextView? = null
        override var snippet: TextView? = null
        override var image: ImageView? = null
    }
}