package com.project.dishnary.viewmodel

import android.os.Message
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AuthenticationVM
    @Inject constructor(val auth: FirebaseAuth) : ViewModel(){


    private val _authstate = MutableStateFlow<AuthState>(AuthState.Loading)
    val authstate : StateFlow<AuthState> = _authstate

    init {
        checkAuthStatus()
    }

    fun checkAuthStatus(){
        if(auth.currentUser==null){
            _authstate.value=AuthState.UnAuthenticated
        }else{
            _authstate.value=AuthState.Authenticated
        }
    }

    fun login(email:String,password:String){
        if(email.isEmpty()||password.isEmpty()){
            _authstate.value=AuthState.Error("Please enter email and password")
                return
        }
        _authstate.value=AuthState.Loading
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
            if(it.isSuccessful){
                _authstate.value=AuthState.Authenticated
            }else{
                _authstate.value=AuthState.Error(it.exception?.message?:"Something is wrong")
            }
        }
    }

    fun signup(email:String,password:String){
        if(email.isEmpty()||password.isEmpty()){
            _authstate.value=AuthState.Error("Please enter email and password")
            return
        }
        _authstate.value=AuthState.Loading
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
            if(it.isSuccessful){
                _authstate.value=AuthState.Authenticated
            }else{
                _authstate.value=AuthState.Error(it.exception?.message?:"Something is wrong")
            }
        }
    }

    fun signOut(){
        auth.signOut()
        _authstate.value=AuthState.UnAuthenticated
    }

}

sealed class AuthState{
    object Authenticated :AuthState()
    object UnAuthenticated :AuthState()
    object Loading : AuthState()
    data class Error(val message: String) : AuthState()

}