package com.theobencode.victoroben.sampleproject.presentation.posts

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.theobencode.victoroben.sampleproject.R
import com.theobencode.victoroben.sampleproject.presentation.AllPostsFragmentDirections.Companion.actionAllPostsToPostCommentsFragment
import com.theobencode.victoroben.sampleproject.startRefreshing
import com.theobencode.victoroben.sampleproject.stopRefreshing
import kotlinx.android.synthetic.main.fragment_all_posts.postsRecyclerView
import kotlinx.android.synthetic.main.fragment_all_posts.swipeRefreshLayout
import org.koin.androidx.viewmodel.ext.android.getViewModel

class AllPostsFragment : Fragment(R.layout.fragment_all_posts) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(requireContext())
        val postsAdapter = PostAdapter(::postItemClick, ::commentItemClick)

        val dividerItemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        postsRecyclerView.apply {
            this.layoutManager = layoutManager
            adapter = postsAdapter
            addItemDecoration(dividerItemDecoration)
        }

        val viewModel = getViewModel<PostsViewModel>()

        swipeRefreshLayout.setOnRefreshListener { viewModel.getPosts() }
        viewModel.posts.observe(viewLifecycleOwner) { resource ->
            when (resource.dataState) {
                DataState.LOADING -> swipeRefreshLayout.startRefreshing()
                DataState.SUCCESS -> {
                    swipeRefreshLayout.stopRefreshing()
                }
                DataState.ERROR -> {
                    Toast.makeText(requireContext(), com.theobencode.victoroben.sampleproject.R.string.an_error_has_occured, Toast.LENGTH_SHORT)
                        .show()
                    swipeRefreshLayout.stopRefreshing()
                }
            }
            postsAdapter.submitList(resource.data ?: emptyList())
        }

    }

    private fun commentItemClick(postId: String) = findNavController().navigate(actionAllPostsToPostCommentsFragment(postId))

    private fun postItemClick(postId: String) {
        val url = "https://www.reddit.com/r/aww/comments/$postId/"
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }
}
