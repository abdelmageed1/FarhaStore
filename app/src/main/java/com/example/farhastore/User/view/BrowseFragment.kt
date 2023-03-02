package com.example.farhastore.User.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.farhastore.User.Adapters.BrowseAdapter
import com.example.farhastore.User.Util.constant
import com.example.farhastore.User.ViewModel.ProductViewModel
import com.example.farhastore.User.model.Products
import com.example.farhastore.databinding.FragmentBrowseBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class BrowseFragment : Fragment() {

    lateinit var binding: FragmentBrowseBinding

    private lateinit var productViewModel: ProductViewModel
    // private var arrAllProduct: MutableList<Products> = mutableListOf<Products>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productViewModel = ViewModelProvider(this)[ProductViewModel::class.java]

        GlobalScope.launch(Dispatchers.Main) {

            launch { productViewModel.getPrintProducts() }
            launch { productViewModel.getSkinsProducts() }
            launch { productViewModel.getResinProducts() }
            launch { productViewModel.getHandmadeProducts() }
            launch { productViewModel.getAccessoriesProducts() }
            launch { productViewModel.getLaserProducts() }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {


        binding = FragmentBrowseBinding.inflate(inflater, container, false)
        return binding.root

    }


    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchView.setText("")

        var arrAllProduct: MutableList<Products> = mutableListOf<Products>()
        arrAllProduct.clear()


        productViewModel.mutableHandmadeProduct().observe(viewLifecycleOwner) {
            if (it != null) {
                arrAllProduct.addAll(it)
            }
        }

        productViewModel.mutableAccessoriesProduct().observe(viewLifecycleOwner) {
            if (it != null) {
                arrAllProduct.addAll(it)
            }
        }
        /////////

        productViewModel.mutableLaserProduct().observe(viewLifecycleOwner) {
            if (it != null) {

                arrAllProduct.addAll(it)

            }
        }
        productViewModel.mutablePrintProduct().observe(viewLifecycleOwner) {
            if (it != null) {
                arrAllProduct.addAll(it)
            }
        }

        productViewModel.mutableSkinsProduct().observe(viewLifecycleOwner) {
            if (it != null) {
                arrAllProduct.addAll(it)
            }
        }

        productViewModel.mutableResinProduct().observe(viewLifecycleOwner) {
            if (it != null) {
                arrAllProduct.addAll(it)
            }


        }

        arrAllProduct.shuffle()


        setRecycle(arrAllProduct)

        binding.searchView.addTextChangedListener { text ->
                setRecycle(arrAllProduct.filter { it.nameProduct.contains(text.toString()) } as MutableList<Products>)

        }


    }



    private fun setRecycle(it: MutableList<Products>) {
        var adapterProduct =
            BrowseAdapter(constant.Browse)
        binding.recycleBrowseProduct.layoutManager = LinearLayoutManager(context)
        binding.recycleBrowseProduct.adapter = adapterProduct
        it.shuffle()
        adapterProduct.setList(it)

    }


    companion object {


    }
}