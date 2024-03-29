package com.example.farhastore.User.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.farhastore.User.ViewModel.CartViewModel
import com.example.farhastore.User.model.Products
import com.example.farhastore.databinding.RowCartBinding
import com.squareup.picasso.Picasso
import java.util.*

class CartAdapter : RecyclerView.Adapter<CartAdapter.CardVH>() {
    var listCArt: MutableList<Products> = mutableListOf()

    lateinit var context: Context
    var cartViewModel = CartViewModel()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardVH {
        context = parent.context
        return CardVH(RowCartBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: CardVH, position: Int) {
        var current = listCArt[position]

        Picasso.get().load(current.imageProduct.toString()).into(holder.imgProduct)
        holder.nameProduct.text = current.nameProduct
        holder.priceProduct.text = "${current.priceProduct}"



        holder.btnRemove.setOnClickListener {

            removeFromCart(current)
        }


    }

    private fun removeFromCart(current: Products) {
        cartViewModel.removeCartItem(current)
        Toast.makeText(context, "removed", Toast.LENGTH_SHORT).show()
        listCArt.remove(current)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return listCArt.size
        notifyDataSetChanged()
    }

    fun setList(list: MutableList<Products>) {
        this.listCArt = list
    }


    class CardVH(item: RowCartBinding) : RecyclerView.ViewHolder(item.root) {
        var imgProduct = item.imageCartProduct
        var nameProduct = item.cartNameProduct
        var priceProduct = item.cartPriceProduct

        var btnRemove = item.btnRemoveCertItem

    }


}