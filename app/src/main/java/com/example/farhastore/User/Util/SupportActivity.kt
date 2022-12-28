package com.example.farhastore.User.Util

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.farhastore.databinding.ActivitySupportBinding

class SupportActivity : AppCompatActivity() {
    lateinit var binding: ActivitySupportBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySupportBinding.inflate(layoutInflater)
        setContentView(binding.root)





    }
}