package com.example.meetingapp.UI.Meetings.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.meetingapp.R
import com.example.meetingapp.data.User
import kotlinx.android.synthetic.main.user_item.view.*

class userAdapter(private val list: List<User>, private val context: Context)
    : RecyclerView.Adapter<userAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.user_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindItem(user: User){

            val name = itemView.name.text as TextView
            val email = itemView.email.text as TextView
            name.text = user.name.toString()
            email.text = user.email.toString()





        }

    }


}