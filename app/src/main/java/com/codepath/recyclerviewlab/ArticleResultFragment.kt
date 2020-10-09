package com.codepath.recyclerviewlab

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.codepath.recyclerviewlab.networking.NYTimesApiClient

/**
 * A fragment representing a list of Items.
 *
 *
 * interface.
 */
class ArticleResultFragment
/**
 * Mandatory empty constructor for the fragment manager to instantiate the
 * fragment (e.g. upon screen orientation changes).
 */
    : Fragment() {
    private val client = NYTimesApiClient()
    override fun onPrepareOptionsMenu(menu: Menu) {
        val item = menu.findItem(R.id.action_search).actionView as SearchView
        item.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                loadNewArticlesByQuery(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_article_result_list, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    private fun loadNewArticlesByQuery(query: String) {
        Log.d("ArticleResultFragment", "loading articles for query $query")
        Toast.makeText(context, "Loading articles for \'$query\'", Toast.LENGTH_SHORT).show()
        // TODO(Checkpoint 3): Implement this method to populate articles
    }

    private fun loadArticlesByPage(page: Int) {
        // TODO(Checkpoint 4): Implement this method to do infinite scroll
    }
}