package com.example.pocandroid.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.pocandroid.R
import com.example.pocandroid.databinding.ActivitySignInBinding
import com.example.pocandroid.repository.AuthenticationRepository
import com.example.pocandroid.viewmodel.MainViewModel
import com.example.pocandroid.viewmodel.MainViewModelFactory
class SignIn : AppCompatActivity() {
    lateinit var binding: ActivitySignInBinding
    lateinit var firebaseViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)

        binding.button.setOnClickListener {
            signIn()

        }
        binding.goForSignUp.setOnClickListener{
            intent = Intent(this,SignUp::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.left_slide_in,R.anim.right_slide_out)
        }
    }
        private fun signIn()
        {
            firebaseViewModel = ViewModelProvider(this,
                MainViewModelFactory(AuthenticationRepository())
            )[MainViewModel::class.java]

            val email = binding.emailE.text.toString()
            val password = binding.passE.text.toString()

            if(email.isEmpty()|| password.isEmpty()){
                Toast.makeText(this,"Fields are empty", Toast.LENGTH_LONG).show()
            }
            else if(password.length<6 || password.length>13){
                Toast.makeText(this,"Password must be 6-12 digits", Toast.LENGTH_LONG).show()
            }
            else{
                firebaseViewModel.loginUserInFirebase(email , password)
            }




            firebaseViewModel.firebaseSignIn.observe(this , Observer {
                intent  = Intent(this,Drawer::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.right_slide_in,R.anim.left_slide_out)

            })

        }

}