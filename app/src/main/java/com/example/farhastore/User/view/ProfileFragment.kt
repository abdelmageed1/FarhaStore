package com.example.farhastore.User.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.farhastore.R
import com.example.farhastore.User.ViewModel.ProfileViewModel
import com.example.farhastore.User.model.User
import com.example.farhastore.databinding.FragmentProfileBinding
import com.squareup.picasso.Picasso


class ProfileFragment : Fragment() {
    private lateinit var launcher: ActivityResultLauncher<String>
    lateinit var binding: FragmentProfileBinding
    lateinit var profileVM: ProfileViewModel


    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileVM = ViewModelProvider(this)[ProfileViewModel::class.java]

        downloadImage()

        binding.profileIconChangePhoto.setOnClickListener {
            selectPhoto()

        }

        upLoadImage()
        downloadImage()
        getUserInfo()

        initMoreOption()

        updateDialogProfileInfo()

        //  onBack()


    }


    private fun updateDialogProfileInfo() {
        binding.iconProfileEdit.setOnClickListener {

            var builder = AlertDialog.Builder(context)
            builder.apply {

                setView(R.layout.update_profile_userinfo)
                var alter = builder.show()

                var btnSaveData = alter.findViewById<Button>(R.id.btnUpdateProfileSave)
                btnSaveData.setOnClickListener {
                    var fName = alter.findViewById<EditText>(R.id.et_update_fname).text.toString()
                    var lName = alter.findViewById<EditText>(R.id.et_update_lname).text.toString()
                    var phone = alter.findViewById<EditText>(R.id.et_update_Phone).text.toString()
                    var address =
                        alter.findViewById<EditText>(R.id.et_update_address).text.toString()

                    if (fName.isNotEmpty() && lName.isNotEmpty() && address.isNotEmpty()) {
                        if (phone.length == 11) {
                            var user = User(fName = fName, lName = lName, address = address, phone = phone)
                            setUserInfoToDB(user)
                            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
                            alter.dismiss()
                        } else {
                            Toast.makeText(
                                context,
                                "${resources.getString(R.string.invalid_phone_number)}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    } else {
                        Toast.makeText(
                            context,
                            "${resources.getString(R.string.complete_this_fields)}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

    }

    // send info to  data base
    private fun setUserInfoToDB(user: User) {
        profileVM.sendUserUpdatedInfo(user)
    }

    private fun getUserInfo() {
        profileVM.getUserInfo()

        profileVM.mGetUserInfo.observe(viewLifecycleOwner) {
            binding.tvProfileName.text = "${it.fName} ${it.lName}"
            binding.tvProfileUserPhone.text = it.phone
            binding.tvProfileUserEmail.text = it.email
            binding.tvProfileUserGender.text = it.gender

            if (it.address.isEmpty()) {

                binding.tvProfileUserAddress.visibility = View.GONE
                binding.tvTextAddress.visibility = View.GONE
            } else {
                binding.tvProfileUserAddress.text = it.address
                binding.tvProfileUserAddress.visibility = View.VISIBLE
                binding.tvTextAddress.visibility = View.VISIBLE
            }
        }
    }

    private fun downloadImage() {
        profileVM.getImage()
        profileVM.mDownSuccess.observe(viewLifecycleOwner) {
            Picasso.get().load(it).into(binding.imProfile);
            showIconSelectImg()
            hideProgressTakePhoto()

        }
        profileVM.mDownFailure.observe(viewLifecycleOwner) {
            //  Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
            showIconSelectImg()
            hideProgressTakePhoto()
        }
    }

    private fun upLoadImage() {

        launcher =
            registerForActivityResult(ActivityResultContracts.GetContent(), ActivityResultCallback {
                if (it != null) {
                    binding.imProfile.setImageURI(it)
                    profileVM = ProfileViewModel()
                    profileVM.setImage(it)

                    showProgressTakePhoto()
                    hideIconSelectImg()

                    downloadImage()


                    profileVM.mUpFailure.observe(viewLifecycleOwner) {
                        Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
                    }

                } else {
                }


            })
    }

    private fun initMoreOption() {
        binding.iconMoreProfile.setOnClickListener { img ->


            val pop = PopupMenu(context, binding.iconMoreProfile)
            pop.menuInflater.inflate(R.menu.menu_more_option_profile, pop.menu)
            pop.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.contact_us -> {
                        Navigation.findNavController(binding.iconMoreProfile)
                            .navigate(R.id.action_profileFragment_to_connectUsFragment)
                    }
                    R.id.about -> {

                        Navigation.findNavController(img)
                            .navigate(R.id.action_profileFragment_to_aboutFragment)

                    }
                    R.id.signOut -> {
                        profileVM.signOut()
                        startActivity(Intent(context, Login::class.java))
                        activity?.finish()

                    }

                }
                true
            }

            pop.show()
        }
    }


    fun selectPhoto() {

        launcher.launch("image/*")


    }

    private fun showProgressTakePhoto() {
        binding.profileProgTakePhoto.visibility = View.VISIBLE
    }

    private fun hideProgressTakePhoto() {
        binding.profileProgTakePhoto.visibility = View.GONE
    }

    private fun showIconSelectImg() {
        binding.profileIconChangePhoto.visibility = View.VISIBLE
    }

    private fun hideIconSelectImg() {
        binding.profileIconChangePhoto.visibility = View.GONE
    }


}

