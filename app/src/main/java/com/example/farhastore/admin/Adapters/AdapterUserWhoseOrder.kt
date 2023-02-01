package com.example.farhastore.admin.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.farhastore.R
import com.example.farhastore.User.model.User
import com.example.farhastore.admin.ui.ViewOrderUserFragment
import com.example.farhastore.databinding.RowAdminAllUsersBinding
import com.squareup.picasso.Picasso

class AdapterUserWhoseOrder : RecyclerView.Adapter<AdapterAdminUsers.UsersVH>() {

    private lateinit var context: Context
    private var list: MutableList<User> = mutableListOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterAdminUsers.UsersVH {
        context = parent.context
        return AdapterAdminUsers.UsersVH(
            RowAdminAllUsersBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: AdapterAdminUsers.UsersVH, position: Int) {
        var current = list[position]
        holder.name.text = " ${current.fName} ${current.lName}"

        if (current.photo.isNotEmpty()) {
            Picasso.get().load(current.photo).into(holder.img)
        }



        holder.itemView.setOnClickListener {
              Navigation.findNavController(it).navigate(R.id.action_ordersAdminFragment_to_viewOrderUserFragment)
              ViewOrderUserFragment.getUser(current)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setList(list: MutableList<User>) {
        this.list = list
        notifyDataSetChanged()
    }


    class UsersVH(item: RowAdminAllUsersBinding) : RecyclerView.ViewHolder(item.root) {
        var name = item.tvAdminNameUsers
        var img = item.imgAdminUsersImage
    }
}