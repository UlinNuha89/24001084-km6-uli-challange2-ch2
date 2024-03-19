package com.lynn.challange2.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lynn.challange2.R
import com.lynn.challange2.data.datasource.CategoryDataSourceImpl
import com.lynn.challange2.databinding.FragmentHomeBinding
import com.lynn.challange2.presentation.cataloglist.CatalogListFragment
import com.lynn.challange2.presentation.categorylist.adapter.CategoryAdapter

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val categoryAdapter = CategoryAdapter()
    private fun setListCategory() {
        val data = CategoryDataSourceImpl().getCategoryData()
        binding.rvCategory.apply {
            adapter = this@HomeFragment.categoryAdapter
        }
        categoryAdapter.submitData(data)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        childFragmentManager.beginTransaction()
            .replace(R.id.list_fragment, CatalogListFragment())
            .commit()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListCategory()
    }

}