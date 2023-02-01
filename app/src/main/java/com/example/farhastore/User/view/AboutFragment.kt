package com.example.farhastore.User.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.farhastore.R
import com.example.farhastore.databinding.FragmentAboutBinding


class AboutFragment : Fragment() {
    lateinit var binding: FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imgBack.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_aboutFragment_to_profileFragment)

        }
        binding.gotToPageFaceBook.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.facebook.com/Hagarofficia?mibextid=ZbWKwL")
            startActivity(intent)
        }

    }

    companion object {


    }
}