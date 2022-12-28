package com.example.farhastore.User.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farhastore.User.model.Products
import com.example.farhastore.User.repo.CartRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartViewModel:ViewModel() {

    var cartRepo = CartRepo.instanceCartRepo()

    fun setItemToCart(products: Products)
    {
        viewModelScope.launch(Dispatchers.Main) {
            cartRepo.setProductToCArtDB(products)
        }

    }

    fun getCartItems( )
    {
        viewModelScope.launch(Dispatchers.Main) {
            cartRepo.getProductFromCrtDB()
        }

    }

    fun removeCartItem(products: Products)
    {
        viewModelScope.launch(Dispatchers.Main) {
            cartRepo.removeFromCart(products)
        }
    }


    fun getMutableCartItem() = cartRepo.mCartItem







}