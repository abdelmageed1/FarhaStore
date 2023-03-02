package com.example.farhastore.admin.ui

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.farhastore.R
import com.example.farhastore.User.Util.constant
import com.example.farhastore.User.ViewModel.ProfileViewModel
import com.example.farhastore.User.model.Products
import com.example.farhastore.User.view.HomeActivity
import com.example.farhastore.User.view.Login
import com.example.farhastore.admin.viewModel.AdminViewModel
import com.example.farhastore.admin.viewModel.VMAddOfferItem
import com.example.farhastore.databinding.FragmentAdminAddProductBinding


class AdminAddProductFragment : Fragment() {
    lateinit var binding: FragmentAdminAddProductBinding
    private lateinit var launcher: ActivityResultLauncher<String>

    lateinit var adminProductViewModel: AdminViewModel
    lateinit var vmAddOfferItem: VMAddOfferItem
    lateinit var uriImage: Uri

    var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vmAddOfferItem = ViewModelProvider(this)[VMAddOfferItem::class.java]
        adminProductViewModel = ViewModelProvider(this)[AdminViewModel::class.java]

    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentAdminAddProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMoreOption()
        setSpinner()
        upLoadImage()

        binding.selectImage.setOnClickListener {
            selectPhoto()
        }


        binding.btnAdminAddProduct.setOnClickListener {
            showProgressAndHideBtn()
            sendProduct()

        }

