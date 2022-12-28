package com.example.farhastore.admin.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farhastore.admin.Repo.adminRepoUsers
import com.example.farhastore.User.model.Products
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AdminViewModel :ViewModel() {


    var adminRepo = adminRepoUsers.instaneAdminRepo

    var mFailureAddProduct = adminRepo.mutableFailureAddProduct
    var mSuccessAddProduct = adminRepo.mutableSuccessAddProduct


    fun getAllUsers(){
     viewModelScope.launch (Dispatchers.IO){
         adminRepo.getUser()
     }

    }

    fun setProduct(products: Products, categoryName :String)
    {
        adminRepo.setProduct(products , categoryName)
    }

    fun mGetAllUsers() =adminRepo.mGetUsersInfo

}