package com.example.farhastore.User.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.farhastore.User.AuthenticationReop.AuthRepo
import com.example.farhastore.User.model.User


class AuthViewModel(application: Application) : AndroidViewModel(application) {
    var authRepo = AuthRepo(application)
    private var successSignUpMutableLiveData = authRepo.getSuccessSignUpMutable()
    private var failureSignUpMutableLiveData = authRepo.getFailureSignUpMutable()

    private var successLoginMutableLiveData  = authRepo.getSuccessLoginMutable()
    private var failureLoginMutableLiveData  = authRepo.getFailureLoginMutable()




    fun createNewUser(email: String, password: String) {
        authRepo.createNewUser(email, password)
    }

    // send user data to firebase
    fun initUserInfo(user: User) {
        authRepo.sendUserData(user)
    }

    fun login(email: String, password: String) {
        authRepo.loginRepo(email, password)
    }


    // admin
      fun loginAdmin(email: String, password: String) {
        authRepo.loginAdmin(email, password)
    }


// sign up
    fun getSuccessSignUpMutable() = successSignUpMutableLiveData
    fun getFailureSignUpMutable() = failureSignUpMutableLiveData
// login
    fun getSuccessLoginMutable() = successLoginMutableLiveData
    fun getFailureLoginMutable() = failureLoginMutableLiveData

  // login admin
  fun getAdminSuccessLogin() =  authRepo.mAdminSuccessLogin

//




}