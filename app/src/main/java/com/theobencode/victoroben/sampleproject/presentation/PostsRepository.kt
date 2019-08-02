package com.theobencode.victoroben.sampleproject.presentation

import com.orhanobut.logger.Logger
import com.theobencode.victoroben.sampleproject.data.RedditAPIService
import com.theobencode.victoroben.sampleproject.presentation.posts.DataState
import com.theobencode.victoroben.sampleproject.presentation.posts.PostAuthor
import com.theobencode.victoroben.sampleproject.presentation.posts.PostCommentCount
import com.theobencode.victoroben.sampleproject.presentation.posts.PostEntity
import com.theobencode.victoroben.sampleproject.presentation.posts.PostId
import com.theobencode.victoroben.sampleproject.presentation.posts.PostThumbnailUrl
import com.theobencode.victoroben.sampleproject.presentation.posts.PostTitle
import com.theobencode.victoroben.sampleproject.presentation.posts.Resource

class PostsRepository(private val redditAPIService: RedditAPIService) {

    suspend fun fetchPosts(): Resource<List<PostEntity>> {
        return try {
            val posts = redditAPIService.fetchRedditPosts()
            val postEntities = ArrayList<PostEntity>()
            posts.postData.children.forEach { child ->
                val entity = with(child.data) {
                    PostEntity(
                        PostId(id),
                        PostTitle(title),
                        PostThumbnailUrl(thumbnail),
                        PostAuthor(author),
                        PostCommentCount(numComments)
                    )
                }
                postEntities.add(entity)
            }

            Resource(
                dataState = DataState.SUCCESS,
                data = postEntities,
                message = null
            )
        } catch (e: Exception) {
            Logger.e(e, "An error occurred while fetching posts")
            Resource(dataState = DataState.ERROR, data = null, message = e.message)
        }
    }

    suspend fun fetchComments(postId: String): Resource<List<CommentEntity>> {
        return try {
            val commentsResponse = redditAPIService.fetchRedditCommentsForPost(postId)
            val commentEntities = ArrayList<CommentEntity>()

            commentsResponse[1].data.children.sortedByDescending { it.data.score }.forEachIndexed { index, child ->
                if (index >= 5) return@forEachIndexed
                with(child.data) {
                    commentEntities.add(CommentEntity(score, body))
                }
            }
            Resource(
                dataState = DataState.SUCCESS,
                data = commentEntities,
                message = null
            )

        } catch (e: Exception) {
            Logger.e(e, "An error occurred while fetching comments for post $postId")
            Resource(dataState = DataState.ERROR, data = null, message = e.message)
        }

    }
}

data class CommentEntity(val score: Int?, val body: String?)