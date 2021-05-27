package com.example.gitfinder.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gitfinder.R
import com.example.gitfinder.models.Repository
import kotlinx.android.synthetic.main.item_repo.view.*

class MainAdapter  : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                onItemListener(data[adapterPosition])
            }
        }

        fun bind(repository: Repository) {
            itemView.owner_name.text = repository.owner.login
            itemView.repo_name.text = repository.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(data[position])

    private var onItemListener: (item: Repository) -> Unit = {}

    fun setOnItemClickListener(listener:(item: Repository) -> Unit) {
        onItemListener = listener
    }

    var data: List<Repository> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
}