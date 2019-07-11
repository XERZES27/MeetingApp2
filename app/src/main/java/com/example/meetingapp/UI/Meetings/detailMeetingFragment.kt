package com.example.meetingapp.UI.Meetings

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.meetingapp.R
import com.example.meetingapp.UI.Meetings.adapters.messageAdapter
import com.example.meetingapp.ViewModels.MessageViewModel
import com.example.meetingapp.ViewModels.PollViewModel
import com.example.meetingapp.data.Message
import com.example.meetingapp.data.Poll
import kotlinx.android.synthetic.main.detail_meeting_fragment.*
import kotlinx.android.synthetic.main.detail_meeting_fragment.view.*

class detailMeetingFragment : Fragment() {
    private var adapter: messageAdapter? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private lateinit var viewModel: MessageViewModel
    private lateinit var pollViewModel: PollViewModel
    private lateinit var sharedPreferences:SharedPreferences
    companion object {
        fun newInstance() = detailMeetingFragment()
    }

    private fun connected():Boolean {
        val context = requireContext()
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        return activeNetwork != null && activeNetwork.isConnected
    }

    fun getAllMessages():List<Message>?{
        if(connected()){
            val token = sharedPreferences.getString(TEXT_KEY,"null")
            viewModel.getAllMessages(token!!)
            val messages = viewModel.AllMessages.value?.body()
            for(message in messages!!){
                suspend {
                    viewModel.updateMessage(message)
                }
            }
            return messages
        }
        else{
            return null
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_meeting_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MessageViewModel::class.java)
        pollViewModel = ViewModelProviders.of(this).get(PollViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layoutManager = LinearLayoutManager(context)
        var messages = getAllMessages()
        if(messages == null){
            suspend {
                messages = viewModel.getAllMessages(0).value!!
                adapter = messageAdapter(messages!!,context!!)
            }
        }
        else{
            adapter = messageAdapter(messages!!,context!!)
        }
        user_buttton.setOnClickListener {
            findNavController().navigate(R.id.action_detailMeetingFragment_to_userFrament)
        }

        imageView_send.setOnClickListener {
            createPoll(it)
        }
        yes_button.setOnClickListener {
            var question = it.editText_message.text.toString()
            val poll = Poll(0,question,yes_button.text.toString().toInt()+1,0," ",0,0 )
            if(connected()){
                pollViewModel.createApoll(poll)
                suspend {
                    pollViewModel.createPoll(poll)
                }
                yes_button.text = (yes_button.text.toString().toInt()+1).toString()
            }


        }
        no_button.setOnClickListener {
            var question = it.editText_message.text.toString()
            val poll = Poll(0,question,no_button.text.toString().toInt()-1,0," ",0,0 )
            if(connected()){
                pollViewModel.createApoll(poll)
                suspend {
                    pollViewModel.createPoll(poll)
                }
            }

            no_button.text = (no_button.text.toString().toInt()-1).toString()
        }

        fab.setOnClickListener {
            findNavController().navigate(R.id.action_allMeetingsFragment_to_addMeetingFragment)
        }




    }

    fun createPoll(it:View){

        var question = it.editText_message.text.toString()
        val poll = Poll(0,question,0,0," ",0,0 )
        if(connected()){
            pollViewModel.createApoll(poll)
            suspend {
                pollViewModel.createPoll(poll)
            }
        }


    }

}
