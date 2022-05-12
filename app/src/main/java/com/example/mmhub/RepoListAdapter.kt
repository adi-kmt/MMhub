package com.example.mmhub

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.RepoData
import com.example.mmhub.databinding.RepoEachRowBinding


class RepoListAdapter: ListAdapter<RepoData, RepoListAdapter.RepoListHolder>(DiffCall()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoListHolder {
        val binding = RepoEachRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoListHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoListHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    class RepoListHolder(private val binding: RepoEachRowBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(repoData: RepoData){
            binding.apply {
                RepoName.text = repoData.name
                RepoStars.text = repoData.stars.toString()
                RepoDesc.text = repoData.description.toString()
                RepoLang.text = repoData.language.toString()
            }
        }
    }
    class DiffCall:DiffUtil.ItemCallback<RepoData>() {
        override fun areItemsTheSame(oldItem: RepoData, newItem: RepoData): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: RepoData, newItem: RepoData): Boolean =
            oldItem == newItem
    }
}