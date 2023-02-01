package com.example.farhastore.User.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build.VERSION_CODES.R
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.farhastore.R
import com.example.farhastore.User.ViewModel.AuthViewModel
import com.example.farhastore.User.model.User
import com.example.farhastore.databinding.ActivitySignUpBinding

class SignUp : AppCompatActivity() {
    private lateinit var user: User
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // color text of status bar
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        binding.signLogo.animate().scaleXBy(1f).duration = 2000


        setSuccessObserver()
        setFailureObserver()



        binding.btnSignup.setOnClickListener {

            hideBtnSignUp()
            showProgress()
            signUpAndSendData()
        }

        binding.tvGotoLogin.setOnClickListener {
            startActivity(Intent(applicationContext, Login::class.java))
            finish()
        }


    }


    private fun setSuccessObserver() {
        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        authViewModel.getSuccessSignUpMutable().observe(this) {
            if (it != null) {
                Toast.makeText(applicationContext, "create success ", Toast.LENGTH_SHORT).show()
                authViewModel.initUserInfo(user)
                startActivity(Intent(this, HomeActivity::class.java))
                finish()

            }


        }
    }

    private fun setFailureObserver() {
        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        authViewModel.getFailureSignUpMutable().observe(this) {
            Toast.makeText(applicationContext, "${it.toString()}", Toast.LENGTH_SHORT).show()
            hideProgress()
            showBtnSignUp()
        }
    }


    private fun signUpAndSendData() {

        var fName = binding.etSignupFname.text.toString().trimStart().trimEnd()
        var lName = binding.etSignupLname.text.toString().trimStart().trimEnd()
        var email = binding.etSignupMail.text.toString().trimStart().trimEnd()
        var password = binding.etSignupPassword.text.toString().trimStart().trimEnd()
        var phone = binding.etSignupPhone.text.toString().trimStart().trimEnd()
        var address = binding.etUpdateAddress.text.toString().trimStart().trimEnd()
        var gender = getGender()

        if (email.isNotEmpty() && password.isNotEmpty() && fName.isNotEmpty() && lName.isNotEmpty()  && address.isNotEmpty() && gender != null) {
            if (phone.length == 11) {
                user = User("",fName, lName, email, password, phone, gender, address= address, "")

                authViewModel.createNewUser(email, password)
            } else {
                binding.etSignupPhone.error = "Enter valid phone number"
                showBtnSignUp()
                hideProgress()

            }


        } else {
            Toast.makeText(applicationContext,
                "${resources.getString(R.string.complete_this_fields)}",
                Toast.LENGTH_SHORT).show()
            hideProgress()
            showBtnSignUp()

        }

    }

    @SuppressLint("SuspiciousIndentation")
    private fun getGender(): String? {

        var radioSelectedId = binding.radioGroupGender.checkedRadioButtonId

        if (radioSelectedId == R.id.rb_btm_female) return "female"
        else if (radioSelectedId == R.id.rb_btm_male) return "male"
        else return null

    }

    private fun showProgress() {
        binding.progressSignup.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        binding.progressSignup.visibility = View.GONE
    }

    private fun showBtnSignUp() {
        binding.btnSignup.visibility = View.VISIBLE
    }

    private fun hideBtnSignUp() {
        binding.btnSignup.visibility = View.GONE
    }


}

