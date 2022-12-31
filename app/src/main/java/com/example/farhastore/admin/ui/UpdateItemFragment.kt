package com.example.farhastore.admin.ui

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.farhastore.R
import com.example.farhastore.User.Util.constant
import com.example.farhastore.User.model.Products
import com.example.farhastore.admin.Repo.RepoUpdateItem
import com.example.farhastore.databinding.FragmentUpdateItemBinding
import com.squareup.picasso.Picasso


class UpdateItemFragment : Fragment() {
    lateinit var binding: FragmentUpdateItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Picasso.get().load(products.imageProduct).into(binding.imageProduct)
        binding.etAdminUpdateProductName.setText(products.nameProduct)
        binding.etAdminUpdateProductPrice.setText(products.priceProduct.toString())
        binding.etAdminUpdateProductDesc.setText(products.descriptionProduct)






        binding.btnOkUpdate.setOnClickListener {

            if (binding.etAdminUpdateProductName.text.isNotEmpty() &&
                binding.etAdminUpdateProductPrice.text.toString().isNotEmpty() &&
                binding.etAdminUpdateProductDesc.text.isNotEmpty()

            ) {
                var name = binding.etAdminUpdateProductName.text.toString()
                var price = binding.etAdminUpdateProductPrice.text.toString().toDouble()
                var imageUri = binding.etAdminUpdateProductImageLink.text.toString()
                var desc = binding.etAdminUpdateProductDesc.text.toString()

                updateProduct(name, price, desc, imageUri)
                RepoUpdateItem.getInstance().chickIsCompleteUpdate().observe(viewLifecycleOwner) {
                    if (it)
                        Toast.makeText(context, "Complete Update", Toast.LENGTH_SHORT).show()
                    else
                        Toast.makeText(context, "Failure Update", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Complete this Fields", Toast.LENGTH_LONG).show()
            }


        }

       binding.btnDeleteItem.setOnClickListener {

           if (category == constant.offer)
           {
               // delete order

               var builder= AlertDialog.Builder(context)
               builder.apply {
                   setTitle("تنبيه ")
                   setIcon(R.drawable.farha_photo)
                   setMessage("هل انت متأكد من حذف هذاالمنتج")
                   setPositiveButton("yes",DialogInterface.OnClickListener { dialog, which ->
                       //
                      deleteOffer()
                   })


                   setCancelable(true)
                   show()


               }
           }



           else{
               // delete product

               var builder= AlertDialog.Builder(context)
               builder.apply {
                   setTitle("تنبيه ")
                   setIcon(R.drawable.farha_photo)
                   setMessage("هل انت متأكد من حذف هذاالمنتج")
                   setPositiveButton("yes",DialogInterface.OnClickListener { dialog, which ->
                       //
                       deleteProductWithCategory()
                   })


                   setCancelable(true)
                   show()


               }
           }




        }





    }
    fun deleteProductWithCategory(){
        RepoUpdateItem.getInstance().deleteProduct(products.key , category)
        RepoUpdateItem.getInstance().mDeleteProduct().observe(viewLifecycleOwner){

            if (it)
            { Toast.makeText(context, "Deleted Successful", Toast.LENGTH_SHORT).show()
                initView()
            }
            else
            { Toast.makeText(context, "Error ", Toast.LENGTH_SHORT).show()}
        }
    }

    fun deleteOffer(){
        RepoUpdateItem.getInstance().deleteOffersDB(products.key)
        RepoUpdateItem.getInstance().mDeleteOffers().observe(viewLifecycleOwner){

            if (it)
            { Toast.makeText(context, "Deleted Successful", Toast.LENGTH_SHORT).show()
                initView()
            }
            else
            { Toast.makeText(context, "Error ", Toast.LENGTH_SHORT).show()}
        }
    }



    private fun initView() {
        binding.etAdminUpdateProductName.setText("")
        binding.etAdminUpdateProductPrice.setText("")
        binding.etAdminUpdateProductDesc.setText("")
    }

    fun updateProduct(newName: String, nawPrice: Double, newDesc: String, newLinkImg: String) {
        RepoUpdateItem.getInstance().updateProduct(
            products.key,
            category,
            newName,
            nawPrice,
            newDesc,
            newLinkImg
        )
    }

    companion object {

        lateinit var products: Products
        lateinit var category: String

        fun getProduct(products: Products) {
            this.products = products
        }

    }
}