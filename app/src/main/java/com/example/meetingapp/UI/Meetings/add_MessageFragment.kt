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
import android.widget.Toast
import androidx.navigation.fragment.findNavController

import com.example.meetingapp.R
import com.example.meetingapp.ViewModels.MessageViewModel
import com.example.meetingapp.data.Message
import kotlinx.android.synthetic.main.add__message_fragment.*
import kotlinx.android.synthetic.main.add__message_fragment.view.*
import kotlinx.android.synthetic.main.add__message_fragment.view.title

class add_MessageFragment : Fragment() {

    companion object {
        fun newInstance() = add_MessageFragment()
    }

    private lateinit var viewModel: MessageViewModel
    private lateinit var sharedPreferences: SharedPreferences

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
        return inflater.inflate(R.layout.add__message_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MessageViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab.setOnClickListener {
            val title = it.title.text.toString()
            val description = it.body.text.toString()
            val message = Message(0,description,0,0)
            if(connected()){
                val token = sharedPreferences.getString(TEXT_KEY," ")
                viewModel.createAMessage(token,message)
                findNavController().navigate(R.id.action_addMeetingFragment_to_allMeetingsFragment)
            }
            else{
                Toast.makeText(context,"No Connetion", Toast.LENGTH_SHORT).show()
            }

        }


    }

}
