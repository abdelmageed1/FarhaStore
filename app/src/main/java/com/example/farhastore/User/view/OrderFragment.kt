package com.example.farhastore.User.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.farhastore.User.Adapters.OrderAdapter
import com.example.farhastore.User.ViewModel.OrderViewModel
import com.example.farhastore.User.model.Orders
import com.example.farhastore.databinding.FragmentOrderBinding

class OrderFragment : Fragment() {

    lateinit var binding: FragmentOrderBinding
    lateinit var orderViewModel: OrderViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        orderViewModel = ViewModelProvider(this)[OrderViewModel::class.java]
        orderViewModel.getOrder()

        binding = FragmentOrderBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        orderViewModel.getMutableOrder().observe(viewLifecycleOwner) {
            if (it != null)
            {
                setRecycle(it)


                    if (it.size == 0) {
                        binding.OrderIsEmpty.visibility = View.VISIBLE

                    }
            }

        }


    }

    private fun setRecycle(it: MutableList<Orders>) {
        var adapterOrder = OrderAdapter()
        binding.recycleOrders.layoutManager = LinearLayoutManager(context)
        binding.recycleOrders.adapter = adapterOrder
        adapterOrder.setListOrders(it)

    }


}