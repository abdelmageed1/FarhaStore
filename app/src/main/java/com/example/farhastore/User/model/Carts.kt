package com.example.farhastore.User.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Carts  (

    val products : MutableList<Products> = mutableListOf()

        ): Parcelable
