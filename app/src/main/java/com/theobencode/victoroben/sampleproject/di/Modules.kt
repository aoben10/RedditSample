package com.theobencode.victoroben.sampleproject.di

import com.theobencode.victoroben.sampleproject.presentation.PostsRepository
import com.theobencode.victoroben.sampleproject.presentation.comments.CommentsViewModel
import com.theobencode.victoroben.sampleproject.presentation.posts.PostsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PostsViewModel(get()) }
    viewModel { (postId: String) -> CommentsViewModel(get(), postId) }
}

val repositoryModule = module {
    single { PostsRepository(get()) }
}

