package com.example.glc

import android.app.Application
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthCredential

class LoginViewModel(val application: Application) : ViewModel() {

    var  userRepository : UserRepository = UserRepository()

    lateinit var userLiveData : LiveData<User>
    val user: LiveData<User> = TODO()

    fun signInWithGoogle(googleAuthCredential: AuthCredential) {
        userLiveData = userRepository.firebaseSingInWithGoogle(googleAuthCredential)
    }

    fun createUser(authenticatedUser: User) {
        userLiveData = userRepository.createNewUser(authenticatedUser)
    }




    enum class AuthenticationState {
        AUTHENTICATED, UNAUTHENTICATED, INVALID_AUTHENTICATION
    }

    val authenticationState = UserRepository().map { user ->
        if (user != null) {
            AuthenticationState.AUTHENTICATED
        } else {
            AuthenticationState.UNAUTHENTICATED
        }
    }



}
