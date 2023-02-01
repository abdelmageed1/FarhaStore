package com.example.farhastore.User.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class User (

    var userId:String ="",
    var fName:String ="",
    var lName:String ="",
    var email :String ="" ,
    var password :String ="" ,
    var phone :String ="" ,
    var gender :String ="" ,
    var address :String ="" ,
    var photo :String = ""

        ):Parcelable
