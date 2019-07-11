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
import android.widget.Toast
import androidx.navigation.fragment.findNavController

import com.example.meetingapp.R
import com.example.meetingapp.ViewModels.UserViewModel
import com.example.meetingapp.data.User
import kotlinx.android.synthetic.main.sign_up_fragment.*
import kotlinx.android.synthetic.main.sign_up_fragment.view.*

class signUpFragment : Fragment() {
    companion object {
        fun newInstance() = signUpFragment()
    }

    private lateinit var viewModel: UserViewModel

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

        val view = inflater.inflate(R.layout.sign_up_fragment, container, false)

        view.signup.setOnClickListener {
            if(connected()){
                val user = User(signup_email.toString(),null,signup_user_name.toString(),signup_password.toString())
                viewModel.signUp(user)
                findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
            }
            else{
                Toast.makeText(context,"Not Connected",Toast.LENGTH_SHORT).show()

            }



        }


        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
