package com.example.farhastore.admin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.farhastore.User.model.User
import com.example.farhastore.admin.Adapters.AdapterAdminUsers
import com.example.farhastore.admin.viewModel.AdminViewModel
import com.example.farhastore.databinding.FragmentAdminUserBinding


class AdminUserFragment : Fragment() {
    lateinit var binding: FragmentAdminUserBinding
    lateinit var adminViewModel: AdminViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adminViewModel = ViewModelProvider(this)[AdminViewModel::class.java]
        adminViewModel.getAllUsers()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentAdminUserBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        adminViewModel.mGetAllUsers().observe(viewLifecycleOwner) {
            setRecycle(it)
        }


    }

    private fun setRecycle(it: MutableList<User>) {
        var adapterUsers = AdapterAdminUsers()
        binding.recycleAdminUsers.layoutManager = LinearLayoutManager(context)
        binding.recycleAdminUsers.adapter = adapterUsers
        adapterUsers.setList(it)

    }


    companion object {

    }

}