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
import com.example.meetingapp.ViewModels.UserViewModel
import com.example.meetingapp.data.User
import kotlinx.android.synthetic.main.sign_in_fragment.*
import kotlinx.android.synthetic.main.sign_in_fragment.view.*


const val TEXT_KEY = "token"
const val SHARED_PREFERENCE_ID = "shared_preference_file_id"

class signInFragment : Fragment() {

    private fun connected():Boolean {
        val context = requireContext()
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

        return activeNetwork != null && activeNetwork.isConnected
    }

    companion object {
        fun newInstance() = signInFragment()
    }



    private lateinit var viewModel: UserViewModel
    lateinit var sharedPreferences: SharedPreferences
    lateinit var token : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        sharedPreferences = getSharedPreferences(SHARED_PREFERENCE_ID, Context.MODE_PRIVATE)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.sign_in_fragment, container, false)
        val signInButton = view.signin

        signInButton.setOnClickListener {
            if(connected()){
                val user = User(signin_email.toString(),null,signin_user_name.toString(),signin_password.toString())
                viewModel.signIn(user)
                val token = viewModel.signin.value?.body()?.token
                saveToken(token)
                findNavController().navigate(R.id.action_signInFragment_to_allMeetingsFragment)


            }
            else{
                Toast.makeText(context,"Not Connected",Toast.LENGTH_SHORT).show()
            }



        }
        create_account.setOnClickListener {
            if(connected()){
                findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)


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

    private fun loadToken() {
        val savedText = sharedPreferences.getString(TEXT_KEY, "kdfaakjf")
        token = savedText!!
    }

    private fun saveToken(token: String?) {
        with(sharedPreferences.edit()){
            putString(TEXT_KEY, token)
            apply()
        }
    }



}
