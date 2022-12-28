package com.example.farhastore.User.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
   class Orders (
    var key :String = "",
    var userId: String ="",
    var date :String= "",
    var time:String ="",
    var products : MutableList<Products>,
    var isConfirmed :Boolean= false

     ):Parcelable {
       constructor():this("","","","", mutableListOf<Products>(),false)
     }


