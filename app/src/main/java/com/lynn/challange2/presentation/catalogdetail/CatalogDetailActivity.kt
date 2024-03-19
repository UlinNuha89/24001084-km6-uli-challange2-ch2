package com.lynn.challange2.presentation.catalogdetail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lynn.challange2.data.model.Catalog
import com.lynn.challange2.databinding.ActivityCatalogDetailBinding
import com.lynn.challange2.utils.toIndonesianFormat

class CatalogDetailActivity : AppCompatActivity() {
    private val binding: ActivityCatalogDetailBinding by lazy {
        ActivityCatalogDetailBinding.inflate(layoutInflater)
    }
    private var locationLink: String? = null
    private var price: Double? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        getDetailData()
        setClickAction()
    }

    companion object {
        const val EXTRAS_ITEM = "EXTRAS_ITEM"
        fun startActivity(context: Context, catalog: Catalog) {
            val intent = Intent(context, CatalogDetailActivity::class.java)
            intent.putExtra(EXTRAS_ITEM, catalog)
            context.startActivity(intent)
        }
    }

    private fun getDetailData() {
        intent.extras?.getParcelable<Catalog>(EXTRAS_ITEM).let {
            price = it?.price
            locationLink = it?.locUrl
            bindView(it)
        }
    }

    private fun bindView(item: Catalog?) {
        item?.let {
            binding.layoutDetail.ivDetailImage.setImageResource(it.image)
            binding.layoutDetail.tvDetailTitle.text = it.name
            binding.layoutDetail.tvDetailPrice.text = it.price.toIndonesianFormat()
            binding.layoutDetail.tvDetailDesc.text = it.desc
            binding.layoutDetail.tvDetailLocation.text = it.location
        }
        setAddToCartText(1.0, price)
    }

    private fun setClickAction() {
        binding.layoutBottomDetail.ivAddButton.setOnClickListener {
            orderIncrement()
        }
        binding.layoutBottomDetail.ivMinusButton.setOnClickListener {
            orderDecrement()
        }
        binding.layoutDetail.tvDetailLocation.setOnClickListener {
            goToGoogleMaps()
        }
        binding.cardBackArrow.setOnClickListener {
            onBackPressed()
        }
        binding.layoutBottomDetail.btnAddToCart.setOnClickListener {
            val count = binding.layoutBottomDetail.tvOrderCount.text
            Toast.makeText(this, "Kamu menambahkan $count porsi ke keranjang", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun goToGoogleMaps() {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(locationLink)
        )
        startActivity(intent)
    }

    private fun orderIncrement() {
        val count: Int = binding.layoutBottomDetail.tvOrderCount.text.toString().toInt().inc()
        binding.layoutBottomDetail.tvOrderCount.text = count.toString()
        setAddToCartText(count.toDouble(), price)
    }

    private fun orderDecrement() {
        val count: Int = binding.layoutBottomDetail.tvOrderCount.text.toString().toInt().dec()
        if (count <= 0) {
            Toast.makeText(this, "Tidak bisa kurang dari 1", Toast.LENGTH_SHORT).show()
        } else {
            binding.layoutBottomDetail.tvOrderCount.text = count.toString()
            setAddToCartText(count.toDouble(), price)
        }
    }

    private fun setAddToCartText(count: Double, price: Double?) {
        binding.layoutBottomDetail.btnAddToCart.text =
            "Tambah ke Keranjang - " + price?.times(count).toIndonesianFormat()
    }
}