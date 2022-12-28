package com.example.farhastore.User.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


 @Parcelize
 class Products (
     var key :String = "",
     var nameProduct :String ="",
    var descriptionProduct : String ="",
     var imageProduct :String ="",
     var priceProduct : Double =0.0
         ) :Parcelable
