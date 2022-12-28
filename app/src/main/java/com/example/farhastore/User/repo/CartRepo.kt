package com.example.farhastore.User.repo

import androidx.lifecycle.MutableLiveData
import com.example.farhastore.User.model.Products
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class CartRepo {
    private val auth: FirebaseAuth = Firebase.auth
    private var refMyCart = Firebase.database.reference.child("Users")
        .child(auth.currentUser!!.uid).child("MyCart")

    var mCartItem = MutableLiveData<MutableList<Products>>()
    //  var mRemoveItem :MutableLiveData<> = MutableLiveData()


    suspend fun setProductToCArtDB(products: Products) {
        var path = refMyCart.push()
        products.key = path.key.toString()
        //  products.imageProduct
        path.setValue(products)
    }

    suspend fun getProductFromCrtDB() {

        var arrItem: MutableList<Products> = mutableListOf()
        refMyCart.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                arrItem.clear()
                for (i in snapshot.children) {
                    arrItem.add(i.getValue<Products>(Products::class.java)!!)
                }
                mCartItem.postValue(arrItem)
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }

    fun removeFromCart(products: Products) {
        refMyCart.child(products.key).removeValue()


    }


    companion object {
        fun instanceCartRepo() = CartRepo()
    }

}
