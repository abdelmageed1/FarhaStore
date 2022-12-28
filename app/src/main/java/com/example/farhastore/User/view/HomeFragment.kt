package com.example.farhastore.User.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.example.farhastore.R
import com.example.farhastore.User.Adapters.CategoryAdapter
import com.example.farhastore.User.ViewModel.HomeViewModel
import com.example.farhastore.User.ViewModel.ProfileViewModel
import com.example.farhastore.User.model.Category
import com.example.farhastore.databinding.FragmentHomeBinding
import com.squareup.picasso.Picasso


class HomeFragment : Fragment() {

    private lateinit var arrCategory: MutableList<Category>

    //  private  var arr: MutableList<Products> = mutableListOf()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var profileVM: ProfileViewModel
    private lateinit var homeViewModel: HomeViewModel


    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]


        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setPhotoAndNameProfile()
        setWelcomeMessage()
        setImageSlider()
        goToProfile()
        arrCategory = mutableListOf<Category>()

        setArrayCategory()
        setRecycle(arrCategory)


        ////////////////////
        goToChat()

//        binding.slider.setItemClickListener(object : ItemClickListener {
//            override fun onItemSelected(position: Int) {
//                Toast.makeText(context, "number ${position + 1}", Toast.LENGTH_SHORT).show()
//            }
//        })


        binding.slider.setItemClickListener(object :ItemClickListener{
            override fun onItemSelected(position: Int) {
                Toast.makeText(context, "num $position ", Toast.LENGTH_SHORT).show()
             }

        })


    }

    private fun goToChat() {
        binding.imgChatIcon.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_chatFragment)
        }

    }

    private fun setImageSlider() {
          homeViewModel.getOffers()
        var imageList = ArrayList<SlideModel>()
        homeViewModel.mutableOffers().observe(viewLifecycleOwner){it->
            if(it.isNotEmpty() )
            {  imageList.clear()
              for (i in it )
              {
                   imageList.add(SlideModel(i.imageProduct.toString()))
              }
            }
            else {

                imageList.add(SlideModel("https://bit.ly/2YoJ77H"))
                imageList.add(SlideModel("https://bit.ly/2BteuF2"))

            }

            binding.slider.setImageList(imageList)
        }







//        binding.slider.setItemClickListener(object : ItemClickListener {
//            override fun onItemSelected(position: Int) {
//                Toast.makeText(context, "number ${position + 1}", Toast.LENGTH_SHORT).show()
//            }
//        })
    }

    private fun goToProfile() {
        binding.imgHomeUserImage.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_profileFragment)
        }
    }

    private fun setWelcomeMessage() {
        homeViewModel.getWelComeMessage()
        homeViewModel.mWelMessage.observe(viewLifecycleOwner) {
            binding.tvHomeMessage.text = it
            if (it.isEmpty()) {
                binding.tvHomeMessage.visibility = View.GONE
            } else {
                binding.tvHomeMessage.visibility = View.VISIBLE
            }
        }
    }


    private fun setArrayCategory() {
        arrCategory.add(Category("${getString(R.string.handmade)} ", R.drawable.handmade_category))
        arrCategory.add(Category("${getString(R.string.accessories)}", R.drawable.accessories_category))
        arrCategory.add(Category("${getString(R.string.print)}", R.drawable.print_category1))
        arrCategory.add(Category("${getString(R.string.laser)}", R.drawable.category_laser))
        arrCategory.add(Category("${getString(R.string.resin)}", R.drawable.resin_category1))
        arrCategory.add(Category("${getString(R.string.skins)}", R.drawable.skins_category1))

    }


    private fun setRecycle(arr: MutableList<Category>) {
        var categoryAdapter = CategoryAdapter()
        binding.recycleCategoryHome.layoutManager = GridLayoutManager(context, 3)
        binding.recycleCategoryHome.adapter = categoryAdapter

        categoryAdapter.setList(arr)
    }


    private fun setPhotoAndNameProfile() {
        profileVM = ViewModelProvider(this)[ProfileViewModel::class.java]
        downloadHomeUserImage()
        setUserName()
    }


    private fun setUserName() {

        profileVM.getUserInfo()

        profileVM.mGetUserInfo.observe(viewLifecycleOwner) {
            binding.tvHomeUserName.text = "${it.fName} ${it.lName}"

        }

    }

    private fun downloadHomeUserImage() {
        profileVM.getImage()
        profileVM.mDownSuccess.observe(viewLifecycleOwner) {
            Picasso.get().load(it).into(binding.imgHomeUserImage);


        }
        profileVM.mDownFailure.observe(viewLifecycleOwner) {
            //  Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()

        }
    }


}