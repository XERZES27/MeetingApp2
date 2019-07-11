package com.example.meetingapp.UI.Meetings

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.SharedPreferences
import androidx.navigation.fragment.findNavController
import com.example.meetingapp.R
import com.example.meetingapp.ViewModels.MeetingViewModel
import com.example.meetingapp.data.Meeting
import kotlinx.android.synthetic.main.add_meeting_fragment.*
import kotlinx.android.synthetic.main.add_meeting_fragment.view.*

class addMeetingFragment : Fragment() {

    lateinit var sharedPreferences: SharedPreferences
    companion object {
        fun newInstance() = addMeetingFragment()
    }

    private lateinit var viewModel: MeetingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.add_meeting_fragment, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MeetingViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fab.setOnClickListener {
            val title = it.title.text.toString()
            val description = it.description.text.toString()
            val token = sharedPreferences.getString(TEXT_KEY,"kdfaakjf")
            viewModel.addMeetings(Meeting(description,0,title),token)
            suspend {
                viewModel.insert(Meeting(description,0,title))
            }
            findNavController().navigate(R.id.action_addMeetingFragment_to_allMeetingsFragment)
        }
    }

}
