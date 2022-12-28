package com.example.farhastore.User.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.farhastore.R
import com.example.farhastore.User.Adapters.BrowseAdapter
import com.example.farhastore.User.Util.constant
import com.example.farhastore.User.ViewModel.ProductViewModel
import com.example.farhastore.User.model.Products
import com.example.farhastore.databinding.FragmentShowCategoryItemsBinding


class ShowCategoryItems : Fragment() {
    private lateinit var binding: FragmentShowCategoryItemsBinding
    private lateinit var productViewModel: ProductViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productViewModel = ViewModelProvider(this)[ProductViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentShowCategoryItemsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when (nameCategory) {

            constant.HandMade -> {

                productViewModel.getHandmadeProducts()
                binding.nameCategory.text = " ${constant.HandMade}"
                productViewModel.mutableHandmadeProduct().observe(viewLifecycleOwner) {
                    if (it != null) {
                        it.shuffle()
                        setRecycle(it)
                    }
                }
            }
            constant.Accessories -> {
                productViewModel.getAccessoriesProducts()
                binding.nameCategory.text = " ${constant.Accessories}"
                productViewModel.mutableAccessoriesProduct().observe(viewLifecycleOwner) {
                    if (it != null) {
                        it.shuffle()
                        setRecycle(it)
                    }
                }
            }
            constant.Print -> {
                productViewModel.getPrintProducts()
                binding.nameCategory.text = " ${constant.Print}"
                productViewModel.mutablePrintProduct().observe(viewLifecycleOwner) {
                    if (it != null) {
                        it.shuffle()
                        setRecycle(it)
                    }
                }
            }
            constant.Laser -> {
                productViewModel.getLaserProducts()
                binding.nameCategory.text = " ${constant.Laser}"
                productViewModel.mutableLaserProduct().observe(viewLifecycleOwner) {
                    if (it != null) {
                        it.shuffle()
                        setRecycle(it)
                    }
                }
            }
            constant.Resin -> {
                productViewModel.getResinProducts()
                binding.nameCategory.text = " ${constant.Resin}"
                productViewModel.mutableResinProduct().observe(viewLifecycleOwner) {
                    if (it != null) {
                        it.shuffle()
                        setRecycle(it)
                    }
                }
            }
            constant.Skins -> {
                productViewModel.getSkinsProducts()
                binding.nameCategory.text = " ${constant.Skins}"
                productViewModel.mutableSkinsProduct().observe(viewLifecycleOwner) {
                    if (it != null) {
                        it.shuffle()
                        setRecycle(it)
                    }
                }
            }


        }

        goToHome()

    }

    private fun goToHome() {
        binding.imgBack.setOnClickListener {
             Navigation.findNavController(it).navigate(R.id.action_showCategoryItems_to_homeFragment)

        }
    }

    private fun setRecycle(it: MutableList<Products>) {
        var adapterCategory = BrowseAdapter(constant.ShowCategoryItems)
        binding.recycleShowCategory.layoutManager = LinearLayoutManager(context)
        binding.recycleShowCategory.adapter = adapterCategory
        adapterCategory.setList(it)

    }


    companion object {
        lateinit var nameCategory: String
        fun getCategory(nameCategory: String) {
            this.nameCategory = nameCategory
        }

    }
}