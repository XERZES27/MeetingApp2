package com.example.meetingapp.UI.Meetings.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.meetingapp.R
import com.example.meetingapp.data.Message

class messageAdapter(private val list: List<Message>,private val context: Context)
    : RecyclerView.Adapter<messageAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.message_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindItem(message: Message){

            val title  = itemView.findViewById(R.id.sender_id) as TextView
            var description = itemView.findViewById(R.id.message) as TextView

            title.text = message.sender_id.toString()
            description.text = message.body
        }

    }


}