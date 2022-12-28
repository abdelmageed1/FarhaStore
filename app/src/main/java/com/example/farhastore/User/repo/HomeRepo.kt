package com.example.farhastore.User.repo

import androidx.lifecycle.MutableLiveData
import com.example.farhastore.User.Util.constant
import com.example.farhastore.User.model.Products
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class HomeRepo {

    private var refDB = Firebase.database.reference
    var mWelcomeMessage = MutableLiveData<String>()

    var mOffers: MutableLiveData<MutableList<Products>> = MutableLiveData()

    private var arrOffers      : MutableList<Products> = mutableListOf()


    suspend fun getWelComeMessageFromDB() {
        refDB.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                mWelcomeMessage.postValue(snapshot.child("welcomeMessage").value.toString())

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }


    suspend   fun getOffersFromDB(){
        constant.refDBOffers.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                arrOffers.clear()
                for (i in snapshot.children){
                    arrOffers.add(i.getValue<Products>(Products::class.java)!!)
                }
                mOffers.postValue(arrOffers)

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }


    companion object {
        val InstanceRepoHome = HomeRepo()
    }

}







