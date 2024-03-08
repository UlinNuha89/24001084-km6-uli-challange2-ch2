package com.lynn.challange2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lynn.challange2.databinding.ItemCatalogBinding
import com.lynn.challange2.models.Catalog
import com.lynn.challange2.utils.toIndonesianFormat

class CatalogAdapter : RecyclerView.Adapter<CatalogAdapter.CatalogViewHolder>() {

    private val data = mutableListOf<Catalog>()

    fun submitData(items: List<Catalog>) {
        data.addAll(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogViewHolder {
        return CatalogViewHolder(
            ItemCatalogBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CatalogViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    class CatalogViewHolder(private val binding: ItemCatalogBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Catalog) {
            binding.tvCatalogName.text = item.name
            binding.tvCatalogPrice.text = item.price.toIndonesianFormat()
            binding.ivCatalogImage.setImageResource(item.image)
        }
    }
}