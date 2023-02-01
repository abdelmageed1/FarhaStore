package com.example.farhastore.User.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.farhastore.User.Adapters.CartAdapter
import com.example.farhastore.User.ViewModel.CartViewModel
import com.example.farhastore.User.ViewModel.OrderViewModel
import com.example.farhastore.User.model.Orders
import com.example.farhastore.User.model.Products
import com.example.farhastore.databinding.FragmentCartBinding
import java.text.SimpleDateFormat
import java.util.*

class CartFragment : Fragment() {
    lateinit var binding: FragmentCartBinding
    lateinit var cartViewModel: CartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cartViewModel = ViewModelProvider(this)[CartViewModel::class.java]
        cartViewModel.getCartItems()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var itemsInCart = mutableListOf<Products>()

        cartViewModel.getMutableCartItem().observe(viewLifecycleOwner) {
            if (it != null) {
                setRecycle(it)
                itemsInCart = it

                if (it.size == 0) {
                    binding.CartIsEmpty.visibility = View.VISIBLE

                }
            }


        }

        binding.btnBuyNow.setOnClickListener {
            // add to Order

            if (itemsInCart.size > 0) {
                var order: Orders =
                    Orders(" ", " ", getCurrentDate(), getCurrentTime(), itemsInCart, false)
                OrderViewModel().setOrder(order)
                Toast.makeText(context, "added Order ${itemsInCart.size}", Toast.LENGTH_SHORT)
                    .show()

                var cartViewModel = CartViewModel()

                // remove item in cart
                for (i in itemsInCart) {
                    cartViewModel.removeCartItem(i)

                }
                itemsInCart.clear()
            } else {
                Toast.makeText(context, "Cart Is Empty", Toast.LENGTH_SHORT).show()
            }


        }


    }


    private fun setRecycle(it: MutableList<Products>) {
        var adapterCart = CartAdapter()
        binding.recycleCart.layoutManager = LinearLayoutManager(context)
        binding.recycleCart.adapter = adapterCart
        adapterCart.setList(it)

    }

    fun getCurrentDate(): String {
        val calendar = Calendar.getInstance()
        val simpleDateFormat =
            SimpleDateFormat("MMM dd, yyyy")
        return simpleDateFormat.format(calendar.time)
    }

    fun getCurrentTime(): String {
        val calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("HH:mm:ss ")
        return simpleDateFormat.format(calendar.time)
    }

}
