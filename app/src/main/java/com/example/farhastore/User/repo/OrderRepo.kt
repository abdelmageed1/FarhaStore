package com.example.farhastore.User.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.farhastore.User.model.Orders
import com.example.farhastore.User.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class OrderRepo {


    private lateinit var user: User

    private val auth: FirebaseAuth = Firebase.auth
    var mOrders = MutableLiveData<MutableList<Orders>>()
    private var refDBUser = Firebase.database.reference.child("Users")

    private var refMyOrders = Firebase.database.reference.child("Users")
        .child(auth.currentUser!!.uid).child("MyOrders")


    suspend fun setOrderToDB(order: Orders) {

        var path = refMyOrders.push()
        order.key = path.key.toString()
        order.userId = auth.currentUser!!.uid

        path.setValue(order)

    }

    suspend fun getOrders() {

        //    var arrOrders = mutableListOf<Orders>()

        var orders = mutableListOf<Orders>()
        refMyOrders.addValueEventListener(object : ValueEventListener {
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
//    refMyOrders.addValueEventListener(object : ValueEventListener {
//
//            override fun onDataChange(snapshot: DataSnapshot) {
//                arrOrders.clear()
//                 for (i in snapshot.children)
//                 {
//                     arrOrders.add(i.getValue<Orders>(Orders::class.java)!!)
//                 }
//                mOrders.postValue(arrOrders)
//            }
//            override fun onCancelled(error: DatabaseError) {
//            }
//        })


    }


    fun removeFromOrder(order: Orders) {
        refMyOrders.child(order.key).removeValue()


    }

    fun getUser() {

        refDBUser.child("${auth.currentUser?.uid}").child("UserInfo")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    user = snapshot.getValue<User>(User::class.java)!!

                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d("profileRepo", "onCancelled: error  getUser ")
                }

            })
    }


    companion object {
        var instanceRepoOrder = OrderRepo()

    }

}