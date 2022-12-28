package com.example.farhastore.User.repo

import androidx.core.net.toUri
import androidx.lifecycle.MutableLiveData
import com.example.farhastore.User.model.Products
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class RepoReedOffersItm {

    private var auth = Firebase.auth
    private var refDBOffers = Firebase.database.reference.child("Offers")
    private var refStorage = Firebase.storage.reference
    private var pathPhotoOffers = refStorage.child("ProductImageOffers")

    var mutableSuccessSetOffer = MutableLiveData<Boolean>()
    var mutableFailureSetOffer = MutableLiveData<String>()

//
//    suspend fun setOffersItem(products: Products) {
//
//        var path = refDBOffers.push()
//        products.key = path.key!!
//        pathPhotoOffers.child(products.key).putFile(products.imageProduct.toUri())
//            .addOnCompleteListener {
//                if (it.isSuccessful) {
//                    products.imageProduct = it.result.toString()
//                    path.setValue(products)
//                    mutableSuccessSetOffer.value = true
//
//                }
//            }
//            .addOnFailureListener {
//                mutableFailureSetOffer.value = it.message
//            }
//
//
//    }


}