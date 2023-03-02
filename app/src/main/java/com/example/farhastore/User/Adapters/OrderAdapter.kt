package com.example.farhastore.User.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.farhastore.R
import com.example.farhastore.User.ViewModel.OrderViewModel
import com.example.farhastore.User.model.Orders
import com.example.farhastore.databinding.RowOrdersBinding

class OrderAdapter : RecyclerView.Adapter<OrderAdapter.OrderVH>() {
    var listOrder: MutableList<Orders> = mutableListOf()

    lateinit var context: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderVH {
        context = parent.context
        return OrderVH(RowOrdersBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))

    }

    @SuppressLint("SuspiciousIndentation", "ResourceAsColor")
    override fun onBindViewHolder(holder: OrderVH, position: Int) {
        var current = listOrder[position]

         holder.dateOrder.text = current.date
         holder.timeOrder.text = current.time
         holder.countProduct.text = current.products.size.toString()
         var productInfo = StringBuilder()
         var price = 0.0
             for (i in current.products)
             {   productInfo.append(i.nameProduct).append(" ").append(i.priceProduct).append("\n")
                 price += i.priceProduct
             }
        holder.totalPriceProduct.text ="$price"
        holder.productItem.text = productInfo



        holder.btnRemove.setOnClickListener {

            OrderViewModel().removeOrderItem(current)
            Toast.makeText(context, "order removed", Toast.LENGTH_SHORT).show()
            listOrder.remove(current)
        }


        if (current.isConfirmed){
            holder.btnRemove.visibility = View.GONE
            holder.btnBuy.apply {
                text = "تم تأكيد الطلب بنجاح"

            }
            holder.btnBuy.setBackgroundColor(R.color.green)

        }


    }

    override fun getItemCount(): Int {
        return listOrder.size
    }


    fun setListOrders(list: MutableList<Orders>) {
        this.listOrder = list
        notifyDataSetChanged()
    }

    class OrderVH(item: RowOrdersBinding) : RecyclerView.ViewHolder(item.root) {

        var countProduct = item.OrderCountProduct
        var totalPriceProduct = item.orderPriceProduct
        var productItem = item.productItem

        var dateOrder = item.dateOrder
        var timeOrder = item.timeOrder
        var btnRemove = item.btnRemoveOrder
        var btnBuy = item.btnCartBuy

    }



}


