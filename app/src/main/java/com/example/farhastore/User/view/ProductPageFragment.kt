package com.example.farhastore.User.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.farhastore.User.ViewModel.CartViewModel
import com.example.farhastore.User.model.Products
import com.example.farhastore.databinding.FragmentProductPageBinding
import com.squareup.picasso.Picasso


class ProductPageFragment : Fragment() {
    lateinit var binding: FragmentProductPageBinding


    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductPageBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        Picasso.get().load(products.imageProduct).into(binding.imgProductPage)
        binding.tvNameProductPage.text = products.nameProduct
        binding.tvPriceProductPage.text = products.priceProduct.toString() + " EGP "
        binding.tvDescProductPage.text = products.descriptionProduct



        binding.btnAddToCartProductPage.setOnClickListener {

            CartViewModel().setItemToCart(products)
            Toast.makeText(context, "Added To Cart", Toast.LENGTH_SHORT).show()
        }


    }


    companion object {

        lateinit var products: Products

        fun getProduct(products: Products) {
            this.products = products
        }
    }
}