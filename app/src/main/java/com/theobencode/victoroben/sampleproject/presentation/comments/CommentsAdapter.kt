package com.theobencode.victoroben.sampleproject.presentation.comments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.theobencode.victoroben.sampleproject.R
import com.theobencode.victoroben.sampleproject.presentation.CommentEntity
import kotlinx.android.synthetic.main.comment_list_item.view.commentText

class CommentsAdapter : ListAdapter<CommentEntity, CommentsAdapter.CommentsViewHolder>(
    CommentsDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        return CommentsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.comment_list_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        val comment = getItem(position)
        holder.itemView.apply {
            commentText.text = comment.body
        }
    }

    class CommentsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}

private class CommentsDiffCallback : DiffUtil.ItemCallback<CommentEntity>() {

    override fun areItemsTheSame(oldItem: CommentEntity, newItem: CommentEntity) = oldItem == newItem

    override fun areContentsTheSame(oldItem: CommentEntity, newItem: CommentEntity) = oldItem == newItem
}