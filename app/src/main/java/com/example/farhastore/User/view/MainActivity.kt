package com.example.farhastore.User.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.farhastore.User.Util.constant
import com.example.farhastore.User.AuthenticationReop.AuthRepo
import com.example.farhastore.admin.ui.AdminActivity
import com.example.farhastore.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding


    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // binding.splashLogo.animate().translationYBy(1000f).scaleXBy(1f).duration=1500


         var currentuser = AuthRepo(application).getCurrentUser()

        GlobalScope.launch(Dispatchers.Main) {
            delay(2500)


            if (currentuser == null) {
                goToLogin()
            } else {
                if (currentuser.email == constant.Email_Admin) {
                    goAdmin()
                } else {
                    goToHome()
                }

            }


        }

    }


    private fun goToLogin() {
        var intent = Intent(this, Login::class.java)
        startActivity(intent)
        finish()
    }

    private fun goToHome() {
        var intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun goAdmin() {
        var intent = Intent(this, AdminActivity::class.java)
        startActivity(intent)
        finish()
    }


}