        binding.btnAdminAddProductWithLinkImg.setOnClickListener {
            sendProductWithLinkImg()
        }


    }

    @SuppressLint("SuspiciousIndentation")
    private fun sendProductWithLinkImg() {
        var productName = binding.etAdminAddProductName.text.toString()
        var productDesc = binding.etAdminAddProductDesc.text.toString()
        var productPrice = binding.etAdminAddProductPrice.text.toString()
        var imgLink = binding.etLinkImgProduct.text.toString()
        if (productName.isNotEmpty() && productDesc.isNotEmpty() && imgLink.isNotEmpty() && productPrice.isNotEmpty()) {
            var product = Products("", productName, productDesc, imgLink, productPrice.toDouble())
            var category = ""

            when (index) {
                0 -> {
                    adminProductViewModel.setProductWithImgLink(product, constant.HandMade)
                    adminProductViewModel.mSuccessAddProductWithLinkImg.observe(viewLifecycleOwner) {
                        if (it)
                            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                        else
                            Toast.makeText(context, "Failure ", Toast.LENGTH_SHORT).show()
                    }
                }

                1 -> {
                    adminProductViewModel.setProductWithImgLink(product, constant.Accessories)
                    adminProductViewModel.mSuccessAddProductWithLinkImg.observe(viewLifecycleOwner) {
                        if (it)
                            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                        else
                            Toast.makeText(context, "Failure ", Toast.LENGTH_SHORT).show()
                    }

                }
                2 -> {
                    adminProductViewModel.setProductWithImgLink(product, constant.Laser)
                    adminProductViewModel.mSuccessAddProductWithLinkImg.observe(viewLifecycleOwner) {
                        if (it)
                            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                        else
                            Toast.makeText(context, "Failure ", Toast.LENGTH_SHORT).show()
                    }
                }

                3 -> {
                    adminProductViewModel.setProductWithImgLink(product, constant.Print)
                    adminProductViewModel.mSuccessAddProductWithLinkImg.observe(viewLifecycleOwner) {
                        if (it)
                            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                        else
                            Toast.makeText(context, "Failure ", Toast.LENGTH_SHORT).show()
                    }
                }
                4 -> {
                    adminProductViewModel.setProductWithImgLink(product, constant.Resin)
                    adminProductViewModel.mSuccessAddProductWithLinkImg.observe(viewLifecycleOwner) {
                        if (it)
                            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                        else
                            Toast.makeText(context, "Failure ", Toast.LENGTH_SHORT).show()
                    }
                }
                5 -> {

                    adminProductViewModel.setProductWithImgLink(product, constant.Skins)
                    adminProductViewModel.mSuccessAddProductWithLinkImg.observe(viewLifecycleOwner) {
                        if (it)
                            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                        else
                            Toast.makeText(context, "Failure ", Toast.LENGTH_SHORT).show()
                    }

                }

                6 -> {
                    vmAddOfferItem.setOfferWithLinkImg(product)
                    vmAddOfferItem.mAddOfferSuccessWithLInkImg.observe(viewLifecycleOwner) {
                        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                    }
                }
            }


        } else {
            Toast.makeText(context, "Complete this Fields", Toast.LENGTH_SHORT).show()
        }

    }

    private fun reSetView() {
        binding.imgAdminAddImage.setImageResource(R.drawable.default_image_loading)
        binding.etAdminAddProductName.setText("")
        binding.etAdminAddProductDesc.setText("")
        binding.etAdminAddProductPrice.setText("")
    }

    private fun sendProduct() {
        var productName = binding.etAdminAddProductName.text.toString()
        var productDesc = binding.etAdminAddProductDesc.text.toString()
        var productPrice = binding.etAdminAddProductPrice.text.toString()


        if (productName.isNotEmpty() && productDesc.isNotEmpty() && uriImage != null && productPrice.isNotEmpty()) {

            var productImg = uriImage.toString()

            var product =
                Products("", productName, productDesc, productImg, productPrice.toDouble())
            // Category name
            when (index) {
                0 -> {

                    adminProductViewModel.setProduct(product, constant.HandMade)
                    adminProductViewModel.mSuccessAddProduct.observe(viewLifecycleOwner) {
                        if (it == true) {
                            showBtnAndHideProgress()
                            reSetView()
                            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Failure ", Toast.LENGTH_SHORT).show()
                        }
                    }
                    adminProductViewModel.mFailureAddProduct.observe(viewLifecycleOwner) {

                        Toast.makeText(context, "Failure : ${it}", Toast.LENGTH_SHORT).show()
                    }
                    showBtnAndHideProgress()
                }
                1 -> {

                    adminProductViewModel.setProduct(product, constant.Accessories)
                    showBtnAndHideProgress()
                    reSetView()
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                }
                2 -> {
                    adminProductViewModel.setProduct(product, constant.Laser)
                    showBtnAndHideProgress()
                    reSetView()
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                }
                3 -> {
                    adminProductViewModel.setProduct(product, constant.Print)
                    showBtnAndHideProgress()
                    reSetView()
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                }
                4 -> {
                    adminProductViewModel.setProduct(product, constant.Resin)
                    showBtnAndHideProgress()
                    reSetView()
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                }
                5 -> {
                    adminProductViewModel.setProduct(product, constant.Skins)
                    showBtnAndHideProgress()
                    reSetView()
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                }
                6 -> {
                    vmAddOfferItem.setOffer(product)
                    vmAddOfferItem.mutableAddOfferSuccess.observe(viewLifecycleOwner) {
                        if (it) {
                            showBtnAndHideProgress()
                            reSetView()
                            Toast.makeText(context, "uploaded successfully", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            showBtnAndHideProgress()
                            vmAddOfferItem.mutableAddOfferFailure.observe(viewLifecycleOwner) { message ->
                                //  Toast.makeText(context, "${message.toString()}", Toast.LENGTH_LONG).show()
                                showDialogError(message)
                            }
                        }
                    }

                }
            }


            // productViewModel.setProduct(product)

        } else {
            Toast.makeText(context, "Complete this Fields", Toast.LENGTH_LONG).show()
            // showDialogError("Complete this Fields")
            showBtnAndHideProgress()
        }
    }


    private fun upLoadImage() {

        launcher =
            registerForActivityResult(ActivityResultContracts.GetContent(), ActivityResultCallback {
                if (it != null) {
                    binding.imgAdminAddImage.setImageURI(it)
                    uriImage = it


                }

            })
    }

    private fun setSpinner() {

        binding.adminAddSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    when (position) {
                        0 -> {
                            index = 0
                        }
                        1 -> {
                            index = 1
                        }
                        2 -> {
                            index = 2
                        }
                        3 -> {
                            index = 3
                        }
                        4 -> {
                            index = 4
                        }
                        5 -> {
                            index = 5
                        }
                        6 -> {
                            index = 6
                        }

                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }


    }

    private fun initMoreOption() {
        binding.iconAdminAddMore.setOnClickListener {
            val pop = PopupMenu(context, binding.iconAdminAddMore)
            pop.menuInflater.inflate(R.menu.admin_add_more, pop.menu)
            pop.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.adminSignOut -> {
                        ProfileViewModel().signOut()
                        startActivity(Intent(context, Login::class.java))
                        activity?.finish()
                    }
                    R.id.userMode->{
                        startActivity(Intent(context,HomeActivity::class.java))
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

    fun showProgressAndHideBtn() {
        binding.btnAdminAddProduct.visibility = View.GONE
        binding.progressAddProduct.visibility = View.VISIBLE
    }

    fun showBtnAndHideProgress() {
        binding.progressAddProduct.visibility = View.GONE
        binding.btnAdminAddProduct.visibility = View.VISIBLE
    }

    fun showDialogError(message: String) {
        var builder = AlertDialog.Builder(context)
        builder.apply {
            setTitle("Error")
            setIcon(R.mipmap.ic_farha_icon_foreground)
            setMessage(message)
            setPositiveButton("Ok", DialogInterface.OnClickListener { dialog, which -> }
            )
            setCancelable(true)

        }
        builder.create()
        builder.show()
    }


    companion object {


    }
}