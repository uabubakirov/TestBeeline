package com.example.test.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.test.databinding.FragmentMapBinding
import com.example.test.databinding.MapItemsBinding
import com.example.test.presentation.entities.RecyclerModel
import java.util.Collections.list

class MapAdapter(
    private val onItemClick:(model: RecyclerModel)-> Unit
): ListAdapter<RecyclerModel,MapAdapter.MapViewHolder>(Comparator()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MapViewHolder {
        return MapViewHolder(MapItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MapViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class MapViewHolder(val binding: MapItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                getItem(absoluteAdapterPosition)?.let {item->
                    item?.let { onItemClick(it) }
                }
            }
        }

        fun bind(model: RecyclerModel) {
            binding.text.text = model.name
        }


    }
    class Comparator : DiffUtil.ItemCallback<RecyclerModel>() {
        override fun areItemsTheSame(oldItem: RecyclerModel, newItem: RecyclerModel): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: RecyclerModel, newItem: RecyclerModel): Boolean {
            return oldItem == newItem
        }

    }
}
