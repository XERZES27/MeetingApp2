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
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.meetingapp.R
import com.example.meetingapp.UI.Meetings.adapters.userAdapter
import com.example.meetingapp.ViewModels.UserViewModel

class userFrament : Fragment() {

    companion object {
        fun newInstance() = userFrament()
    }
    private var adapter: userAdapter? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_frament_fragment, container, false)
    }
    private fun connected():Boolean {
        val context = requireContext()
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        return activeNetwork != null && activeNetwork.isConnected
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layoutManager = LinearLayoutManager(context)
        suspend {
            adapter = userAdapter( viewModel.getAllUsers.value?.body()!!,context!!)
        }
        viewModel.getAllUsers.observe(this, Observer {
            adapter = userAdapter(it.body()!!,context!!)
        })




    }}


