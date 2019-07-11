package com.example.meetingapp.UI.Meetings

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.meetingapp.R
import com.example.meetingapp.UI.Meetings.adapters.meetingAdapter
import com.example.meetingapp.ViewModels.MeetingViewModel
import com.example.meetingapp.ViewModels.PollViewModel
import com.example.meetingapp.data.Meeting
import kotlinx.android.synthetic.main.all_meetings_fragment.*

class allMeetingsFragment : Fragment() {
    private var adapter:meetingAdapter? = null
    private var layoutManager:RecyclerView.LayoutManager? = null
    private lateinit var viewModel: MeetingViewModel
    private lateinit var pollViewModel: PollViewModel

    companion object {
        fun newInstance() = allMeetingsFragment()


    }
    fun getAllMeetings():List<Meeting>?{

        if(connected()){
            viewModel.getAllMeetings()
             var meetings = viewModel.getAllMeeting.value?.body()
            for ( meeting in meetings!!){
                suspend {
                    viewModel.insert(meeting)
                }
            }
            return meetings
        }
        else{
            return null

        }

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layoutManager = LinearLayoutManager(context)
        val meetings = getAllMeetings()
        if(meetings== null){
            suspend {
                val meetings:List<Meeting> = viewModel.AllMeeting().value!!
                adapter = meetingAdapter(meetings,context!!)
            }
        }
        else{
            adapter = meetingAdapter(meetings,context!!)

        }
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        recyclerView.setOnClickListener{
            findNavController().navigate(R.id.action_allMeetingsFragment_to_detailMeetingFragment2)

        }

        fab.setOnClickListener {
            findNavController().navigate(R.id.action_allMeetingsFragment_to_addMeetingFragment)
        }

    }

    private fun connected():Boolean {
        val context = requireContext()
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        return activeNetwork != null && activeNetwork.isConnected
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.all_meetings_fragment, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MeetingViewModel::class.java)
        pollViewModel = ViewModelProviders.of(this).get(PollViewModel::class.java)
        bindUI()


    }

    private fun bindUI() = suspend{
        var allMeeting = viewModel.getAllMeeting
        allMeeting.observe(this, Observer {
            adapter!!.notifyDataSetChanged()
        })
    }



}
