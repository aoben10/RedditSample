package com.theobencode.victoroben.sampleproject.presentation.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.theobencode.victoroben.sampleproject.presentation.PostsRepository
import kotlinx.coroutines.launch

class PostsViewModel(private val repository: PostsRepository) : ViewModel() {

    private val _allPosts = MutableLiveData<Resource<List<PostEntity>>>()
    val posts: LiveData<Resource<List<PostEntity>>> = _allPosts

    init {
        getPosts()
    }

    fun getPosts() {
        _allPosts.value = Resource(
            dataState = DataState.LOADING,
            data = _allPosts.value?.data,
            message = null
        )
        viewModelScope.launch {
            val repoResource = repository.fetchPosts()
            if (repoResource.dataState == DataState.ERROR) {
                _allPosts.value = repoResource.copy(data = _allPosts.value?.data)
            } else {
                _allPosts.value = repoResource
            }
        }
    }
}

enum class DataState { LOADING, SUCCESS, ERROR }

data class Resource<out T> constructor(val dataState: DataState, val data: T? = null, val message: String? = null)
