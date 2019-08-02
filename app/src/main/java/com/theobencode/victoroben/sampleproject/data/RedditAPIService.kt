package com.theobencode.victoroben.sampleproject.data

import com.theobencode.victoroben.sampleproject.network.models.comments.CommentsResponse
import com.theobencode.victoroben.sampleproject.network.models.posts.PostsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface RedditAPIService {

    @GET("/r/aww/.json")
    suspend fun fetchRedditPosts(): PostsResponse

    @GET("/r/aww/comments/{postId}/.json")
    suspend fun fetchRedditCommentsForPost(@Path("postId") postId: String): List<CommentsResponse>
}