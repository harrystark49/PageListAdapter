package com.example.pagelistadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.pagelistadapter.databinding.ErrorRowBinding

//
//class LoadstateAdapter(private val retry:()->Unit):LoadstateAdapter<LoadstateAdapter.> {
////    class LoadingViewHolder(private var binding:Er)
//}

class ReposLoadStateViewHolder(
    private val binding: ErrorRowBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.retryButton.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.errorMsg.text = loadState.error.localizedMessage
        }
        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.retryButton.isVisible = loadState is LoadState.Error
        binding.errorMsg.isVisible = loadState is LoadState.Error
    }
    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): ReposLoadStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.error_row, parent, false)
            val binding =ErrorRowBinding.bind(view)
            return ReposLoadStateViewHolder(binding, retry)
        }
    }
}
