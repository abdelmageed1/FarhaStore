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
import com.example.farhastore.admin.Adapters.AdapterUserWhoseOrder
import com.example.farhastore.admin.viewModel.AdminViewModel
import com.example.farhastore.admin.viewModel.OrderAdminViewModel
import com.example.farhastore.databinding.FragmentOrdersAdminBinding


class OrdersAdminFragment : Fragment() {
    lateinit var binding: FragmentOrdersAdminBinding
    lateinit var orderVM :OrderAdminViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        orderVM =  ViewModelProvider(this)[OrderAdminViewModel::class.java]
        orderVM.getUserWhoseOrder()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrdersAdminBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        orderVM.mUserWhoseOrder?.observe(viewLifecycleOwner) {
            setRecycle(it)
            if (it.size == 0) {
                binding.OrderIsEmpty.visibility = View.VISIBLE

            }
        }


    }
    private fun setRecycle(it: MutableList<User>) {
        var adapterUsers = AdapterUserWhoseOrder()
        binding.recycleAdminUsersWhoseOrder.layoutManager = LinearLayoutManager(context)
        binding.recycleAdminUsersWhoseOrder.adapter = adapterUsers
        adapterUsers.setList(it)

    }


    companion object {

    }
}