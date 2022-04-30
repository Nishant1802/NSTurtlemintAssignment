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
import com.nishant.nsturtlemintassignment.database.CommentData
import com.nishant.nsturtlemintassignment.databinding.ListItemCommentsBinding
import com.nishant.nsturtlemintassignment.utils.Commons

class CommentListAdapter(val onClickListener: OnClickListener) : ListAdapter<CommentData, CommentListAdapter.CommentViewHolder>(DiffCallback()) {

    class CommentViewHolder private constructor(val binding : ListItemCommentsBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: CommentData){

            val options: RequestOptions = RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.user_icon)
                .error(R.drawable.user_icon)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)

            Glide.with(binding.ivUserImage).load(item.avatar)
                .apply(options)
                .into(binding.ivUserImage)

            binding.tvUsername.text = "${item.username} "
            binding.tvUpdatedAt.text = "commented on ${Commons.formattedDate(item.commentedAt)}"
            binding.tvComment.text = item.comment
        }

        companion object{
            fun from(parent: ViewGroup): CommentViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = ListItemCommentsBinding.inflate(layoutInflater,parent,false)
                return CommentViewHolder(view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder{
        return CommentViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int){
        val item = getItem(position)
        holder.itemView.setOnClickListener{
            onClickListener.onClick(item)
        }
        holder.bind(item)
    }

    class OnClickListener(val clickListener: (commentData: CommentData) -> Unit){
        fun onClick(commentData: CommentData) = clickListener(commentData)
    }

    class DiffCallback : DiffUtil.ItemCallback<CommentData>(){
        override fun areItemsTheSame(oldItem: CommentData, newItem: CommentData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CommentData, newItem: CommentData): Boolean {
            return oldItem == newItem
        }
    }
}