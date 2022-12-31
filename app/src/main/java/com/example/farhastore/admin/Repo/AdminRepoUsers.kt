package com.example.farhastore.admin.Repo

import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.MutableLiveData
import com.example.farhastore.User.Util.constant
import com.example.farhastore.User.model.Products
import com.example.farhastore.User.model.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class adminRepoUsers {


    private var auth = Firebase.auth
    private var refDBUser = Firebase.database.reference.child("Users")
    var users = mutableListOf<User>()
    var mGetUsersInfo = MutableLiveData<MutableList<User>>()
    private var refStorage = Firebase.storage.reference
    private var pathPhotoProduct = refStorage.child("ProductImage")


    var mutableSuccessAddProduct = MutableLiveData<Boolean>()
    var mutableFailureAddProduct = MutableLiveData<String>()

    var mutableSuccessAddProductWithLinkImg = MutableLiveData<Boolean>()


    suspend fun getUser() {

        refDBUser.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                users.clear()
                for (i in snapshot.children) {
                    users.add(i.child("UserInfo").getValue<User>(User::class.java)!!)
                }


                mGetUsersInfo.postValue(users)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("profileRepo", "onCancelled: error  getUser ")
            }

        })
    }


    fun setProduct(products: Products, categoryName: String) {

        when (categoryName) {
            constant.HandMade -> {
                var path = constant.refHandMadeProduct.push()
                products.key = path.key!!

                // set  image Uri in Storage
                pathPhotoProduct.child(products.key).putFile(products.imageProduct.toUri())
                    .addOnCompleteListener {
                        pathPhotoProduct.child(products.key).downloadUrl.addOnCompleteListener {
                            products.imageProduct = "${it.result}"
                            path.setValue(products)

                            mutableSuccessAddProduct.value = it.isSuccessful
                        }
                            .addOnFailureListener { messsage ->
                                mutableFailureAddProduct.value = messsage.message
                            }
                    }
            }

            constant.Accessories -> {
                var path = constant.refAccessoriesProduct.push()
                products.key = path.key!!
                pathPhotoProduct.child(products.key).putFile(products.imageProduct.toUri())
                    .addOnCompleteListener {
                        pathPhotoProduct.child(products.key).downloadUrl.addOnCompleteListener {
                            products.imageProduct = "${it.result}"
                            path.setValue(products)

                        }
                    }
            }


            constant.Print -> {
                var path = constant.refPrintProduct.push()
                products.key = path.key!!
                pathPhotoProduct.child(products.key).putFile(products.imageProduct.toUri())
                    .addOnCompleteListener {
                        pathPhotoProduct.child(products.key).downloadUrl.addOnCompleteListener {
                            products.imageProduct = "${it.result}"
                            path.setValue(products)

                        }
                    }
            }


            constant.Laser -> {
                var path = constant.refLaserProduct.push()
                products.key = path.key!!
                pathPhotoProduct.child(products.key).putFile(products.imageProduct.toUri())
                    .addOnCompleteListener {
                        pathPhotoProduct.child(products.key).downloadUrl.addOnCompleteListener {
                            products.imageProduct = "${it.result}"
                            path.setValue(products)

                        }
                    }
            }


            constant.Skins -> {
                var path = constant.refSkinsProduct.push()
                products.key = path.key!!
                pathPhotoProduct.child(products.key).putFile(products.imageProduct.toUri())
                    .addOnCompleteListener {
                        pathPhotoProduct.child(products.key).downloadUrl.addOnCompleteListener {
                            products.imageProduct = "${it.result}"
                            path.setValue(products)

                        }
                    }

            }


            constant.Resin -> {
                var path = constant.refResinProduct.push()
                products.key = path.key!!
                pathPhotoProduct.child(products.key).putFile(products.imageProduct.toUri())
                    .addOnCompleteListener {
                        pathPhotoProduct.child(products.key).downloadUrl.addOnCompleteListener {
                            products.imageProduct = "${it.result}"
                            path.setValue(products)

                        }
                    }
            }
        }
    }


    fun setProductWithImgLink(products: Products, categoryName: String) {
        var refProduct = Firebase.database.reference.child("Products")
            .child("Category").child(categoryName)
        var path = refProduct.push()
        products.key = path.key!!
        path.setValue(products).addOnCompleteListener {
            mutableSuccessAddProductWithLinkImg.value = it.isSuccessful
        }

    }

    companion object {
        val instaneAdminRepo = adminRepoUsers()
    }
}