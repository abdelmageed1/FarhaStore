package com.example.farhastore.User.repo

import androidx.lifecycle.MutableLiveData
import com.example.farhastore.User.Util.constant
import com.example.farhastore.User.model.Products
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue

class ProductRepo {


    // Mutable Live DAta


    var mHandMadeProducts: MutableLiveData<MutableList<Products>> = MutableLiveData()
    var mAccessoriesProducts: MutableLiveData<MutableList<Products>> = MutableLiveData()
    var mLaserProducts: MutableLiveData<MutableList<Products>> = MutableLiveData()
    var mPrintProducts: MutableLiveData<MutableList<Products>> = MutableLiveData()
    var mResinProducts: MutableLiveData<MutableList<Products>> = MutableLiveData()
    var mSkinsProducts: MutableLiveData<MutableList<Products>> = MutableLiveData()



    private var arrAllProduct: MutableList<Products> = mutableListOf()


    private var arrHandmadeProduct: MutableList<Products> = mutableListOf()
    private var arrAccessoriesProduct: MutableList<Products> = mutableListOf()
    private var arrLaserProduct: MutableList<Products> = mutableListOf()
    private var arrPrintProduct: MutableList<Products> = mutableListOf()
    private var arrResinProduct: MutableList<Products> = mutableListOf()
    private var arrSkinsProduct: MutableList<Products> = mutableListOf()




    suspend fun getHandmadeProductSFromDB() {

        constant.refHandMadeProduct.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                arrHandmadeProduct.clear()
                for (i in snapshot.children) {
                    arrHandmadeProduct.add(i.getValue<Products>(Products::class.java)!!)
                }
                arrAllProduct.addAll(arrHandmadeProduct)

                mHandMadeProducts.postValue(arrHandmadeProduct)
            }

            override fun onCancelled(error: DatabaseError) {}
        })


    }




    suspend fun getAccessoriesProductSFromDB() {
        constant.refAccessoriesProduct.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                arrAccessoriesProduct.clear()
                for (i in snapshot.children) {
                    arrAccessoriesProduct.add(i.getValue<Products>(Products::class.java)!!)

                }
                arrAllProduct.addAll(arrAccessoriesProduct)
                mAccessoriesProducts.postValue(arrAccessoriesProduct)


            }

            override fun onCancelled(error: DatabaseError) {}

        })

    }


    suspend fun getLaserProductSFromDB() {
        constant.refLaserProduct.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                arrLaserProduct.clear()
                for (i in snapshot.children) {
                    arrLaserProduct.add(i.getValue<Products>(Products::class.java)!!)

                }
                mLaserProducts.postValue(arrLaserProduct)


            }

            override fun onCancelled(error: DatabaseError) {}

        })
    }

    suspend fun getPrintProductSFromDB() {
        constant.refPrintProduct.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                arrPrintProduct.clear()
                for (i in snapshot.children) {
                    arrPrintProduct.add(i.getValue<Products>(Products::class.java)!!)

                }
                mPrintProducts.postValue(arrPrintProduct)

            }

            override fun onCancelled(error: DatabaseError) {}

        })

    }


    suspend fun getResinProductSFromDB() {
        constant.refResinProduct.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                arrResinProduct.clear()
                for (i in snapshot.children) {
                    arrResinProduct.add(i.getValue<Products>(Products::class.java)!!)

                }
                mResinProducts.postValue(arrResinProduct)


            }

            override fun onCancelled(error: DatabaseError) {}

        })

    }


    suspend fun getSkinsProductSFromDB() {
        constant.refSkinsProduct.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                arrSkinsProduct.clear()
                for (i in snapshot.children) {
                    arrSkinsProduct.add(i.getValue<Products>(Products::class.java)!!)

                }
                mSkinsProducts.postValue(arrSkinsProduct)


            }

            override fun onCancelled(error: DatabaseError) {}

        })

    }




    companion object {
        val InstanceProductRepo = ProductRepo()
    }


}









