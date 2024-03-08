package com.lynn.challange2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.lynn.challange2.adapter.CatalogAdapter
import com.lynn.challange2.adapter.CategoryAdapter
import com.lynn.challange2.databinding.ActivityMainBinding
import com.lynn.challange2.models.Category
import com.lynn.challange2.models.Catalog

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val categoryAdapter = CategoryAdapter()
    private val catalogAdapter = CatalogAdapter()
    private fun setListCategory() {
        val data = listOf(
            Category(image = R.drawable.img_nasi, name = "Nasi"),
            Category(image = R.drawable.img_mie, name = "Mie"),
            Category(image = R.drawable.img_esteh, name = "Minuman"),
            Category(image = R.drawable.img_martabak, name = "Snack"),
        )
        binding.rvCategory.apply {
            adapter = this@MainActivity.categoryAdapter
        }
        categoryAdapter.submitData(data)
    }

    private fun setListCatalog() {
        val data = listOf(
            Catalog(image = R.drawable.img_kentang, price = 15000.00, name = "Kentang Goreng"),
            Catalog(image = R.drawable.img_kuebandung, price = 20000.00, name = "Kue Bandung"),
            Catalog(image = R.drawable.img_martabak, price = 20000.00, name = "Martabak"),
            Catalog(image = R.drawable.img_nasi, price = 5000.00, name = "Nasi"),
            Catalog(image = R.drawable.img_esteh, price = 3000.00, name = "Es Teh"),
            Catalog(image = R.drawable.img_mie, price = 10000.00, name = "Mie Goreng"),
            Catalog(image = R.drawable.img_ondeonde, price = 5000.00, name = "Onde Onde"),
            Catalog(image = R.drawable.img_pisangmolen, price = 5000.00, name = "Pisang Molen"),
            Catalog(image = R.drawable.img_tahupetis, price = 10000.00, name = "Tahu Petis"),
            Catalog(image = R.drawable.img_lumpia, price = 5000.00, name = "Lumpia")
        )
        binding.rvCatalog.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = this@MainActivity.catalogAdapter
        }
        catalogAdapter.submitData(data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setAction()
        setListCategory()
        setListCatalog()
    }

    private fun setAction() {
        binding.layoutHeader.ivProfile.setOnClickListener {
            Toast.makeText(this, "ini tempat profile", Toast.LENGTH_SHORT).show()
        }
    }

}