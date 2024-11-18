package com.example.testapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication.databinding.ItemProductBinding
import com.example.testapplication.model.ProductListModel
import java.lang.ref.WeakReference

class ListAdapter(
    private val context: WeakReference<Context>,
    private val list: ArrayList<ProductListModel> = arrayListOf(),
    listener: ListAdapterListener? = null
) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            context.get()?.let { ctx ->
                binding.apply {
                    val data = list[position]
                    tvId.text = data.id.toString()
                    tvName.text = data.title.toString()
                }
            }
        }
    }
}

interface ListAdapterListener {
    fun onItemClick()
}