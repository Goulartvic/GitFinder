package com.example.gitfinder.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gitfinder.R
import com.example.gitfinder.utils.showText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private val adapter by lazy { MainAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        observeViewModel()

        viewModel.getRepositories()
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
        })
        
        viewModel.error.observe(this, Observer {
            showText(this, "Error to load repositories")
        })
    }
}