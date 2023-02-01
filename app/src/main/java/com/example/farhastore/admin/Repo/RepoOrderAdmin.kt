package com.example.farhastore.admin.Repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.farhastore.User.model.Orders
import com.example.farhastore.User.model.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RepoOrderAdmin {

    private var refDBUser = Firebase.database.reference.child("Users")
    var usersWhoseOrder = mutableListOf<User>()
    var mGetUsersWhoseOrder = MutableLiveData<MutableList<User>>()

    // orders
    private var refMyOrders = Firebase.database.reference.child("Users")
    var mOrders = MutableLiveData<MutableList<Orders>>()
    private var mGetUserInfo = MutableLiveData<User>()


    suspend fun getUserWhoseOrder() {

        refDBUser.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                    usersWhoseOrder.clear()
                    for (i in snapshot.children) {
                        if (i.hasChild("MyOrders")){
                            usersWhoseOrder.add(i.child("UserInfo").getValue<User>(User::class.java)!!)
                        }

                    }
                    mGetUsersWhoseOrder.postValue(usersWhoseOrder)

            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("profileRepo", "onCancelled: error  getUser ")
            }

        })
    }

   suspend fun getOrders(userId: String) {

        var orders = mutableListOf<Orders>()
        refMyOrders.child(userId).child("MyOrders").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                orders.clear()
                for (child in snapshot.children) {
                    orders.add(child.getValue(Orders::class.java)!! as Orders)
                }
                mOrders.postValue(orders)
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }

    fun confirm(userId: String,orders: Orders){
        refMyOrders.child(userId).child("MyOrders")
            .child("${orders.key}").child("confirmed").setValue(true)
    }


    fun removeAdminUOrder( userId: String , order: Orders) {
        refMyOrders.child(userId).child("MyOrders").child(order.key).removeValue()


    }


    companion object
    {
        private var instance :RepoOrderAdmin? = null

        fun getInstance() :RepoOrderAdmin? {
            if (instance == null)
                instance = RepoOrderAdmin()
            return instance
        }
    }

}