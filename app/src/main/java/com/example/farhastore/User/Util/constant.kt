package com.example.farhastore.User.Util

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

object constant {

    /*
    Handmade
    Accessories
    Laser
    Print
    Resin
    Skins
   */

    const val HandMade: String = "HandMade"
    const val Accessories: String = "Accessories"
    const val Laser: String = "Laser"
    const val Print: String = "Print"
    const val Resin: String = "Resin"
    const val Skins: String = "Skins"
    const val Browse: String = "Browse"
    const val offer: String = "Offers"
    const val adminUpdateProduct: String = "adminUpdate"
    const val ShowCategoryItems: String = "ShowCategoryItems"


    const val Email_Admin: String = "hagarfarha88@gmail.com"
    const val Pass_Admin: String = "farha2011"
    const val AdminSecureVerfiy: String = "H"




    var refHandMadeProduct = Firebase.database.reference.child("Products")
        .child("Category").child("HandMade")

    var refAccessoriesProduct = Firebase.database.reference.child("Products")
        .child("Category").child("Accessories")

    var refLaserProduct = Firebase.database.reference.child("Products")
        .child("Category").child("Laser")


    var refPrintProduct = Firebase.database.reference.child("Products")
        .child("Category").child("Print")


    var refResinProduct = Firebase.database.reference.child("Products")
        .child("Category").child("Resin")


    var refSkinsProduct = Firebase.database.reference.child("Products")
        .child("Category").child("Skins")

    var refDBOffers = Firebase.database.reference.child("Offers")


}