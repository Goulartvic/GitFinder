package com.example.gitfinder.main

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gitfinder.R
import com.example.gitfinder.repository.RepositoryActivity
import com.example.gitfinder.utils.Constants.Companion.REPOSITORY
import com.example.gitfinder.utils.showText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private lateinit var searchView: SearchView

    private val adapter by lazy { MainAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        observeViewModel()

        viewModel.getRepositories()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)

        val searchItem = menu?.findItem(R.id.action_search)
        searchView = searchItem?.actionView as SearchView

        val searchManager = this.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        setupSearchView(searchManager)

        return super.onCreateOptionsMenu(menu)
    }

    private fun observeViewModel() {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.repositories.observe(this, Observer {
            loader.visibility = View.GONE
            recycler_view.visibility = View.VISIBLE
            recycler_view.adapter = adapter
            val layoutManager = LinearLayoutManager(this)
            layoutManager.orientation = RecyclerView.VERTICAL
            recycler_view.layoutManager = layoutManager
            it?.let {
                adapter.data = it
            }

            adapter.setOnItemClickListener {
                startActivity(Intent(this, RepositoryActivity::class.java)
                    .putExtra(REPOSITORY, it))
            }
        })
        viewModel.error.observe(this, Observer {
            showText(this, getString(R.string.error_message))
        })
    }

    private fun setupSearchView(searchManager: SearchManager) {
        searchView.setSearchableInfo(searchManager.getSearchableInfo(this.componentName))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    if (it.isNotEmpty()) {
                        viewModel.searchRepositories(it)
                        recycler_view.visibility = View.GONE
                        loader.visibility = View.VISIBLE
                    } else {
                        viewModel.getRepositories()
                    }
                }
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean = true
        })
    }
}