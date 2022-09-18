package com.karasu.yome.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.karasu.yome.R
import com.karasu.yome.databinding.UserItemBinding
import com.karasu.yome.model.User

class UsersAdapter(var users: MutableList<User>) : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    var onUserClick: ((User) -> Unit)? = null
//    var users: MutableList<User> = mutableListOf()
//
//    var data = dataSet

    inner class ViewHolder(val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                onUserClick?.invoke(users[bindingAdapterPosition])
            }
        }
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val user = users[position]

        viewHolder.binding.apply {
            itemTitle.text = "${user.username}"
            itemCover.load(R.drawable.ic_round_home_24)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            UserItemBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        )
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = users.size

}
