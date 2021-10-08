package com.example.glc.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.glc.user.User
import com.example.glc.user.UserRepository
import com.google.firebase.auth.AuthCredential

class LoginViewModel() : ViewModel() {

    var  userRepository : UserRepository = UserRepository()

    lateinit var userLiveData : LiveData<User>

    fun signInWithGoogle(googleAuthCredential: AuthCredential) {
        userLiveData = userRepository.firebaseSingInWithGoogle(googleAuthCredential)
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
