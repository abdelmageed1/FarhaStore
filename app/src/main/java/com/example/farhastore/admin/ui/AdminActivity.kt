package com.example.farhastore.admin.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.farhastore.R
import com.example.farhastore.databinding.ActivityAdminBinding

class AdminActivity : AppCompatActivity() {
    lateinit var binding: ActivityAdminBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        var adminNavController = findNavController(R.id.adminFragmentContainerView)
        var adminNavBar = binding.adminBottomNavigationView
        adminNavBar.setupWithNavController(adminNavController)


//    var navController = findNavController(R.id.fragmentContainerView)
//    var navBar = binding.bottomNavigationView
//    navBar.setupWithNavController(navController)

    }

}