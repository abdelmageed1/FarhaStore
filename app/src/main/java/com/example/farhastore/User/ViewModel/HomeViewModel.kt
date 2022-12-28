package com.example.farhastore.User.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farhastore.User.repo.HomeRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    //
    var repoProducts = HomeRepo.InstanceRepoHome
    var mWelMessage = repoProducts.mWelcomeMessage


    fun getWelComeMessage() {
        viewModelScope.launch(Dispatchers.Main) {
            repoProducts.getWelComeMessageFromDB()
        }

    }

    fun getOffers(){
        viewModelScope.launch(Dispatchers.IO)  {
            repoProducts.getOffersFromDB()
        }
    }

    fun mutableOffers() = repoProducts.mOffers


//    }
//
//
//    fun MutableAllProduct() = mProduct
}