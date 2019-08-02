package com.theobencode.victoroben.sampleproject.presentation.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.theobencode.victoroben.sampleproject.R
import kotlinx.android.synthetic.main.reddit_post_list_item.view.author
import kotlinx.android.synthetic.main.reddit_post_list_item.view.commentCount
import kotlinx.android.synthetic.main.reddit_post_list_item.view.title
import kotlinx.android.synthetic.main.reddit_post_list_item.view.userProfileIcon

class PostAdapter(private val postItemClickBlock: (String) -> Unit, private val commentClickBlock: (String) -> Unit) :
    ListAdapter<PostEntity, PostAdapter.PostViewHolder>(
        RepoDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val viewHolder = PostViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.reddit_post_list_item, parent, false)
        )

        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                postItemClickBlock(getItem(position).postId.id)
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.itemView.apply {
            author.text = post.postAuthor.author
            title.text = post.title.title
            commentCount.text = post.postCommentCount.count.toString()
            commentCount.setOnClickListener { commentClickBlock(post.postId.id) }
            post.postThumbnailUrl.url?.let { url ->
                Glide.with(context).load(url)
                    .apply(RequestOptions.circleCropTransform())
                    .placeholder(R.drawable.ic_person_black)
                    .into(userProfileIcon)
            }
        }
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}

private class RepoDiffCallback : DiffUtil.ItemCallback<PostEntity>() {

    override fun areItemsTheSame(oldItem: PostEntity, newItem: PostEntity) = oldItem.postId.id == newItem.postId.id

    override fun areContentsTheSame(oldItem: PostEntity, newItem: PostEntity) = oldItem == newItem
}