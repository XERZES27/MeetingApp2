package com.example.meetingapp.UI.Meetings.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.meetingapp.R
import com.example.meetingapp.data.Meeting

class meetingAdapter(private val list: List<Meeting>,private val context: Context)
    : RecyclerView.Adapter<meetingAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.meeting_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
         return list.size
          }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

         }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindItem(meeting:Meeting){

            val title  = itemView.findViewById(R.id.title) as TextView
            var description = itemView.findViewById(R.id.description) as TextView

            title.text = meeting.title
            description.text = meeting.description
        }

    }


}