package com.example.farhastore.admin.Repo

import androidx.lifecycle.MutableLiveData
import com.example.farhastore.User.Util.constant
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RepoUpdateItem {

   private var mutableUpdateProduct = MutableLiveData<Boolean>()
   private var mutableDeleteProduct = MutableLiveData<Boolean>()
   private var mutableDeleteOffer = MutableLiveData<Boolean>()

    fun updateProduct(
        key: String,
        category: String,
        newName: String,
        newPrice: Double,
        newDesc: String,
        newLinkImg: String
    ) {
        when (category) {
            constant.HandMade -> {
                constant.refHandMadeProduct.child(key).child("nameProduct").setValue(newName)
                constant.refHandMadeProduct.child(key).child("priceProduct").setValue(newPrice)
                constant.refHandMadeProduct.child(key).child("descriptionProduct")
                    .setValue(newDesc).addOnCompleteListener {
                        mutableUpdateProduct.value = it.isSuccessful
                    }
                if (newLinkImg.isNotEmpty()) {
                    constant.refHandMadeProduct.child(key).child("imageProduct")
                        .setValue(newLinkImg)
                }
            }


            constant.Accessories -> {
                constant.refAccessoriesProduct.child(key).child("nameProduct").setValue(newName)
                constant.refAccessoriesProduct.child(key).child("priceProduct").setValue(newPrice)
                constant.refAccessoriesProduct.child(key).child("descriptionProduct")
                    .setValue(newDesc).addOnCompleteListener {
                        mutableUpdateProduct.value = it.isSuccessful
                    }
                if (newLinkImg.isNotEmpty()) {
                    constant.refAccessoriesProduct.child(key).child("imageProduct")
                        .setValue(newLinkImg)
                }
            }

            constant.Print -> {
                constant.refPrintProduct.child(key).child("nameProduct").setValue(newName)
                constant.refPrintProduct.child(key).child("priceProduct").setValue(newPrice)
                constant.refPrintProduct.child(key).child("descriptionProduct")
                    .setValue(newDesc).addOnCompleteListener {
                        mutableUpdateProduct.value = it.isSuccessful
                    }
                if (newLinkImg.isNotEmpty()) {
                    constant.refPrintProduct.child(key).child("imageProduct")
                        .setValue(newLinkImg)
                }
            }

            constant.Resin -> {
                constant.refResinProduct.child(key).child("nameProduct").setValue(newName)
                constant.refResinProduct.child(key).child("priceProduct").setValue(newPrice)
                constant.refResinProduct.child(key).child("descriptionProduct")
                    .setValue(newDesc).addOnCompleteListener {
                        mutableUpdateProduct.value = it.isSuccessful
                    }
                if (newLinkImg.isNotEmpty()) {
                    constant.refResinProduct.child(key).child("imageProduct")
                        .setValue(newLinkImg)
                }}

            constant.Skins -> {
                constant.refSkinsProduct.child(key).child("nameProduct").setValue(newName)
                constant.refSkinsProduct.child(key).child("priceProduct").setValue(newPrice)
                constant.refSkinsProduct.child(key).child("descriptionProduct")
                    .setValue(newDesc).addOnCompleteListener {
                        mutableUpdateProduct.value = it.isSuccessful
                    }
                if (newLinkImg.isNotEmpty()) {
                    constant.refSkinsProduct.child(key).child("imageProduct")
                        .setValue(newLinkImg)
                }
            }

            constant.Laser -> {
                constant.refLaserProduct.child(key).child("nameProduct").setValue(newName)
                constant.refLaserProduct.child(key).child("priceProduct").setValue(newPrice)
                constant.refLaserProduct.child(key).child("descriptionProduct")
                    .setValue(newDesc).addOnCompleteListener {
                        mutableUpdateProduct.value = it.isSuccessful
                    }
                if (newLinkImg.isNotEmpty()) {
                    constant.refLaserProduct.child(key).child("imageProduct")
                        .setValue(newLinkImg)
                }
            }


            constant.offer ->{
                 constant.refDBOffers.child(key).child("nameProduct").setValue(newName)
                constant.refDBOffers.child(key).child("priceProduct").setValue(newPrice)
                constant.refDBOffers.child(key).child("descriptionProduct")
                    .setValue(newDesc).addOnCompleteListener {
                        mutableUpdateProduct.value = it.isSuccessful
                    }
                if (newLinkImg.isNotEmpty()) {
                    constant.refDBOffers.child(key).child("imageProduct")
                        .setValue(newLinkImg)
                }
            }

        }
    }



    fun deleteProduct ( key: String ,category: String) {
        Firebase.database.reference.child("Products")
            .child("Category").child(category).child(key).removeValue()
            .addOnCompleteListener{
                mutableDeleteProduct.value = it.isSuccessful
            }
    }

        fun deleteOffersDB ( key: String ) {
            constant.refDBOffers.child(key).removeValue()
            .addOnCompleteListener{
                mutableDeleteOffer.value = it.isSuccessful
            }
    }




    fun chickIsCompleteUpdate() = mutableUpdateProduct
    fun mDeleteProduct () = mutableDeleteProduct
    fun mDeleteOffers () = mutableDeleteOffer

    companion object{
        private var instance :RepoUpdateItem? = null
        fun getInstance():RepoUpdateItem{
            if (instance == null)
            {
                instance = RepoUpdateItem()
            }
            return instance!!
        }
    }

}