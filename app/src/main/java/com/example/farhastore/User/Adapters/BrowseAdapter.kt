package com.example.farhastore.User.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView


import com.example.farhastore.R
import com.example.farhastore.User.Util.constant

import com.example.farhastore.databinding.RowAllProductsBinding
import com.example.farhastore.User.model.Products
import com.example.farhastore.User.view.ProductPageFragment

import com.squareup.picasso.Picasso


class BrowseAdapter(var whoCall: String) : RecyclerView.Adapter<BrowseAdapter.ProductViewHolder>() {

    private var list: MutableList<Products> = mutableListOf<Products>()
    lateinit var context: Context

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        context = parent.context
        return ProductViewHolder(
            RowAllProductsBinding.inflate(LayoutInflater.from(parent.context),

                parent,
                false))
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        var current = list[position]


        Picasso.get().load(current.imageProduct.toString()).into(holder.img)


        holder.name.text = current.nameProduct
        holder.price.text = current.priceProduct.toString() + " EGP"




        holder.itemView.setOnClickListener {
            when (this.whoCall) {
                constant.Browse -> {
                    Navigation.findNavController(it)
                        .navigate(R.id.action_browseFragment_to_productPageFragment)
                }
                constant.ShowCategoryItems -> {
                    Navigation.findNavController(it)
                        .navigate(R.id.action_showCategoryItems_to_productPageFragment)

                }
            }

            ProductPageFragment.getProduct(current)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    @JvmName("setList1")
    fun setList(list: MutableList<Products>) {
        this.list = list

        notifyDataSetChanged()
    }

    class ProductViewHolder(itemView: RowAllProductsBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        var img = itemView.rowImageProducts
        var name = itemView.rowNameProduct
        var price = itemView.rowPriceProduct

    }


}