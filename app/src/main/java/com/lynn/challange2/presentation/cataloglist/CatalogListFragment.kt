package com.lynn.challange2.presentation.cataloglist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.lynn.challange2.R
import com.lynn.challange2.data.datasource.CatalogDataSource
import com.lynn.challange2.data.datasource.CatalogDataSourceImpl
import com.lynn.challange2.data.model.Catalog
import com.lynn.challange2.databinding.FragmentCatalogListBinding
import com.lynn.challange2.presentation.catalogdetail.CatalogDetailActivity
import com.lynn.challange2.presentation.cataloglist.adapter.CatalogAdapter
import com.lynn.challange2.presentation.cataloglist.adapter.OnItemClickedListener


class CatalogListFragment : Fragment() {

    private lateinit var binding: FragmentCatalogListBinding
    private var adapter: CatalogAdapter? = null
    private val dataSource: CatalogDataSource by lazy {
        CatalogDataSourceImpl()
    }
    private var isUsingGridMode: Boolean = true


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCatalogListBinding.inflate(layoutInflater, container, false)
        return (binding.root)
    }

    private fun setClickAction() {
        binding.layoutMenuHeader.ivMenu.setOnClickListener {
            isUsingGridMode = !isUsingGridMode
            setImageListMode(isUsingGridMode)
            bindCatalogList(isUsingGridMode)
        }
    }


    private fun setImageListMode(usingGridMode: Boolean) {
        binding.layoutMenuHeader.ivMenu.setImageResource(
            if (usingGridMode) R.drawable.ic_list_menu else R.drawable.ic_list_menu_grid
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindCatalogList(isUsingGridMode)
        setClickAction()
    }

    private fun bindCatalogList(isUsingGrid: Boolean) {
        val listMode = if (isUsingGrid) CatalogAdapter.MODE_GRID else CatalogAdapter.MODE_LIST
        adapter = CatalogAdapter(
            listMode = listMode,
            listener = object : OnItemClickedListener<Catalog> {
                override fun onItemClicked(item: Catalog) {
                    navigateToDetail(item)
                }
            })
        binding.rvCatalogList.apply {
            adapter = this@CatalogListFragment.adapter
            layoutManager = GridLayoutManager(requireContext(), if (isUsingGrid) 2 else 1)
        }
        adapter?.submitData(dataSource.getCatalogData())
    }

    private fun navigateToDetail(item: Catalog) {
        CatalogDetailActivity.startActivity(requireContext(), item)
    }
}