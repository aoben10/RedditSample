package com.theobencode.victoroben.sampleproject.presentation.comments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.theobencode.victoroben.sampleproject.presentation.CommentEntity
import com.theobencode.victoroben.sampleproject.presentation.PostsRepository
import com.theobencode.victoroben.sampleproject.presentation.posts.Resource
import kotlinx.coroutines.launch

class CommentsViewModel(private val repository: PostsRepository, val postId: String) : ViewModel() {

    private val _comments = MutableLiveData<Resource<List<CommentEntity>>>()
    val comments: LiveData<Resource<List<CommentEntity>>> = _comments

    init {
        getTop5Comments()
    }

    private fun getTop5Comments() {
        viewModelScope.launch {
            _comments.value = repository.fetchComments(postId)
        }
    }
}
