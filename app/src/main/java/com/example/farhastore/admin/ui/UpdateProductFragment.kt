package com.example.farhastore.admin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.farhastore.User.Adapters.BrowseAdapter
import com.example.farhastore.User.Util.constant
import com.example.farhastore.User.ViewModel.HomeViewModel
import com.example.farhastore.User.ViewModel.ProductViewModel
import com.example.farhastore.User.model.Products
import com.example.farhastore.databinding.FragmentAdminUpdateProductBinding


class UpdateProductFragment : Fragment() {
    lateinit var binding: FragmentAdminUpdateProductBinding
    private lateinit var productViewModel: ProductViewModel
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        productViewModel = ViewModelProvider(this)[ProductViewModel::class.java]
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAdminUpdateProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setSpinner()


    }


    private fun setSpinner() {

        binding.spinnerUpdateProduct.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    when (position) {
                        0 -> {

                            productViewModel.getHandmadeProducts()
                            productViewModel.mutableHandmadeProduct().observe(viewLifecycleOwner) {
                                if (it != null) {
                                    setRecycle(it, constant.HandMade)


                                }
                            }
                        }
                        1 -> {

                            productViewModel.getAccessoriesProducts()
                            productViewModel.mutableAccessoriesProduct()
                                .observe(viewLifecycleOwner) {
                                    if (it != null) {
                                        setRecycle(it, constant.Accessories)
                                    }
                                }
                        }

                        2 -> {
                            productViewModel.getLaserProducts()
                            productViewModel.mutableLaserProduct().observe(viewLifecycleOwner) {
                                if (it != null) {
                                    setRecycle(it, constant.Laser)
                                }
                            }
                        }

                        3 -> {
                            productViewModel.getPrintProducts()
                            productViewModel.mutablePrintProduct().observe(viewLifecycleOwner) {
                                if (it != null) {
                                    setRecycle(it, constant.Print)
                                }
                            }
                        }

                        4 -> {

                            productViewModel.getResinProducts()
                            productViewModel.mutableResinProduct().observe(viewLifecycleOwner) {
                                if (it != null) {
                                    setRecycle(it, constant.Resin)
                                }
                            }
                        }

                        5 -> {
                            productViewModel.getSkinsProducts()
                            productViewModel.mutableSkinsProduct().observe(viewLifecycleOwner) {
                                if (it != null) {
                                    setRecycle(it, constant.Skins)
                                }
                            }
                        }


                        6 -> {
                            homeViewModel.getOffers()
                            homeViewModel.mutableOffers().observe(viewLifecycleOwner) {
                                if (it != null) {
                                    setRecycle(it, constant.offer)
                                }
                            }

                        }

                    }

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }


    }


    private fun setRecycle(it: MutableList<Products>, category: String) {

        var adapterCategory = BrowseAdapter(constant.adminUpdateProduct)
        adapterCategory.categoryInUpdateProduct = category
        binding.recycleViewUpdateProduct.layoutManager = LinearLayoutManager(context)
        binding.recycleViewUpdateProduct.adapter = adapterCategory
        adapterCategory.setList(it)

    }


    companion object {


    }

}