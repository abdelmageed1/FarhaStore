package com.example.farhastore.User.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.farhastore.R
import com.example.farhastore.User.Util.constant
import com.example.farhastore.User.ViewModel.AuthViewModel
import com.example.farhastore.admin.ui.AdminActivity
import com.example.farhastore.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // color text of status bar
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        binding.signLogo.animate().scaleXBy(1f).duration = 2000

        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        setSuccessLoginObserve()
        setFailureLoginObserve()
        setAdminSuccessLoginObserve()

        binding.btnLogin.setOnClickListener {

            hideBtnSignUp()
            showProgress()
            login()
        }





        binding.tvCreateAccount.setOnClickListener {
            startActivity(Intent(applicationContext, SignUp::class.java))
            finish()
        }
    }


    @SuppressLint("SuspiciousIndentation")
    private fun setSuccessLoginObserve() {
        authViewModel.getSuccessLoginMutable().observe(this) {
            if (it != null)
                Toast.makeText(applicationContext, "login success", Toast.LENGTH_SHORT).show()
            startActivity(Intent(applicationContext, HomeActivity::class.java))
            finish()
        }

    }

    private fun setAdminSuccessLoginObserve() {
        authViewModel.getAdminSuccessLogin().observe(this) {
            if (it != null)
                Toast.makeText(applicationContext, "login Admin success", Toast.LENGTH_SHORT).show()
            startActivity(Intent(applicationContext, AdminActivity::class.java))
            finish()
        }

    }

    private fun setFailureLoginObserve() {
        authViewModel.getFailureLoginMutable().observe(this) {
            Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
            hideProgress()
            showBtnSignUp()
        }

    }

    fun login() {

        var email = binding.etLoginEmail.text.toString().trim()
        var pass = binding.etLoginPassword.text.toString().trim()
        if (email.isNotEmpty() && pass.isNotEmpty()) {
            if (email == constant.Email_Admin && pass == constant.Pass_Admin) {
                adminMethod(email, pass)

            } else {
                authViewModel.login(email, pass)
            }

        } else {
            Toast.makeText(applicationContext,
                "${resources.getString(R.string.complete_this_fields)}",
                Toast.LENGTH_SHORT).show()
            hideProgress()
            showBtnSignUp()
        }
    }


    private fun showProgress() {
        binding.progressLogin.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        binding.progressLogin.visibility = View.GONE
    }

    private fun showBtnSignUp() {
        binding.btnLogin.visibility = View.VISIBLE
    }

    private fun hideBtnSignUp() {
        binding.btnLogin.visibility = View.GONE
    }

    fun adminMethod(email: String, pass: String) {
        binding.btnOkAdmin.visibility = View.VISIBLE
        binding.etVerfiyAdmin.visibility = View.VISIBLE
        binding.btnOkAdmin.setOnClickListener {
            var verify = binding.etVerfiyAdmin.text.toString()
            if (verify == constant.AdminSecureVerfiy) {
                authViewModel.loginAdmin(email, pass)
            }
        }

    }


}








