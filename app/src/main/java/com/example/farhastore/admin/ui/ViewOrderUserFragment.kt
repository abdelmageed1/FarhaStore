package com.example.farhastore.admin.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.farhastore.R
import com.example.farhastore.User.Adapters.OrderAdapter
import com.example.farhastore.User.model.Orders
import com.example.farhastore.User.model.User
import com.example.farhastore.admin.Adapters.AdapterOrderAdmin
import com.example.farhastore.admin.Adapters.AdapterUserWhoseOrder
import com.example.farhastore.admin.viewModel.OrderAdminViewModel
import com.example.farhastore.databinding.FragmentViewOrderUserBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


class ViewOrderUserFragment : Fragment() {
    lateinit var binding :FragmentViewOrderUserBinding
    lateinit var orderVM : OrderAdminViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        orderVM =  ViewModelProvider(this)[OrderAdminViewModel::class.java]
        orderVM.getOrders(user.userId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

         binding = FragmentViewOrderUserBinding.inflate(inflater ,container ,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



         if(user.photo.toUri().toString().isEmpty()){
             Picasso.get().load(R.drawable.image_profile).into(binding.imProfile);
         }
        else{
             Picasso.get().load(user.photo.toUri()).into(binding.imProfile);
         }

        binding.tvUName.text = "${user.fName} ${user.lName}"
        binding.uAddress.text = "${user.address} "
        binding.uNumber.text = "${user.phone} "

        binding.uNumber.setOnClickListener {
            goToUserWhatsApp(user.phone)
        }



           GlobalScope.launch(Dispatchers.Main) {
               orderVM.mOrder?.observe(viewLifecycleOwner) {
                   setRecycle(it)


               }


        }

    }
    private fun setRecycle(it: MutableList<Orders>) {
        var adapterOrder = AdapterOrderAdmin()
        AdapterOrderAdmin.uid = user.userId
        binding.recycleUOrder.layoutManager = LinearLayoutManager(context)
        binding.recycleUOrder.adapter = adapterOrder
        adapterOrder.setListOrders(it)

    }

    // go to user whats app
    fun goToUserWhatsApp(phone :String){
        var intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("https://wa.me/2${phone.trim()}")
        startActivity(intent)


    }


    companion object {
        lateinit var user: User

        fun getUser(user: User) {
            this.user = user
        }

    }
}