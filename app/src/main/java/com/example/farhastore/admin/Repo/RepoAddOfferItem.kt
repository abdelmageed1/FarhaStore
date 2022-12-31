package com.example.farhastore.admin.Repo

import androidx.core.net.toUri
import androidx.lifecycle.MutableLiveData
import com.example.farhastore.User.Util.constant
import com.example.farhastore.User.Util.constant.refDBOffers
import com.example.farhastore.User.model.Products
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class RepoAddOfferItem {



    private var auth = Firebase.auth

    private var refStorage = Firebase.storage.reference
    private var pathPhotoOffers = refStorage.child("ProductImageOffers")

    var mutableSuccessSetOffer =MutableLiveData<Boolean>()
    var mutableFailureSetOffer = MutableLiveData<String>()

    var mutableSuccessSetOfferWithLinkImg =MutableLiveData<Boolean>()

    suspend fun setOffersItem(products: Products) {

        var path = constant.refDBOffers.push()
        products.key = path.key!!
        pathPhotoOffers.child(products.key).putFile(products.imageProduct.toUri())
            .addOnCompleteListener {
                pathPhotoOffers.child(products.key).downloadUrl.addOnCompleteListener{
                    if (it.isSuccessful) {
                        products.imageProduct = it.result.toString()
                        path.setValue(products)
                        mutableSuccessSetOffer.value = true

                    }
                    else{
                        mutableFailureSetOffer.value = it.exception?.message
                    }

                }
            }
            .addOnFailureListener {
                mutableFailureSetOffer.value = it.message
            }


    }
    fun setOffersWithLinkImg(products: Products){
        var path = constant.refDBOffers.push()
        products.key = path.key!!
        path.setValue(products).addOnCompleteListener {
            mutableSuccessSetOfferWithLinkImg.value = it.isSuccessful
        }
    }


    companion object
    {
        private var addOfferRepoInstance: RepoAddOfferItem? = null

        fun getInstance():RepoAddOfferItem
        {
            if (addOfferRepoInstance == null )
            {
                addOfferRepoInstance = RepoAddOfferItem()
            }
            return  addOfferRepoInstance!!

        }


    }
}