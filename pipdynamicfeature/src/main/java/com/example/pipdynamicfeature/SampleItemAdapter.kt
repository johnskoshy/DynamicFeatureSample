package com.example.pipdynamicfeature

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class SampleItemAdapter :
    ListAdapter<Int, SampleItemAdapter.SampleItemViewHolder>(IntComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleItemViewHolder {
        return SampleItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.sample_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SampleItemViewHolder, position: Int) {
        holder.textView.text =
            holder.itemView.context.getString(R.string.sample_item, getItem(position))
    }

    class SampleItemViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {
        val textView: TextView = rootView.findViewById(R.id.sample_text)
    }

    class IntComparator : DiffUtil.ItemCallback<Int>() {
        override fun areItemsTheSame(oldItem: Int, newItem: Int) =
            oldItem == newItem


        override fun areContentsTheSame(oldItem: Int, newItem: Int) =
            oldItem == newItem
    }
}