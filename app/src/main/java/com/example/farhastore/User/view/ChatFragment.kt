package com.example.farhastore.User.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.farhastore.databinding.FragmentChatBinding


class ChatFragment : Fragment() {
    lateinit var binding: FragmentChatBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        goToHome()
        goToChatActivity()


    }

    private fun goToChatActivity() {
        binding.btnStartChat.setOnClickListener {
            startActivity(Intent(context, ChatActivity::class.java))
        }
    }

    private fun goToHome() {
        binding.imgBackFromChatFragToHome.setOnClickListener {
            startActivity(Intent(context, HomeActivity::class.java))
        }
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ChatFragment().apply {

            }
    }
}