package com.example.farhastore.admin.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.farhastore.User.model.User
import com.example.farhastore.databinding.FragmentAdminViewUserInfoBinding
import com.squareup.picasso.Picasso


class AdminViewUserInfoFragment : Fragment() {
    lateinit var binding: FragmentAdminViewUserInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentAdminViewUserInfoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (user.photo.isNotEmpty()) {
            Picasso.get().load(user.photo).into(binding.imgViewUser)
        }


        binding.tvNameViewUser.text = "${user.fName}  ${user.lName} "
        binding.tvEmailViewUser.text = user.email
        binding.tvAddressViewUser.text = user.address
        binding.tvMobileViewUser.text = user.phone
        binding.tvGenderViewUser.text = user.gender


        binding.tvMobileViewUser.setOnClickListener {
            goToUserWhatsApp(user.phone)
        }
    }
    fun goToUserWhatsApp(phone :String){
        var intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("https://wa.me/2${phone.trim()}")
        startActivity(intent)


    }

    companion object {
        lateinit var user: User

        fun getUser(user: User) {
            this.user = user
        }


    }
}