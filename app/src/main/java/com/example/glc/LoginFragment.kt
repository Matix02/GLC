package com.example.glc

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.glc.databinding.FragmentLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment() {
    companion object {
        private const val TAG = "LoginFragment"
        private const val RC_SIGN_IN = 9001
    }

    private lateinit var loginViewModel: LoginViewModel

    private lateinit var navController: NavController

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        auth = Firebase.auth

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //sprawdzić zmianę poprzez NavController do innego Fragmentu, czy czasem nie w funkcji to umiescic
        navController = findNavController()
        initGoogleSignInClient()

        observeAuthenticationState()

        binding?.loginButton?.setOnClickListener { signIn() }
        binding?.button2?.setOnClickListener { signOut() }
    }

    private fun signOut() {
        googleSignInClient.signOut()
    }

    private fun observeAuthenticationState() {
        loginViewModel.authenticationState.observe(viewLifecycleOwner, {
            when (it) {
                LoginViewModel.AuthenticationState.AUTHENTICATED -> {
                    Toast.makeText(requireContext(), "AUTHENTICATED", Toast.LENGTH_LONG).show()
                    Log.d("Creating2", "Observe ViewModel")
                    navController.navigate(R.id.current_game_list)
                }
                else -> { Toast.makeText(requireContext(), "NO AUTHENTICATED", Toast.LENGTH_LONG).show() }
            }
        })
    }

    private fun initGoogleSignInClient() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task?.getResult(ApiException::class.java)
                Log.d(TAG, "Task 1 - firebaseAuthWithGoogle:" + account?.id)

                firebaseAuthWithGoogle(account?.idToken!!)
            } catch (e: ApiException) {
                Log.w(TAG, "Google sign in failed $e")
            }
        }
    }
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        loginViewModel.signInWithGoogle(credential)
        val user2 = User(idToken, "JJJJJ", "Wp@wp.pl")
        loginViewModel.createUser(user2)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    updateUI(null)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null)
            Toast.makeText(requireContext(), "Success!!!", Toast.LENGTH_LONG).show()
        else {
            Toast.makeText(requireContext(), "Failed!!!", Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
//Dobre praktyki stosowanie zapisów w OnCreate i OnViewCreated
}

