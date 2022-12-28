package com.example.farhastore.User.ViewModel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farhastore.User.model.User
import com.example.farhastore.User.repo.ProfileRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    var profileRepo = ProfileRepo()
    var mUpSuccess = profileRepo.upDataSuccessProfileImage()
    var mUpFailure = profileRepo.upDataFailureProfileImage()
    var mDownSuccess = profileRepo.downloadSuccessProfileImage()
    var mDownFailure = profileRepo.downloadFailureProfileImage()
    var mGetUserInfo = profileRepo.getUserInfo()

    fun setImage(uri: Uri) {
        viewModelScope.launch(Dispatchers.Main) {
            profileRepo.upLoadProfileImage(uri)
        }


    }


    fun getImage() {
        viewModelScope.launch(Dispatchers.Main) { profileRepo.downloadProfileImage() }

        // mUpSuccess = profileRepo.upDataSuccessProfileImage()

    }

    fun getUserInfo() {

        viewModelScope.launch(Dispatchers.Main) {
            profileRepo.getUser()
            mGetUserInfo = profileRepo.getUserInfo()
        }

    }


    fun sendUserUpdatedInfo(user: User) {
        viewModelScope.launch(Dispatchers.Main) {
            profileRepo.setUpdateUserInfo(user)
        }

    }

    fun signOut() {
        viewModelScope.launch(Dispatchers.Main) {
            profileRepo.signOut()
        }

    }


}