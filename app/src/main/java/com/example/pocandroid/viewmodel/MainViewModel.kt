package com.example.pocandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pocandroid.repository.AuthenticationRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch

class MainViewModel(private var repository: AuthenticationRepository):ViewModel() {

    var firebaseSignUp:MutableLiveData<FirebaseUser> = MutableLiveData()
    var firebaseSignIn:MutableLiveData<FirebaseUser> = MutableLiveData()

    fun registerUserInFirebase(email:String , password:String){
        viewModelScope.launch {
            repository.registerUser(email , password)
            firebaseSignUp =  repository.firebaseSignUpUser
        }
    }

    fun loginUserInFirebase(email:String , password : String){
        viewModelScope.launch {
            repository.loginUser(email , password )
            firebaseSignIn = repository.firebaseSignInUser
        }
    }

}