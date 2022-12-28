package com.example.farhastore.User.repo

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.farhastore.User.model.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.UploadTask
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProfileRepo {
    private var auth = Firebase.auth
    private var refStorage = Firebase.storage.reference
    private var refDBUser = Firebase.database.reference.child("Users")
    private var pathPhoto = refStorage.child("ProfilePhoto/${auth.currentUser?.uid.toString()}")
    private var mUploadSucPhoto = MutableLiveData<UploadTask.TaskSnapshot?>()
    private var mUploadFailPhoto = MutableLiveData<String>()
    private var mGetUserInfo = MutableLiveData<User>()

    private var mDownloadSuccessProfileImage = MutableLiveData<String>()
    private var mDownloadFailureProfileImage = MutableLiveData<String>()

    private var arrUserInfo = mutableListOf<User>()
    lateinit var user: User

    // upload photo
    suspend fun upLoadProfileImage(uri: Uri) {
        pathPhoto.putFile(uri).addOnSuccessListener {
            mUploadSucPhoto.postValue(it)
            GlobalScope.launch(Dispatchers.IO) { downloadProfileImage() }

        }.addOnFailureListener {
            mUploadFailPhoto.postValue(it.message)
        }

    }


    suspend fun downloadProfileImage() {
        pathPhoto.downloadUrl.addOnSuccessListener {
            mDownloadSuccessProfileImage.postValue(it.toString())
            refDBUser.child("${auth.currentUser?.uid}").child("UserInfo").child("photo")
                .setValue("$it")

        }.addOnFailureListener {
            mDownloadFailureProfileImage.postValue(it.message)
        }
    }

    suspend fun signOut() {
        Firebase.auth.signOut()
    }


    fun getUser() {

        refDBUser.child("${auth.currentUser?.uid}").child("UserInfo")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    user = snapshot.getValue<User>(User::class.java)!!
                    mGetUserInfo.postValue(snapshot.getValue<User>(User::class.java))

                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d("profileRepo", "onCancelled: error  getUser ")
                }

            })
    }


    suspend fun setUpdateUserInfo(user: User) {

        refDBUser.child("${auth.currentUser?.uid}").child("UserInfo").child("fname")
            .setValue(user.fName)
        refDBUser.child("${auth.currentUser?.uid}").child("UserInfo").child("lname")
            .setValue(user.lName)
        refDBUser.child("${auth.currentUser?.uid}").child("UserInfo").child("phone")
            .setValue(user.phone)
        refDBUser.child("${auth.currentUser?.uid}").child("UserInfo").child("address")
            .setValue(user.address)
    }


    fun upDataSuccessProfileImage() = mUploadSucPhoto
    fun upDataFailureProfileImage() = mUploadFailPhoto

    fun downloadSuccessProfileImage() = mDownloadSuccessProfileImage
    fun downloadFailureProfileImage() = mDownloadFailureProfileImage

    //
    //user Info
    fun getUserInfo() = mGetUserInfo

}