package com.example.farhastore.User.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.farhastore.R
import com.example.farhastore.SupportActivity
import com.example.farhastore.User.Util.constant
import com.example.farhastore.User.model.Category
import com.example.farhastore.User.view.ShowCategoryItems
import com.example.farhastore.databinding.RowCategoryBinding




class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private lateinit var context: Context
    private var list: MutableList<Category> = mutableListOf<Category>()


    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        context = parent.context
        return CategoryViewHolder(RowCategoryBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }



    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        var current = list[position]

        holder.img.setImageResource(current.img)
        holder.name.text = current.name

        holder.itemView.setOnClickListener {
            when (current.img) {


                R.drawable.handmade_category -> {
                    goToShowCategory(it)
                    ShowCategoryItems.getCategory(constant.HandMade)
                }
                R.drawable.accessories_category -> {
                    goToShowCategory(it)
                    ShowCategoryItems.getCategory(constant.Accessories)
                }
                R.drawable.print_category1 -> {
                    goToShowCategory(it)
                    ShowCategoryItems.getCategory(constant.Print)
                }
                R.drawable.category_laser -> {
                   goToShowCategory(it)
                     ShowCategoryItems.getCategory(constant.Laser)
                    // context.startActivity(Intent(context , SupportActivity::class.java))
                }
                R.drawable.resin_category1 -> {
                    goToShowCategory(it)
                    ShowCategoryItems.getCategory(constant.Resin)
                }
                R.drawable.skins_category1 -> {
                    goToShowCategory(it)
                    ShowCategoryItems.getCategory(constant.Skins)
                }


            }
        }
    }


    fun goToShowCategory(it: View) {
        Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_showCategoryItems)
    }

    override fun getItemCount(): Int {
        return list.size
    }


    fun setList(list: MutableList<Category>) {
        this.list = list

        notifyDataSetChanged()
    }

    class CategoryViewHolder(itemView: RowCategoryBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        var img = itemView.categoryImage
        var name = itemView.categoryName


    }


}