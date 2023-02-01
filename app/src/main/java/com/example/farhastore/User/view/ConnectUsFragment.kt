package com.example.farhastore.User.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.farhastore.R
import com.example.farhastore.databinding.FragmentConnectUsBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class ConnectUsFragment : Fragment() {
    lateinit var binding: FragmentConnectUsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentConnectUsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imgBack.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_connectUsFragment_to_profileFragment)

        }

        binding.goToWhatsAppGroup.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://chat.whatsapp.com/JFTaLYLxBOiCfIt7DWMaSC")
            startActivity(intent)
        }

        binding.gotToGroupFaceBook.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.facebook.com/groups/1487622518250480/?ref=share_group_link")
            startActivity(intent)
        }

        binding.gotToGroupTelegram.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://t.me/cnnlg")
            startActivity(intent)
        }

     binding.gotToGroupPrivate.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://wa.me/201554220763")
            startActivity(intent)
        }



  binding.gotToDownloadApp.setOnClickListener {

      var refLinkApp = Firebase.database.reference.child("LinkNewVersion")

          refLinkApp.addValueEventListener(object : ValueEventListener {
              override fun onDataChange(snapshot: DataSnapshot) {
                  var intent = Intent(Intent.ACTION_VIEW)
                  intent.data = Uri.parse( snapshot.value.toString())
                  startActivity(intent)
              }

              override fun onCancelled(error: DatabaseError) {

              }

          })


        }




    }

    companion object {


    }
}