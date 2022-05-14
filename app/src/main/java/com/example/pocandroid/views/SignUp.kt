package com.example.pocandroid.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.pocandroid.R
import com.example.pocandroid.databinding.ActivitySignUpBinding
import com.example.pocandroid.repository.AuthenticationRepository
import com.example.pocandroid.viewmodel.MainViewModel
import com.example.pocandroid.viewmodel.MainViewModelFactory

class SignUp : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    lateinit var firebaseViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up)

        binding.button2.setOnClickListener{
            signUp()
        }
        binding.goForSignIn.setOnClickListener{
            intent = Intent(this,SignIn::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.right_slide_in,R.anim.left_slide_out)
        }
    }
    private fun signUp(){

        firebaseViewModel = ViewModelProvider(this,
            MainViewModelFactory(AuthenticationRepository())
        )[MainViewModel::class.java]

        val userName = binding.useName.text.toString()
        val email = binding.emailE2.text.toString()
        val password = binding.passE2.text.toString()

        if(userName.isEmpty()|| email.isEmpty()|| password.isEmpty()){
            Toast.makeText(this,"Fields are empty",Toast.LENGTH_LONG).show()
        }
        else if(password.length<6 || password.length>13){
            Toast.makeText(this,"Password must be 6-12 digits",Toast.LENGTH_LONG).show()
        }
        else{
            firebaseViewModel.registerUserInFirebase(email , password)
        }

        firebaseViewModel.firebaseSignUp.observe(this , Observer {
                intent = Intent(this,SignIn::class.java)
                startActivity(intent)
            overridePendingTransition(R.anim.right_slide_in,R.anim.left_slide_out)
        })
    }
    override fun onBackPressed() {
        finishAffinity()
        super.onBackPressed()
    }
}