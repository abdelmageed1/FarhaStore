package com.example.farhastore.User.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.farhastore.R
import com.example.farhastore.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    // lateinit var binding: ActivityHomeBinding
    lateinit var binding: ActivityHomeBinding
    var img: Int = 0

    var presed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
//        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(binding.root)

        // color text of status bar
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        var navController = findNavController(R.id.fragmentContainerView)
        var navBar = binding.bottomNavigationView
        navBar.setupWithNavController(navController)


    }


//    override fun onBackPressed() {
//        if (presed) {
//            super.onBackPressed()
//        } else {
//            presed = true
//            var text = getString(R.string.presed)
//
//            Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
//            GlobalScope.launch(Dispatchers.Main) {
//                delay(3000)
//                presed = false
//            }
//        }
//    }
}