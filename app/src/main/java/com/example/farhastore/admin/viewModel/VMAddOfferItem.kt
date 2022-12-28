package com.example.farhastore.admin.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farhastore.User.model.Products
import com.example.farhastore.admin.Repo.RepoAddOfferItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VMAddOfferItem :ViewModel() {

    val repoAddOfferInstance = RepoAddOfferItem.getInstance()


    var mutableAddOfferSuccess = repoAddOfferInstance.mutableSuccessSetOffer
    var mutableAddOfferFailure = repoAddOfferInstance.mutableFailureSetOffer




    fun setOffer(product :Products)
    {
        viewModelScope.launch (Dispatchers.IO){
             repoAddOfferInstance.setOffersItem(product)
        }
    }




}