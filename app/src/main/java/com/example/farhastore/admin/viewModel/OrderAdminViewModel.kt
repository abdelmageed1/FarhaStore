package com.example.farhastore.admin.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farhastore.User.model.Orders
import com.example.farhastore.admin.Repo.RepoOrderAdmin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderAdminViewModel : ViewModel() {

   var repoOrder = RepoOrderAdmin.getInstance()

    var mUserWhoseOrder = repoOrder?.mGetUsersWhoseOrder

    var mOrder = repoOrder?.mOrders

    fun getUserWhoseOrder(){
        viewModelScope.launch (Dispatchers.IO){
            repoOrder?.getUserWhoseOrder()
        }
    }
    fun removeOrderItem( uid:String ,orders: Orders) {
        viewModelScope.launch(Dispatchers.Main) {
            repoOrder?.removeAdminUOrder(uid,orders)
        }
    }



    fun getOrders( userId :String){
        viewModelScope.launch (Dispatchers.IO){
            repoOrder?.getOrders( userId )
        }
    }


}