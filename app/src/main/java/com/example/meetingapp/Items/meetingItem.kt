package com.example.meetingapp.Items

import com.example.meetingapp.R
import com.example.meetingapp.data.Meeting
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder

class meetingItem(private val meeting:Meeting):Item() {



    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {


        }
    }

    override fun getLayout() = R.layout.meeting_item



}