package com.example.farhastore.User.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farhastore.User.model.Orders
import com.example.farhastore.User.repo.OrderRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderViewModel : ViewModel() {

    var repoOrder = OrderRepo.instanceRepoOrder


    fun setOrder(orders: Orders) {
        viewModelScope.launch(Dispatchers.IO) {
            repoOrder.setOrderToDB(orders)
        }
    }

    fun getOrder() {
        viewModelScope.launch(Dispatchers.Main) {

            repoOrder.getOrders()
        }
    }

    fun removeOrderItem(orders: Orders) {
        viewModelScope.launch(Dispatchers.Main) {
            repoOrder.removeFromOrder(orders)
        }
    }


    fun getMutableOrder() = repoOrder.mOrders


}