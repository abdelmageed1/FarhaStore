package com.example.farhastore.User.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.farhastore.User.ViewModel.OrderViewModel
import com.example.farhastore.User.model.Orders
import com.example.farhastore.databinding.RowOrdersBinding
import com.squareup.picasso.Picasso

class OrderAdapter : RecyclerView.Adapter<OrderAdapter.OrderVH>() {
    var listOrder: MutableList<Orders> = mutableListOf()

    lateinit var context: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderVH {
        context = parent.context
        return OrderVH(RowOrdersBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))

    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: OrderVH, position: Int) {
        var current = listOrder[position]



         holder.dateOrder.text = current.date
         holder.timeOrder.text = current.time
         holder.countProduct.text = current.products.size.toString()

         var price = 0.0
             for (i in current.products)
             {
                 price += i.priceProduct
             }
        holder.totalPriceProduct.text ="${price} EGP"

//
//        Picasso.get().load(current.products.imageProduct.toString()).into(holder.imgProduct)
//            .toString()

        holder.btnRemove.setOnClickListener {

            OrderViewModel().removeOrderItem(current)
            Toast.makeText(context, "order removed", Toast.LENGTH_SHORT).show()
            listOrder.remove(current)
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

       // var imgProduct = item.imageOrderProduct
        var countProduct = item.OrderCountProduct
        var totalPriceProduct = item.orderPriceProduct

        var dateOrder = item.dateOrder
        var timeOrder = item.timeOrder
        var btnRemove = item.btnRemoveOrder

    }


}


//
//    fun getCurrentDate(): String {
//        val calendar = Calendar.getInstance()
//        val simpleDateFormat =
//            SimpleDateFormat("MMM dd, yyyy")
//        return simpleDateFormat.format(calendar.time)
//    }
//    fun getCurrentTime(): String {
//        val calendar = Calendar.getInstance()
//        val simpleDateFormat = SimpleDateFormat("HH:mm:ss a")
//        return simpleDateFormat.format(calendar.time)
//    }
//
//    fun getCurrentUser() : User {
//        var pvm = ProfileViewModel()
//        pvm.getUserInfo()
//        var currentUser : User = pvm.mGetUserInfo.value as User
//        return currentUser
//    }