package com.theobencode.victoroben.sampleproject.presentation.posts

data class PostEntity(
    val postId: PostId,
    val title: PostTitle,
    val postThumbnailUrl: PostThumbnailUrl,
    val postAuthor: PostAuthor,
    val postCommentCount: PostCommentCount
)

inline class PostId(val id: String)
inline class PostTitle(val title: String)
inline class PostThumbnailUrl(val url: String?)
inline class PostCommentCount(val count: Int)
inline class PostAuthor(val author: String)