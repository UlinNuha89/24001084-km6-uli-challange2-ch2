package com.lynn.challange2.presentation.cataloglist.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.lynn.challange2.base.ViewHolderBinder
import com.lynn.challange2.data.model.Catalog
import com.lynn.challange2.databinding.ItemCatalogListBinding
import com.lynn.challange2.utils.toIndonesianFormat

class CatalogListItemViewHolder(
    private val binding: ItemCatalogListBinding,
    private val listener: OnItemClickedListener<Catalog>
) : ViewHolder(binding.root), ViewHolderBinder<Catalog> {
    override fun bind(item: Catalog) {
        item.let {
            binding.ivCatalogImage.setImageResource(it.image)
            binding.tvCatalogName.text = it.name
            binding.tvCatalogPrice.text = it.price.toIndonesianFormat()
            itemView.setOnClickListener {
                listener.onItemClicked(item)
            }
        }

    }
}