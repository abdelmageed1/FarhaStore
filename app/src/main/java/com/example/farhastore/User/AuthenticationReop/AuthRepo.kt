package com.example.farhastore.User.AuthenticationReop


import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.farhastore.User.model.User
import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class AuthRepo(var application: Application) {
    private val TAG = "main1"


    private val auth: FirebaseAuth = Firebase.auth
    private var database = Firebase.database
    private var successUserSignUpMutableLiveData = MutableLiveData<FirebaseUser>()
    private var failureSignUpMutableLiveData = MutableLiveData<String>()

    private var successLoginMutableLiveData = MutableLiveData<FirebaseUser>()

    private var failureLoginMutableLiveData = MutableLiveData<String>()
   // admin
      var mAdminSuccessLogin= MutableLiveData<FirebaseUser>()



    fun createNewUser(email: String, pass: String) {
        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    successUserSignUpMutableLiveData.postValue(auth.currentUser)
                    Log.d(TAG, "createNewUser: success User SignUp Mutable Live Data")

                }
            }
            .addOnFailureListener {
                Log.d(TAG, "createNewUser: ${it.message}")

                failureSignUpMutableLiveData.postValue(it.message)
            }
    }

    fun loginRepo(email: String, pass: String) {
        auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    successLoginMutableLiveData.postValue(auth.currentUser)
                    Log.d(TAG, "createNewUser: success Login Mutable Live Data")
                }

            }
            .addOnFailureListener {
                failureLoginMutableLiveData.postValue(it.message)
            }
    }

  fun loginAdmin(email: String, pass: String) {
        auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    mAdminSuccessLogin.postValue(auth.currentUser)
                    Log.d(TAG, "createNewUser: success Login Mutable Live Data")
                }

            }
            .addOnFailureListener {
                failureLoginMutableLiveData.postValue(it.message)
            }
    }

    fun getSuccessSignUpMutable() = successUserSignUpMutableLiveData
    fun getFailureSignUpMutable() = failureSignUpMutableLiveData
    fun getSuccessLoginMutable() = successLoginMutableLiveData
    fun getFailureLoginMutable() = failureLoginMutableLiveData

   // admin


    fun sendUserData(user: User) {

        var refUser = database.getReference("Users")
        refUser.child(auth.currentUser!!.uid).child("UserInfo").setValue(user)
    }



fun getCurrentUser()= Firebase.auth.currentUser




}

//    fun sendVerfiy() {
//    val user = Firebase.auth.currentUser
//
//    user!!.sendEmailVerification()
//        .addOnCompleteListener { task ->
//            if (task.isSuccessful) {
//                Toast.makeText(baseContext, "Email sent. ", Toast.LENGTH_SHORT).show()
//
//            }
//        }
//        .addOnFailureListener {
//            Toast.makeText(baseContext, "Email sent. Error ", Toast.LENGTH_SHORT).show()
//        }
//    }