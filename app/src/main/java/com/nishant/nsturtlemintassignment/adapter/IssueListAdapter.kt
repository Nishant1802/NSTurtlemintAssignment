package com.nishant.nsturtlemintassignment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.nishant.nsturtlemintassignment.R
import com.nishant.nsturtlemintassignment.database.IssueData
import com.nishant.nsturtlemintassignment.databinding.ListItemIssuesBinding
import com.nishant.nsturtlemintassignment.utils.Commons

class IssueListAdapter(val onClickListener: OnClickListener) : ListAdapter<IssueData, IssueListAdapter.IssueViewHolder>(DiffCallback()) {

    class IssueViewHolder private constructor(val binding : ListItemIssuesBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: IssueData){

            val options: RequestOptions = RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.user_icon)
                .error(R.drawable.user_icon)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)

            Glide.with(binding.ivUserImage).load(item.avatar)
                .apply(options)
                .into(binding.ivUserImage)

            binding.tvUsername.text = item.username
            binding.tvTitle.text = item.title
            binding.tvDescription.text = item.description
            binding.tvUpdatedAt.text = "Updated on ${Commons.formattedDate(item.updatedAt)}"
        }

        companion object{
            fun from(parent: ViewGroup): IssueViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = ListItemIssuesBinding.inflate(layoutInflater,parent,false)
                return IssueViewHolder(view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssueViewHolder{
        return IssueViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: IssueViewHolder, position: Int){
        val item = getItem(position)
        holder.itemView.setOnClickListener{
            onClickListener.onClick(item)
        }
        holder.bind(item)
    }

    class OnClickListener(val clickListener: (issueData: IssueData) -> Unit){
        fun onClick(issueData: IssueData) = clickListener(issueData)
    }

    class DiffCallback : DiffUtil.ItemCallback<IssueData>(){
        override fun areItemsTheSame(oldItem: IssueData, newItem: IssueData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: IssueData, newItem: IssueData): Boolean {
            return oldItem == newItem
        }
    }
}