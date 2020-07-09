package com.example.gitfinder.repository

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.gitfinder.R
import com.example.gitfinder.models.Repository
import com.example.gitfinder.utils.Constants.Companion.REPOSITORY
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.item_repo_detail.*

class RepositoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val repository: Repository = intent.extras?.get(REPOSITORY) as Repository

        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.title = repository.name
        }

        owner_name.text = repository.owner.login
        repo_name.text = repository.name
        repo_description.text = repository.description

        Glide.with(owner_image)
            .load(repository.owner.avatarUrl)
            .apply(RequestOptions().placeholder(R.drawable.ic_placeholder))
            .into(owner_image)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}