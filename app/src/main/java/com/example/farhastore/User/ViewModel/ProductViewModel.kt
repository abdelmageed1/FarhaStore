package com.example.farhastore.User.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farhastore.User.repo.ProductRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {

    /*
       Handmade
       Accessories
       Laser
       Print
       Resin
       Skins
      */

    var repoProducts = ProductRepo.InstanceProductRepo




     fun getHandmadeProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            repoProducts.getHandmadeProductSFromDB()
        }


    }

       fun getAccessoriesProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            repoProducts.getAccessoriesProductSFromDB()
        }


    }


      fun getLaserProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            repoProducts.getLaserProductSFromDB()
        }


    }


       fun getPrintProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            repoProducts.getPrintProductSFromDB()

        }

    }


       fun getResinProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            repoProducts.getResinProductSFromDB()
        }


    }


      fun getSkinsProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            repoProducts.getSkinsProductSFromDB()
        }

    }



//
//    fun setProduct(products: Products  , categoryName :String)
//    {
//        repoProducts.setProduct(products , categoryName)
//    }
//    fun setImageProduct(uri: Uri){
//        repoProducts.setImageProduct(uri)
//    }

    //

        fun mutableHandmadeProduct() = repoProducts.mHandMadeProducts
        fun mutableAccessoriesProduct() = repoProducts.mAccessoriesProducts
        fun mutableLaserProduct() = repoProducts.mLaserProducts
        fun mutablePrintProduct() = repoProducts.mPrintProducts
        fun mutableResinProduct() = repoProducts.mResinProducts
       fun mutableSkinsProduct() = repoProducts.mSkinsProducts



}