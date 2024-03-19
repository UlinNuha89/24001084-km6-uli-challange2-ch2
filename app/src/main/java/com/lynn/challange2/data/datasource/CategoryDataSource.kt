package com.lynn.challange2.data.datasource

import com.lynn.challange2.R
import com.lynn.challange2.data.model.Category

interface CategoryDataSource {
    fun getCategoryData(): List<Category>
}

class CategoryDataSourceImpl() : CategoryDataSource {
    override fun getCategoryData(): List<Category> {
        return mutableListOf(
            Category(
                image = R.drawable.img_nasi,
                name = "Nasi"),
            Category(
                image = R.drawable.img_mie,
                name = "Mie"),
            Category(
                image = R.drawable.img_esteh,
                name = "Minuman"),
            Category(
                image = R.drawable.img_martabakasin,
                name = "Snack"),

            )
    }
}