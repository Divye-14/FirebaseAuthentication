package com.example.pocandroid.repository

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AuthenticationRepository {
    lateinit var auth:FirebaseAuth

    var firebaseSignUpUser:MutableLiveData<FirebaseUser> = MutableLiveData()
    var firebaseSignInUser:MutableLiveData<FirebaseUser> = MutableLiveData()

    fun registerUser(email:String ,password:String){
        auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(email , password).addOnCompleteListener{
            if(it.isSuccessful){
                firebaseSignUpUser.value = auth.currentUser
                Log.d("FbRepo" , "this is from here22 ${it.result} , ${firebaseSignUpUser}")
            }else{
                Log.d("FbRepo" , "this is from here ${it.result} , ${firebaseSignUpUser}")
            }
        }
    }

    fun loginUser(email: String , password: String){
        auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email , password).addOnCompleteListener {
            if (it.isSuccessful)
            {
                firebaseSignInUser.value = auth.currentUser
                Log.d("FbRepo" ,  "${it.isComplete},${it.isSuccessful},${it.result},${auth.currentUser}")
            }
            else{
                Log.d("FbRepo" , "${it.isComplete},${it.isSuccessful},${it.result},${auth.currentUser}")
            }
            Log.d("FbRepo","${it.isComplete},${it.isSuccessful},${it.result},${auth.currentUser}")
        }
    }
}