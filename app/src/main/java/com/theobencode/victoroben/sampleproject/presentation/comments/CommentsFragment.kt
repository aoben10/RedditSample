package com.theobencode.victoroben.sampleproject.presentation.comments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.theobencode.victoroben.sampleproject.CommentsFragmentArgs.Companion.fromBundle
import com.theobencode.victoroben.sampleproject.R
import com.theobencode.victoroben.sampleproject.presentation.posts.DataState
import kotlinx.android.synthetic.main.fragment_post_comments.commentsRecyclerView
import kotlinx.android.synthetic.main.fragment_post_comments.progressBar
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class CommentsFragment : Fragment(R.layout.fragment_post_comments) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val postId = fromBundle(arguments!!).postId

        val commentsAdapter = CommentsAdapter()
        val layoutManager = LinearLayoutManager(requireContext())
        val dividerItemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        commentsRecyclerView.apply {
            this.layoutManager = layoutManager
            adapter = commentsAdapter
            addItemDecoration(dividerItemDecoration)
        }

        val viewModel = getViewModel<CommentsViewModel> { parametersOf(postId) }
        viewModel.comments.observe(viewLifecycleOwner) { resource ->
            when (resource.dataState) {
                DataState.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                }
                DataState.SUCCESS -> {
                    commentsAdapter.submitList(resource.data ?: emptyList())
                    progressBar.visibility = View.GONE
                    commentsRecyclerView.visibility = View.VISIBLE
                }
                DataState.ERROR -> {
                    progressBar.visibility = View.GONE
                    commentsRecyclerView.visibility = View.VISIBLE
                    Toast.makeText(requireContext(), R.string.an_error_has_occured, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}
